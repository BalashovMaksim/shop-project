package com.maksimbalashov.spring.controller;

import com.maksimbalashov.spring.dto.ProductDTO;
import com.maksimbalashov.spring.service.ProductService;
import com.maksimbalashov.spring.service.SessionObjectHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    private final SessionObjectHolder sessionObjectHolder;


    @GetMapping
    public String list(Model model){
        sessionObjectHolder.addClick();
        List<ProductDTO> list = productService.getAll();
        model.addAttribute("products",list);
        return "products";
    }

    @GetMapping("/{id}/bucket")
    public String addBucket(@PathVariable Long id, Principal principal){
        sessionObjectHolder.addClick();
        if(principal == null){
            return "redirect:/products";
        }
        productService.addToUserBucket(id,principal.getName());
        return "redirect:/products";
    }

}
