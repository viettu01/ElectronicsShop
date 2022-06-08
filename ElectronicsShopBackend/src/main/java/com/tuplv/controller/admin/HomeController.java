package com.tuplv.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

    @RequestMapping(value = {"/admin", "/admin/trang-chu"}, method = RequestMethod.GET)
    public ModelAndView homePage() {
        return new ModelAndView("admin/home");
    }
}