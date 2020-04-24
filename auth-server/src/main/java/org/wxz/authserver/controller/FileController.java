package org.wxz.authserver.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wxz.nconfsyscommon.resultVO.ConfResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Calendar;
import java.util.UUID;

/**
 * @Author xingze Wang
 * @create 2020/4/22 23:59
 */
@RestController
public class FileController {

    /**
     * 上传图片
     * @param file
     * @param request
     * @throws IOException
     */
    @RequestMapping( value = "/w")
    @ResponseBody
    public ConfResponse uploadPic(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        //目前这里是写死的本地硬盘路径
        String path = "D:/img";
       // logger.info("path:" + path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/){
            fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);
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
            String fileUrl="http://localhost:8085";
            //文件获取路径
            fileUrl = fileUrl + request.getContextPath() + "/img/" + fileName;
           // logger.info("fileUrl:" + fileUrl);
           // return ConfResponse.ok().put("fileUrl", fileUrl);
            return ConfResponse.success(fileUrl);
        }else{
            return ConfResponse.error("图片格式有误，请上传.jpg、.png、.jpeg格式的文件");
        }

    }

    @RequestMapping( value = "/upload")
    @ResponseBody
    public ConfResponse uploadPict(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        //目前这里是写死的本地硬盘路径
        String path = "D:/img";
        //logger.info("path:" + path);
        //获取文件名称
        String fileName = file.getOriginalFilename();
        //获取文件名后缀
        Calendar currTime = Calendar.getInstance();
        String time = String.valueOf(currTime.get(Calendar.YEAR))+String.valueOf((currTime.get(Calendar.MONTH)+1));
        //获取文件名后缀
        String suffix = fileName.substring(file.getOriginalFilename().lastIndexOf("."));
        suffix = suffix.toLowerCase();
        if(suffix.equals(".jpg") || suffix.equals(".jpeg") || suffix.equals(".png")/* || suffix.equals(".gif")*/){
            fileName = UUID.randomUUID().toString()+suffix;
            File targetFile = new File(path, fileName);
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
            //logger.info("fileUrl:" + fileUrl);
            System.out.println(fileUrl);
            return ConfResponse.error( fileUrl);
        }else{
            return ConfResponse.error("图片格式有误，请上传.jpg、.png、.jpeg格式的文件");
        }

    }

}
