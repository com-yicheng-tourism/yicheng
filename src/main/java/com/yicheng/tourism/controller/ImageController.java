package com.yicheng.tourism.controller;

import com.yicheng.tourism.base.resp.BaseResponse;
import com.yicheng.tourism.enumerate.RespStatusEnum;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


@Api(value = "角色管理接口",description = "角色管理接口")
@Slf4j
@RestController
@RequestMapping("/img")
public class ImageController {

    @RequestMapping(value = "/seekExperts",method = RequestMethod.GET)
    public String createFolw(HttpServletRequest request,
                             HttpServletResponse response, String picName,Integer type) {
        // response.setContentType("image/*");
        FileInputStream fis = null;
        OutputStream os = null;
        try {
            String baseDir = null;
            if (type == 1){
                baseDir = "D:/gif/head/"; //用户头像
            }else if (type == 2){
                baseDir = "D:/gif/store/"; //店铺头像
            }else if (type == 3){
                baseDir = "D:/gif/comment/"; //评论图片
            }else if (type == 4){
                baseDir = "D:/gif/commodity/"; //商品图片
            }
            fis = new FileInputStream(baseDir+picName);
            os = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((count = fis.read(buffer)) != -1) {
                os.write(buffer, 0, count);
                os.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            fis.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "ok";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new BaseResponse<>(RespStatusEnum.FAIL.getCode(),RespStatusEnum.FAIL.getMessage(),"请选择文件");
        }
        String fileName = file.getOriginalFilename();
        String suffixName = null;
        if (!StringUtils.isEmpty(fileName) && fileName.contains(".")){
            suffixName = fileName.substring(fileName.lastIndexOf("."));
        }

        //生成文件名称通用方法
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName = tempName.toString();
        try {
            // 保存文件
            byte[] bytes = file.getBytes();
            Path path = Paths.get("D:/gif/comment" + newFileName);
            Files.write(path, bytes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResponse<>(RespStatusEnum.SUCCESS.getCode(),RespStatusEnum.SUCCESS.getMessage(),fileName+"上传成功");
    }

}
