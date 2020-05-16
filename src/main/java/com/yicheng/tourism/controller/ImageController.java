package com.yicheng.tourism.controller;

import com.alibaba.fastjson.JSON;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;


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

    @RequestMapping(value ="/editMovieInfo", method = RequestMethod.POST)
    @ResponseBody
    public String editMovieInfo(@RequestParam("type")Integer type,@RequestParam("file")MultipartFile file) throws Exception {
        String uploadDir = null;
        if (type == 1){
            uploadDir = "D:/gif/head/"; //用户头像
        }else if (type == 2){
            uploadDir = "D:/gif/store/"; //店铺头像
        }else if (type == 3){
            uploadDir = "D:/gif/comment/"; //评论图片
        }else if (type == 4){
            uploadDir = "D:/gif/commodity/"; //商品图片
        }
        String result = upload1(file,uploadDir);
        return result;
    }


    /**
     * 上传图片方法
     * @param file 图片文件
     * @param path 上传路径
     * @return
     * @throws Exception
     */
    public String upload1(MultipartFile file, String path) throws Exception {
        // 生成新的文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        String date = df.format(new Date());
        String realPath = path + "/" + UUID.randomUUID().toString().replace("-", "")+date+".jpg";
        File dest = new File(realPath);
        // 判断文件父目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
          }
        // 保存文件
        file.transferTo(dest);
        return dest.getName();
    }

}
