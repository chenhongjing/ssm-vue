package org.example.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 文件上传等
 * @author: cangxiamy
 * @date: 2022.05.11
 */
public interface SystemFileService {
    String upload(MultipartFile file) throws IOException;

    void download(String filename, HttpServletResponse response) throws IOException;
}
