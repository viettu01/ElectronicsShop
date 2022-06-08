package com.tuplv.controller.admin;

import com.tuplv.dto.RoleDTO;
import com.tuplv.dto.UserDTO;
import com.tuplv.service.IRoleService;
import com.tuplv.service.IUserService;
import com.tuplv.uploadFile.UploadFile;
import com.tuplv.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.tuplv.constant.SystemConstant.*;

@Controller(value = "userControllerOfAdmin")
@RequestMapping(value = "/admin/user")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    MessageUtil messageUtil;

    @Autowired
    ServletContext context;

    @Autowired
    UploadFile uploadFile;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView showList(@RequestParam("page") int page, @RequestParam("limit") int limit,
                                 HttpServletRequest request) {
        UserDTO model = new UserDTO();
        model.setPage(page);
        model.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/user/list");
        Pageable pageable = new PageRequest(page - 1, limit);
        model.setListResult(userService.findAll(pageable));
        model.setTotalItem(userService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        mav.addObject("model", model);
        if (request.getParameter("message") != null) {
            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
            mav.addObject("message", message.get("message"));
            mav.addObject("alert", message.get("alert"));
        }
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage(@RequestParam(value = "id", required = false) Long id,
                                 UserDTO userUpdate,
                                 HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/user/edit");
        UserDTO model = new UserDTO();
        if (id != null) {
            model = userService.findById(id);
            if (userUpdate != null && (userUpdate.getUserName() != null || userUpdate.getEmail() != null || userUpdate.getPhone() != null)) {
                model = userUpdate;
            }
        }
//        if (id != null) {
//            model = userService.findById(id);
//        }

//        if (request.getParameter("message") != null) {
//            Map<String, String> message = messageUtil.getMessage(request.getParameter("message"));
//            mav.addObject("message", message.get("message"));
//            mav.addObject("alert", message.get("alert"));
//        }

        mav.addObject("message", request.getAttribute("message"));
        mav.addObject("alert", request.getAttribute("alert"));
        mav.addObject("roles", roleService.findAll(null));
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "userName") String userName,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "password") String password,
                             @RequestParam(value = "roleId") Long[] roleId,
                             @RequestParam(value = "fullName", required = false) String fullName,
                             @RequestParam(value = "gender", required = false) String gender,
                             @RequestParam(value = "birthday", required = false) String birthday,
                             @RequestParam(value = "phone", required = false) String phone,
                             @RequestParam(value = "address", required = false) String address,
                             @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                             @RequestParam(value = "jobs", required = false) String jobs,
                             @RequestParam(value = "facebook", required = false) String facebook,
                             @RequestParam(value = "status") int status,
                             HttpServletRequest request) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setUserName(userName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);

        List<RoleDTO> listRoleDTO = new ArrayList<>();
        for (Long role : roleId) {
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId(role);
            listRoleDTO.add(roleDTO);
        }
        userDTO.setListRole(listRoleDTO);

        userDTO.setFullName(fullName);
        userDTO.setGender(gender);

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            userDTO.setBirthday(dateFormat.parse(birthday));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setJobs(jobs);
        userDTO.setFacebook(facebook);
        userDTO.setStatus(status);

        if (id != null) {
            if (!userName.equals(userService.findById(id).getUserName())) {
                if (userService.exitsByUserName(userName) == USERNAME_EXISTS) {
                    request.setAttribute("alert", "danger");
                    request.setAttribute("message", "Tên đăng nhập '" + userName + "' đã được sử dụng");
                    return editPage(id, userDTO, request);
                }
            }
            if (!email.equals(userService.findById(id).getEmail())) {
                if (userService.exitsByEmail(email) == EMAIL_EXISTS) {
                    request.setAttribute("alert", "danger");
                    request.setAttribute("message", "Email '" + email + "' đã được sử dụng");
                    return editPage(id, userDTO, request);
                }
            }
            if (!phone.equals(userService.findById(id).getPhone())) {
                if (userService.exitsByPhone(phone) == PHONE_EXISTS) {
                    request.setAttribute("alert", "danger");
                    request.setAttribute("message", "Số điện thoại '" + phone + "' đã được sử dụng");
                    return editPage(id, userDTO, request);
                }
            }
        } else {
            if (userService.exitsByUserName(userName) == USERNAME_EXISTS) {
                request.setAttribute("alert", "danger");
                request.setAttribute("message", "Tên đăng nhập '" + userName + "' đã được sử dụng");
                return editPage(id, userDTO, request);
            }
            if (userService.exitsByEmail(email) == EMAIL_EXISTS) {
                request.setAttribute("alert", "danger");
                request.setAttribute("message", "Email '" + email + "' đã được sử dụng");
                return editPage(id, userDTO, request);
            }
            if (userService.exitsByPhone(phone) == PHONE_EXISTS) {
                request.setAttribute("alert", "danger");
                request.setAttribute("message", "Số điện thoại '" + phone + "' đã được sử dụng");
                return editPage(id, userDTO, request);
            }
        }

        userDTO.setAvatar(uploadFile.uploadFile(context, "/template/images/user", avatar));

        UserDTO user = userService.save(userDTO);
        if (user != null) {
            request.setAttribute("alert", "success");
            if (id != null) {
                request.setAttribute("message", "Cập nhật thành công");
            } else {
                request.setAttribute("message", "Thêm mới thành công");
            }
            return editPage(user.getId(), userDTO, request);
        } else {
            request.setAttribute("message", "Lỗi hệ thống");
            request.setAttribute("alert", "danger");
            return showList(1, 10, request);
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ModelAndView detailPage(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("admin/user/detail");
        mav.addObject("model", userService.findById(id));
        return mav;
    }
}
