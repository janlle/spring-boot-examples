package com.andy.pay.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 二维码生成及导出工具类
 * @author: Leone
 * @since: 2018-06-17 12:34
 **/
@Slf4j
public class QRCodeUtil {

    /**
     * 生成二维码
     * @author: Leone
     * @since: 2018-06-17 12:40
     * @params: [qrCodePath, content, filename]
     * @return: void
     **/
    public static void createQRCode(String qrCodePath, String content, String filename) {

        int width = 300;
        int height = 300;
        String format = "png";
        Map<EncodeHintType, Object> hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN, 1);
        try {
            File qrCodeFile = new File(qrCodePath);
            if (!qrCodeFile.exists()) {
                // 创建二维码生成目录
                qrCodeFile.mkdirs();
            }
            File file = new File(qrCodePath + filename + ".png");
            if (!file.exists()) {
                BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hashMap);
                Path path = file.toPath();
                MatrixToImageWriter.writeToPath(bitMatrix, format, path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info(" createQRCode error ");
        }
    }

    /**
     * 生成二维码并响应到浏览器
     * @author: Leone
     * @since: 2018-06-17 12:40
     * @params: [content, response]
     * @return: void
     **/
    public static void createQRCode(String content, HttpServletResponse response) {
        int width = 300;
        int height = 300;
        String format = "png";
        Map<EncodeHintType, Object> hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hashMap.put(EncodeHintType.MARGIN, 1);
        try {
            response.setHeader("Cache-control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("content-type", "image/png");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hashMap);
            BufferedImage img = MatrixToImageWriter.toBufferedImage(bitMatrix);
            ImageIO.write(img, format, response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            log.info("createQRCode error");
        }
    }

    /**
     * 将文件打包成zip
     * @param qrCodePath
     * @param zipFilePath
     * @param fileName
     */
    public static void fileToZip(String qrCodePath, String zipFilePath, String fileName) {
        File sourceFile = new File(qrCodePath);
        FileInputStream fis;
        BufferedInputStream bis = null;
        FileOutputStream fos;
        ZipOutputStream zos = null;
        try {
            File zipFile = new File(zipFilePath + File.separator + fileName);
            if (zipFile.exists()) {
                log.info(" 当前目录已存在该zip文件 ");
            } else {
                File[] sourceFiles = sourceFile.listFiles();
                if (null == sourceFiles || sourceFiles.length < 1) {
                    log.info(" 当前目录不存在待压缩文件 ");
                } else {
                    fos = new FileOutputStream(zipFile);
                    zos = new ZipOutputStream(new BufferedOutputStream(fos));
                    byte[] bufs = new byte[1024 * 10];
                    for (int i = 0; i < sourceFiles.length; i++) {
                        //创建ZIP实体，并添加进压缩包
                        ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
                        zos.putNextEntry(zipEntry);
                        //读取待压缩的文件并写进压缩包里
                        fis = new FileInputStream(sourceFiles[i]);
                        bis = new BufferedInputStream(fis, 1024 * 5);
                        int read = 0;
                        while ((read = bis.read(bufs, 0, 1024 * 5)) != -1) {
                            zos.write(bufs, 0, read);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭流
            try {
                if (null != bis) bis.close();
                if (null != zos) zos.close();
            } catch (IOException e) {
                log.info(" fileToZip close error ");
            }
        }
    }

}
