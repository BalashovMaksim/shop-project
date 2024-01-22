package com.maksimbalashov.spring.controller;

import com.maksimbalashov.spring.dto.BucketDTO;
import com.maksimbalashov.spring.service.BucketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class BucketController {
    private final BucketService bucketService;


    @GetMapping("/bucket")
    public String aboutBucket(Model model, Principal principal){
        if(principal==null){
            model.addAttribute("bucket", new BucketDTO());
        }
        else {
            BucketDTO bucketDTO = bucketService.getBucketByUser(principal.getName());
            model.addAttribute("bucket", bucketDTO);
        }

        return "bucket";
    }
    @PostMapping("/bucket")
    public String commitBucket(Principal principal){
        if(principal!=null){
            bucketService.commitBucketToOrder(principal.getName());
        }
        return "redirect:/bucket";
    }
}
