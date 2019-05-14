package com.leone.boot.mvc.web.controller;

import com.leone.boot.mvc.utils.Sign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * @author leone
 * @since 2018-11-29
 **/
@Slf4j
@RestController
public class FaceIdController {


    public void addFace() {

    }


    public static void main(String[] args) throws Exception {
        String result = Sign.appSign(1256428001, "", "", "tencentyun", 1000);
        System.out.println(result);
    }


}
