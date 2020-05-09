package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.wxz.confserver.from.CreateResourceFrom;
import org.wxz.confserver.service.impl.PaperServiceImpl;
import org.wxz.confserver.service.impl.ResourceServiceImpl;
import org.wxz.confsysdomain.nconfsysconf.Resource;
import org.wxz.confsysdomain.paper.Paper;
import org.wxz.nconfsyscommon.constrants.FileConstrant;
import org.wxz.nconfsyscommon.enums.ResourceTypeEnum;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;
import org.wxz.nconfsyscommon.utils.FileUtil;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Author xingze Wang
 * @create 2020/4/22 23:59
 */
@Slf4j
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private ResourceServiceImpl resourceService;

    @Autowired
    private PaperServiceImpl paperService;


    @RequestMapping( value = "/upload")
    @ResponseBody
    public ConfResponse uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
                                   @RequestParam(value = "userName",required = true)String userName,
                                   @RequestParam(value = "confId",required = true)String confId,
                                   @RequestParam(value = "note",required = false)String note,
                                   HttpServletRequest request) throws IOException {
        log.info("上传文件"+userName+confId);
        CreateResourceFrom resourceFrom=new CreateResourceFrom();
        resourceFrom.setConfId(confId);
        resourceFrom.setNote(note);
        resourceFrom.setOriginName(file.getOriginalFilename());
        //目前这里是写死的本地硬盘路径
        String rootPath = FileConstrant.BASE_LOCATION;
        String finallPath=rootPath;
        String location=null;
        //logger.info("path:" + path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(FileConstrant.IMAG_TYPES.contains(suffix) ){
            finallPath=rootPath+FileConstrant.IMAG_LOCATION;
            location=FileConstrant.IMAG_LOCATION;
            resourceFrom.setType(ResourceTypeEnum.RESOURCE_TYPE_IMG.getCode());
        }
        else if (FileConstrant.DOC_ITYPES.contains(suffix) ){
            finallPath=rootPath+FileConstrant.DOC_LOCATION;
            location=FileConstrant.DOC_LOCATION;
            resourceFrom.setType(ResourceTypeEnum.RESOURCE_TYPE_DOC.getCode());
        }
        else if(FileConstrant.ZIP_ITYPES.contains(suffix)){
            finallPath=rootPath+FileConstrant.ZIP_LOCATION;
            location=FileConstrant.ZIP_LOCATION;
            resourceFrom.setType(ResourceTypeEnum.RESOURCE_TYPE_ZIP.getCode());
        }
        else if (FileConstrant.EXCL_ITYPES.contains(suffix)){
            finallPath=rootPath+FileConstrant.EXCL_LOCATION;
            location=rootPath+FileConstrant.EXCL_LOCATION;
            resourceFrom.setType(ResourceTypeEnum.RESOURCE_TYPE_EXCL.getCode());
        }
        else {
            finallPath=rootPath+"/else";
            location="/else";
            resourceFrom.setType(ResourceTypeEnum.RESOURCE_TYPE_ELSE.getCode());
        }
        fileName = UUID.randomUUID().toString()+suffix;
        File targetFile = new File(finallPath, fileName);
        if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
            targetFile.getParentFile().mkdirs();
        }
        long size = 0;
        //保存
        try {
            file.transferTo(targetFile);
            size = file.getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfResponse.error(400);
        }
        resourceFrom.setStoreName(fileName);
        try {
            Resource result=resourceService.createOne(resourceFrom);
        }catch (Exception e){
            log.warn("上传文件-保存失败：filneme={}",fileName);
            return ConfResponse.fail("同名文件已经存在！");
        }
        //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
        String fileUrl="http://localhost:8671/nconf-gateway/api-conf-service";
        //文件获取路径
        fileUrl = fileUrl + request.getContextPath() + location +"/"+ fileName;
        log.info("资料上传-成功：url={}",fileUrl);
        return ConfResponse.success( fileName);
    }


    @RequestMapping( value = "/uploadpaper")
    @ResponseBody
    public ConfResponse uploadPaper(@RequestParam(value = "file", required = true) MultipartFile file,
                                   @RequestParam(value = "userName",required = true)String userName,
                                   @RequestParam(value = "confId",required = true)String confId,
                                   @RequestParam(value = "topic",required = false)String topic,
                                   HttpServletRequest request) throws IOException {
        log.info("上传文件"+userName+confId);
        //目前这里是写死的本地硬盘路径
        String rootPath = FileConstrant.BASE_LOCATION;
        String finallPath=rootPath;
        String location=null;
        //logger.info("path:" + path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        finallPath=rootPath+"/paper";
        location="/paper";
        fileName = UUID.randomUUID().toString()+suffix;
        File targetFile = new File(finallPath, fileName);
        if(!targetFile.getParentFile().exists()){    //注意，判断父级路径是否存在
            targetFile.getParentFile().mkdirs();
        }
        long size = 0;
        //保存
        try {
            file.transferTo(targetFile);
            size = file.getSize();
        } catch (Exception e) {
            e.printStackTrace();
            return ConfResponse.error(400);
        }
        /*resourceFrom.setStoreName(fileName);*/
        try {
            Paper paper=paperService.createOne(userName,fileName,confId,topic);
        }catch (Exception e){
            log.warn("上传论文-保存失败：filneme={}",fileName);
            return ConfResponse.fail("同名已经存在！");
        }
        //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
        String fileUrl="http://localhost:8671/nconf-gateway/api-conf-service";
        //文件获取路径
        fileUrl = fileUrl + request.getContextPath() + location +"/"+ fileName;
        log.info("论文上传-成功：url={}",fileUrl);
        return ConfResponse.success( fileName);
    }


    @RequestMapping(value = "download")
    public void download(@RequestParam("fileName") String fileName) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String hehe = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        String rootPath = FileConstrant.BASE_LOCATION;
        String finalPath=null;
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String location=null;
        suffix = suffix.toLowerCase();
        if(FileConstrant.IMAG_TYPES.contains(suffix) ){
            finalPath=rootPath+FileConstrant.IMAG_LOCATION;
            location=FileConstrant.IMAG_LOCATION;
        }
        else if (FileConstrant.DOC_ITYPES.contains(suffix) ){
            finalPath=rootPath+FileConstrant.DOC_LOCATION;
            location=FileConstrant.DOC_LOCATION;
        }
        else if(FileConstrant.ZIP_ITYPES.contains(suffix)){
            finalPath=rootPath+FileConstrant.ZIP_LOCATION;
            location=FileConstrant.ZIP_LOCATION;
        }
        else if (FileConstrant.EXCL_ITYPES.contains(suffix)){
            finalPath=rootPath+FileConstrant.EXCL_LOCATION;
            location=rootPath+FileConstrant.EXCL_LOCATION;
        }
        else {
            finalPath=rootPath+"/else";
            location="/else";
        }
        System.out.println(finalPath);
        FileUtil.download(finalPath+"/"+fileName, response);
    }


    @RequestMapping(value = "downloadpaper")
    public void downloadpaper(@RequestParam("fileName") String fileName) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        // 设置信息给客户端不解析
        String type = new MimetypesFileTypeMap().getContentType(fileName);
        // 设置contenttype，即告诉客户端所发送的数据属于什么类型
        response.setHeader("Content-type",type);
        // 设置编码
        String hehe = new String(fileName.getBytes("utf-8"), "iso-8859-1");
        // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
        response.setHeader("Content-Disposition", "attachment;filename=" + hehe);
        String rootPath = FileConstrant.BASE_LOCATION;
        String finalPath=null;
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String location=null;
        suffix = suffix.toLowerCase();
        finalPath=rootPath+"/paper";
        location="/paper";

        System.out.println(finalPath);
        FileUtil.download(finalPath+"/"+fileName, response);
    }


    @PostMapping("/addDownloadTime")
    public ConfResponse addDownLoadTime(@RequestParam(value = "resourceId",required = true)String resourceId){
        if (resourceId==null){
            log.error("增加资源下载次数-失败-参数为空");
            return ConfResponse.fail();
        }
        try {
            resourceService.downLoadTimeAdd(resourceId);
        }
        catch (Exception e){
            log.error("增加资源下载次数-异常：e={}",e.getMessage()+e.getCause()+e.getStackTrace());
            return ConfResponse.fail();
        }
        log.error("增加资源下载次数-成功");
        return ConfResponse.success();
    }


}
