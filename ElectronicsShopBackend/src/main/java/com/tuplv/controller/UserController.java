package com.tuplv.controller;

import com.tuplv.converter.RoleConverter;
import com.tuplv.dto.RoleDTO;
import com.tuplv.dto.UserDTO;
import com.tuplv.repository.RoleRepository;
import com.tuplv.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleConverter roleConverter;

    @RequestMapping(value = "/dang-ky", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("user", new UserDTO());
        return mav;
    }

    @RequestMapping(value = "/dang-ky", method = RequestMethod.POST)
    public String registration(@ModelAttribute("register") UserDTO userDTO) {
        List<RoleDTO> listRoleDTO = new ArrayList<>();
        listRoleDTO.add(roleConverter.toDTO(roleRepository.findOne(Long.valueOf(2))));
        userDTO.setListRole(listRoleDTO);
        userDTO.setStatus(1);
        userService.save(userDTO);

        return "redirect:/dang-nhap";
    }
}
