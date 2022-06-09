package org.example.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.Log;
import org.example.constant.Constants;
import org.example.dao.SystemFileDao;
import org.example.entity.SystemFile;
import org.example.entity.SystemFileExample;
import org.example.service.SystemFileService;
import org.example.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @Description:
 * @author: cangxiamy
 * @date: 2022.05.11
 */
@Slf4j
@Service("FileService")
public class SystemFileServiceImpl implements SystemFileService {

    static final int KB_CONVERSION = 1024;

    @Value("${file.upload}")
    private String fileUploadParentPath;

    @Value("${url.base}")
    private String urlBase;

    @Resource
    private SystemFileDao systemFileDao;

    @Override
    @Log(title = "上传文件", logType = "其他")
    public String upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        if(originalFilename == null){
            log.info("[error] uploaded file is null");
            return null;
        }
        String fileType = originalFilename.substring(originalFilename.lastIndexOf(Constants.DOT) + 1);
        long fileSize = file.getSize();
        // 把文件存储到磁盘
        // 定义文件唯一到标识码
        String uuid = UUID.randomUUID().toString();
        String filename = uuid + Constants.DOT + fileType;
        File uploadFile = new File(fileUploadParentPath + filename);
        if(!uploadFile.getParentFile().exists()){
            uploadFile.getParentFile().mkdirs();
        }
        // 获取文件的md5（防止重复上传同一个文件，但是数据库记录还是需要存储）
        String md5 = SecureUtil.md5(file.getInputStream());
        SystemFileExample systemFileEx = new SystemFileExample();
        systemFileEx.createCriteria().andFileMd5EqualTo(md5);
        List<SystemFile> files = systemFileDao.selectByExample(systemFileEx);
        // 获取文件的url
        String url = "";
        if(files.size() > 0){
            url = files.get(0).getFileUrl();
        }
        else{
            // 磁盘不存在该文件时才上传
            file.transferTo(uploadFile);
            // 设置文件的url
            url = urlBase + Constants.SEPARATOR + filename;
        }

        // 把文件存储到数据库中
        SystemFile systemFile = new SystemFile();
        systemFile.setFileName(originalFilename);
        systemFile.setFileType(fileType);
        systemFile.setFileSize(fileSize / KB_CONVERSION); // 存储的单位转换为kb
        systemFile.setFileMd5(md5);
        Timestamp timeStamp = TimeUtil.getTimeStamp();
        systemFile.setUpdatedTime(timeStamp);


        systemFile.setFileUrl(url);

        systemFileDao.insertSelective(systemFile);

        return url;
    }

    @Override
    @Log(title = "下载文件", logType = "其他")
    public void download(String filename, HttpServletResponse response) throws IOException {
        // 根据文件唯一标识码获取文件
        File file = new File(fileUploadParentPath + filename);
        // 设置输出流的格式
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(filename, Constants.ENCODING));
        response.setContentType(Constants.STREAM_CONTENT_TYPE);
        // 读取文件的字节流
        os.write(FileUtil.readBytes(file));
        os.flush();
        os.close();
    }
}
