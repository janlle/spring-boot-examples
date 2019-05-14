package com.leone.boot.mvc.utils.qiniu;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * <p>
 *
 * @author leone
 **/
@RestController
@RequestMapping("/api/file/upload")
public class QiNiuController {

    @Resource
    private QiNiuService qiNiuService;

    @GetMapping("/token")
    public QiNiuToken getToken(@RequestParam(required = false) String key) {
        return qiNiuService.getToken(key);
    }

    @PostMapping("/single")
    public FileVO single(@NotNull MultipartFile file) {
        return qiNiuService.upload(file);
    }

    @PostMapping("/batch")
    public FileVO batch(@NotNull MultipartFile[] files) {
        return qiNiuService.uploadBatch(files);
    }

    @PostMapping("/stream")
    public FileVO uploadByStream(InputStream inputStream) {
        return qiNiuService.uploadStream(inputStream);
    }

}