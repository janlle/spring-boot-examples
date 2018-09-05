package com.andy.mvc.utils.qiniu;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * <p>
 *
 * @author Leone
 **/
@RestController
@Api(tags = "七牛云上传图片")
@RequestMapping("/api/file/upload")
public class QiNiuController {

    @Resource
    private QiNiuService qiNiuService;

    @GetMapping("/token")
    @ApiOperation("前端上传: 返回Token")
    public QiNiuToken getToken(@RequestParam(required = false) String key) {
        return qiNiuService.getToken(key);
    }

    @PostMapping("/single")
    @ApiOperation("上传单文件")
    public FileVO single(@NotNull MultipartFile file) {
        return qiNiuService.upload(file);
    }

    @PostMapping("/batch")
    @ApiOperation("上传多文件")
    public FileVO batch(MultipartFile[] files) {
        return qiNiuService.uploadBatch(files);
    }

    @PostMapping("/stream")
    @ApiOperation("流上传")
    public FileVO uploadByStream(InputStream inputStream) {
        return qiNiuService.uploadStream(inputStream);
    }

}