package com.itheima.controller;

import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
//    jsr250的注解，只有USER权限的用户才能访问产品列表界面
//    @RolesAllowed("USER")

//    secured注解，只有USER权限的用户才能访问产品列表界面
    @Secured("ROLE_USER")

//    支持表达式注解，只有USER权限的用户才能访问产品列表界面
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> pl =  productService.findAll();
        mv.addObject("productList", pl);
        mv.setViewName("product-list1");
        return mv;
    }

    @RequestMapping("/save.do")
//    @PreAuthorize("authentication.principal.username='root' or hasAuthority('USER')")
    public String save(Product product) throws Exception {
        productService.save(product);
//        添加数据后刷新数据
        return "redirect:findAll.do";
    }
}
