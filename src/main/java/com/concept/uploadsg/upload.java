package com.concept.uploadsg;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("Uploadw")
public class upload {
    @Autowired
    private HttpServletRequest request;

    private static Map<String, UploadFile> uploadSessionMap = new HashMap<>();//

    public boolean isEmpty(Object s) {
        if (null == s || "".equals(s)) {
            return true;
        }
        return false;
    }

    private UploadFile findFileInSession(final String tmpUploadId, final FileItem item, String fileScope,
            final long totalSize, final String targetFileId) { // 这个函数是构造了一个UploadFile文件 数据库存的是版本信息等
                                                               // 而别人访问就是存放的地址
        String url = null;
        String service = null;
        InputStream inputstr = upload.class.getResourceAsStream("/upload.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputstr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        service = properties.getProperty("service");
        String sessionKey = tmpUploadId + "-" + item.getName();
        UploadFile file = uploadSessionMap.get(sessionKey);

        if (isEmpty(fileScope)) {
            fileScope = item.getContentType() + "s";
        }
        if (null == file) {
            fileScope = fileScope + "/";
            // 判断是否是图片，根据类型创建对应的文件，如果有指定目标ID，则
            // 设置文件的对象的为对应的ID值，这个值就是实际的文件名。在服务
            // 器端采用统一的ID规格来命名文件不会使用原文件名，原文件名保存
            // 在文件信息库中。
            boolean isImage = !isEmpty(item.getContentType()) && item.getContentType().startsWith("image/"); // 判斷是否是文件
            file = isImage ? new Picture() : new UploadFile(); // 是就调用UploadFile
            if (null != targetFileId) // 文件的ID不为空 就设置ID
                file.setId(targetFileId);
            String dir = url + fileScope; // 前端要获取的地址
            String[] fileNameArr = item.getName().split("[.]");// 得到的name 以.分割
            String extension = (1 < fileNameArr.length ? "." + fileNameArr[fileNameArr.length - 1] : "");// 扩展名
            File directory = new File(service + fileScope); // new一个要存放的路径文件目录
            directory.mkdirs();// 创建这个目录

            file.setType(item.getContentType());
            file.setName(item.getName());
            file.setRealName(file.getId() + extension);
            file.setSize(totalSize);
            file.setUrl(dir + file.getId() + extension);
            file.setDeleteUrl(file.getUrl());
            file.setDeleteType("DELETE");
            file.setExtension(extension);
            file.setAbsolutePath(service + fileScope + file.getId() + extension); // 这个是文件的绝对路径
                                                                                  // 存放到服务器的绝对路径
            if (file instanceof Picture) { // 判断是否是picture
                Picture p = (Picture) file;
                p.setThumbnailUrl(dir + file.getId() + "_s" + extension);
                p.setMiddleThumbnaiUrl(dir + file.getId() + "_m" + extension);
                p.setLargeThumbnaiUrl(dir + file.getId() + "_l" + extension);
                file = p;
            }
            uploadSessionMap.put(sessionKey, file);
        }
        return file;
    }

    @RequestMapping(value = "sui", method = RequestMethod.POST)
    public ModelAndView Fiload(@RequestParam("files[]")
    MultipartFile[] files, HttpServletResponse response, HttpServletRequest request, ModelAndView mv) {
        // jdbc.properties
        response.addHeader("Content-Type", "application/json");// 以json方式
        response.addHeader("X-Content-Type-Options", "nosniff"); // 是一种对请求编码的限制
        response.addHeader("Access-Control-Allow-Origin", "*");// 响应头
        response.addHeader("Access-Control-Allow-Methods", "OPTIONS, HEAD, GET, POST, PUT, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, Content-Range, Content-Disposition");
        List<UploadFile> fileList = new ArrayList<>();
        for (MultipartFile item : files) {
            if (item instanceof CommonsMultipartFile) {// 是一种普通文件类型
                CommonsMultipartFile cmf = (CommonsMultipartFile) item;
                UploadFile file = findFileInSession(new ObjectId().toString(), cmf.getFileItem(), "",
                        item.getSize(), new ObjectId().toString());
                // 调用上面的方法
                try {
                    if (new FileHandler().create(file, item.getInputStream())) {
                        fileList.add(file);

                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

            // 1. 保存图片到服务器硬盘上
            // 2，保存图片信息到数据库中
            // 3.封装json 格式的files对象供前端页面接受
        }
        // filess="";
        try {
            // mv.addObject("responseText",
            // "{\"files\": [{\"url\":\"http://localhost:8084/images/public_page/head_left_03.png\"}]}");
            mv.addObject("responseText", "{\"files\": " + new JSONArray(fileList).toString() + "}"); // 把这个json串放到新建的模板
            // String filePath ="C:\\Users\\admin\\Desktop\\nginx-1.12.1\\images\\"+new
            // ObjectId()+"."+file.getContentType().split("/")[1];
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // //"{\"files\": "
        // // + new JSONArray(files).toString() + "}"
        // private boolean saveFile(UploadFile file, InputStream in) {
        //
        // // if (file instanceof Picture) {
        // // imgHandler.create(file, in);
        // // } else {
        // // fileHandler.create(file, in);
        // // }
        //
        // return fileHandler.create(file, in);
        mv.setViewName("Ap");

        return mv;
    }
}
