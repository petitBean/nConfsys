package org.wxz.confserver.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.wxz.nconfsyscommon.constrants.FileConstrant;
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

    @RequestMapping( value = "/upload1")
    @ResponseBody
    public ConfResponse uploadPict(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {

        //目前这里是写死的本地硬盘路径
        String path = FileConstrant.BASE_LOCATION;
        //logger.info("path:" + path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png") ){
            fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path+FileConstrant.IMAG_LOCATION, fileName);
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
                return ConfResponse.error("上传失败！");
            }
            //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
            String fileUrl="http://localhost:8671/nconf-gateway/api-auth-service/";
            //文件获取路径
            fileUrl = fileUrl + request.getContextPath() + "/img/" + fileName;
            log.info("资料上传-成功：url={}",fileUrl);
            return ConfResponse.error( fileUrl);
        }else{
            return ConfResponse.error("图片格式有误，请上传.jpg、.png、.jpeg格式的文件");
        }

    }



    @RequestMapping( value = "/upload")
    @ResponseBody
    public ConfResponse uploadFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {

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
        }
        else if (FileConstrant.DOC_ITYPES.contains(suffix) ){
            finallPath=rootPath+FileConstrant.DOC_LOCATION;
            location=FileConstrant.DOC_LOCATION;
        }
        else if(FileConstrant.ZIP_ITYPES.contains(suffix)){
            finallPath=rootPath+FileConstrant.ZIP_LOCATION;
            location=FileConstrant.ZIP_LOCATION;
        }
        else if (FileConstrant.EXCL_ITYPES.contains(suffix)){
            finallPath=rootPath+FileConstrant.EXCL_LOCATION;
            location=rootPath+FileConstrant.EXCL_LOCATION;
        }
        else {
            finallPath=rootPath+"/else";
            location="/else";
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
        //项目url，这里可以使用常量或者去数据字典获取相应的url前缀；
        String fileUrl="http://localhost:8671/nconf-gateway/api-conf-service";
        //文件获取路径
        fileUrl = fileUrl + request.getContextPath() + location +"/"+ fileName;
        log.info("资料上传-成功：url={}",fileUrl);
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


    /**
     * http://localhost:8080/file/download?fileName=新建文本文档.txt
     * @param fileName
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/download1")
    public Object downloadFile(@RequestParam String fileName ,final HttpServletResponse response, final HttpServletRequest request){
        //目前这里是写死的本地硬盘路径
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
        OutputStream os = null;
        InputStream is= null;
        //String fileName="新建文本文档.txt";
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setContentType("application/x-download;charset=GBK");
            response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(finalPath+fileName);
            is = new FileInputStream(f);
            if (is == null) {
               log.error("下载附件失败，请检查文件“" + fileName + "”是否存在");
                return null;
            }
            //复制
            IOUtils.copy(is, os);
            os.flush();
        } catch (IOException e) {
            return null;
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.error("下载文件-关闭流出错:e={}",ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                log.error("下载文件-关闭流出错:e={}",ExceptionUtils.getFullStackTrace(e));
            }
        }
        return null;
    }


}
