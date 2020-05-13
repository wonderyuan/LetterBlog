package com.letterblog.blog.controller;

import com.letterblog.blog.dto.JsonResult;
import com.letterblog.blog.dto.UploadFileVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("/admin/upload")
public class UploadFileController {

    private Logger log = LoggerFactory.getLogger(UploadFileController.class);

    public final String allowSuffix = ".bmp.jpg.jpeg.png.gif.pdf.doc.zip.rar.gz";

    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public JsonResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        //1.文件后缀过滤，只允许部分后缀
        //文件的完整名称,如spring.jpeg
        String filename = file.getOriginalFilename();
        //文件名,如spring
        String name = UUID.randomUUID().toString().replace("-", "").substring(0, 18);
        //文件后缀,如.jpeg
        String suffix = filename.substring(filename.lastIndexOf("."));

        String newFileName = name + suffix;

        if (allowSuffix.indexOf(suffix) == -1) {
            return new JsonResult().fail("不允许上传该后缀的文件！");
        }


        //2.创建文件目录
        //目标文件
        File descFile = new File(request.getServletContext().getRealPath("/") + "/upload/" + newFileName);
        System.out.println(request.getServletContext().getRealPath("/") + "/upload/" + newFileName);
        //判断目标文件所在的目录是否存在
        if (!descFile.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            descFile.getParentFile().mkdirs();
        }

        //3.存储文件
        //将内存中的数据写入磁盘
        try {
            file.transferTo(descFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传失败，cause:{}", e);
        }
        //完整的url
        String fileUrl = "/upload/" + newFileName;

        //4.返回URL
        UploadFileVO uploadFileVO = new UploadFileVO();
        uploadFileVO.setTitle(newFileName);
        uploadFileVO.setSrc(fileUrl);
        return new JsonResult().ok(uploadFileVO);
    }
}