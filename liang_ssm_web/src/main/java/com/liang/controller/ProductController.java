package com.liang.controller;

import com.liang.domain.Product;
import com.liang.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

/**
 * @author liang
 * @create 2020/2/24 16:39
 */
@Controller
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //删除商品操作
    @RequestMapping("/delete.do")
    public @ResponseBody String delete(String ids){
        System.out.println("liansaguciagc"+ids);
        String[] d=ids.split(",");
        productService.delete(d);
        return "ok";
    }

    //保存商品信息
    @RequestMapping("/save.do")
    public String save(Product product){
        System.out.println(product);
        UUID uuid = UUID.randomUUID();
        uuid.toString().replace("-","");
        System.out.println(uuid);
        product.setId(uuid.toString());
        productService.save(product);
        return "redirect:findAll.do";
    }

    //查询所有商品信息
    @RequestMapping("/findAll.do")
    //@RolesAllowed("ADMIN")只有ADMIN角色才可以访问这个方法
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        //调用service层方法,获取查询到的所有产品信息
        List<Product> list = productService.findAll();
        //把所有商品信息存到request域对象里面
        mv.addObject("productList",list);
        //成功后跳转到页面
        mv.setViewName("product-list1");
        return mv;
    }
}
