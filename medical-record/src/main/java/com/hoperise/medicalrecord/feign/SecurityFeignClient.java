package com.hoperise.medicalrecord.feign;

import com.hoperise.medicalrecord.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "security")
public interface SecurityFeignClient {

    @GetMapping("/user/{userId}")
    UserResponse getUserById(@PathVariable("userId") Long userId);
}