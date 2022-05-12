package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.SystemFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 处理文件上传等
 * @author: cangxiamy
 * @date: 2022.05.11
 */
@RestController
@Slf4j
public class SystemFileController {

    @Resource
    private SystemFileService systemFileService;

    @PostMapping("file")
    public ResponseEntity<String> upload(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        if(file == null){
            log.info("[error]file is null");
            return ResponseEntity.status(400).build();
        }
        String url = systemFileService.upload(file);
        if(url == null){
            log.info("[error]file error");
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.ok(url);
    }

    @GetMapping("file/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        systemFileService.download(filename, response);
        return;
    }
}
