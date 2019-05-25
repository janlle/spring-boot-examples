package com.leone.boot.mvc.sign;

import com.leone.boot.common.entity.User;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * @author leone
 * @since 2019-05-24
 **/
@RestController
@RequestMapping("/api/sign")
public class SignController {

    @Signature
    @PostMapping("/test")
    public User test(@RequestBody SignatureBean<User> user) {
        return user.getData();
    }

}
