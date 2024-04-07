package com.sqx.modules.file;

import com.sqx.common.utils.Result;
import com.sqx.modules.file.utils.FileUploadUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@Api(value = "文件上传", tags = {"文件上传"})
@RequestMapping(value = "/file")
@Slf4j
public class FileUploadController {

    @Value("${upload.filePath}")
    private String filePath;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation("文件上传")
    @ResponseBody
    public Result upload(@RequestParam("file") MultipartFile file){
        try
        {
            System.out.println(filePath);
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            System.out.println("=============================================");
            System.out.println(fileName);
            return Result.success().put("data",fileName);
        }
        catch (Exception e)
        {
            log.error("本地上传失败："+e.getMessage(),e);
            return Result.error(-100,"文件上传失败！");
        }
    }

//    @RequestMapping(value = "/fileToBinary", method = RequestMethod.GET)
//    @ApiOperation("文件转二进制")
    @ResponseBody
    public Result fileToBinary() throws IOException {
        try {
            File file = new File("C:\\Users\\java\\Desktop\\1712397379920.jpg"); // 替换为你的图片路径
            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            fis.close();

            StringBuilder binaryBuilder = new StringBuilder();
            for (byte b : bytes) {
                binaryBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
            }
            return Result.success().put("data", binaryBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw  e;
        }
    }
}