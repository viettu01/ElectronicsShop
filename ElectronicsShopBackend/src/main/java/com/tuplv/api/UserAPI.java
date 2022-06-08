package com.tuplv.api;

import com.google.gson.Gson;
import com.tuplv.dto.RoleDTO;
import com.tuplv.dto.UserDTO;
import com.tuplv.service.IRoleService;
import com.tuplv.service.IUserService;
import com.tuplv.uploadFile.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.tuplv.constant.SystemConstant.*;

@RestController
@RequestMapping(value = "/api")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private ServletContext context;

    @GetMapping(value = "/user", produces = "text/plain;charset=UTF-8")
    public String findById(@RequestParam(name = "id") long id) {

        return new Gson().toJson(userService.findById(id));
    }

    @PostMapping(value = "/user", produces = "text/plain;charset=UTF-8")
    public String edit(@RequestParam(value = "id", required = false) String id,
                       @RequestParam(value = "userName", required = false) String userName,
                       @RequestParam(value = "email", required = false) String email,
                       @RequestParam(value = "password", required = false) String password,
                       @RequestParam(value = "fullName", required = false) String fullName,
                       @RequestParam(value = "gender", required = false) String gender,
                       @RequestParam(value = "birthday", required = false) String birthday,
                       @RequestParam(value = "phone", required = false) String phone,
                       @RequestParam(value = "address", required = false) String address,
                       @RequestParam(value = "avatar", required = false) MultipartFile avatar,
                       @RequestParam(value = "jobs", required = false) String jobs,
                       @RequestParam(value = "facebook", required = false) String facebook) {
        UserDTO userDTO = new UserDTO();
        if (!id.equals("")){
            userDTO.setId(Long.valueOf(id));
        }
        userDTO.setUserName(userName);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userDTO.setFullName(fullName);
        userDTO.setGender(gender);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);
        userDTO.setJobs(jobs);
        userDTO.setFacebook(facebook);
        userDTO.setStatus(1);

        List<RoleDTO> listRoleDTO = new ArrayList<>();
        listRoleDTO.add(roleService.findByCode("USER"));
        userDTO.setListRole(listRoleDTO);

        if (birthday != null){
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                userDTO.setBirthday(dateFormat.parse(birthday));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (!id.equals("")) {
            if (!userName.equals(userService.findById(Long.valueOf(id)).getUserName())) {
                if (userService.exitsByUserName(userName) == USERNAME_EXISTS) {
                    return String.valueOf(USERNAME_EXISTS);
                }
            }
            if (!email.equals(userService.findById(Long.valueOf(id)).getEmail())) {
                if (userService.exitsByEmail(email) == EMAIL_EXISTS) {
                    return String.valueOf(EMAIL_EXISTS);
                }
            }
            if (!phone.equals(userService.findById(Long.valueOf(id)).getPhone())) {
                if (userService.exitsByPhone(phone) == PHONE_EXISTS) {
                    return String.valueOf(PHONE_EXISTS);
                }
            }
        } else {
            if (userService.exitsByUserName(userName) == USERNAME_EXISTS) {
                return String.valueOf(USERNAME_EXISTS);
            }
            if (userService.exitsByEmail(email) == EMAIL_EXISTS) {
                return String.valueOf(EMAIL_EXISTS);
            }
            if (userService.exitsByPhone(phone) == PHONE_EXISTS) {
                return String.valueOf(PHONE_EXISTS);
            }
        }
        if (avatar != null){
            userDTO.setAvatar(uploadFile.uploadFile(context, "/template/images/user", avatar));
        } else {
            userDTO.setAvatar("");
        }

        UserDTO user = userService.save(userDTO);
        if (user != null) {
            return new Gson().toJson(user);
        } else {
            return String.valueOf(SYSTEM_ERROR);
        }
    }

    @GetMapping(value = "/login", produces = "text/plain;charset=UTF-8")
    public String login(@RequestParam(value = "username", required = false) String username,
                        @RequestParam(value = "password", required = false) String password) {
        UserDTO userDTO = userService.login(username, password);
        UserDTO userError = new UserDTO();
        if (userDTO == null) {
            userError.setId(Long.valueOf(WRONG_USERNAME_OR_PASSWORD));
            return new Gson().toJson(userError);
        } else if (userDTO.getStatus() == 0) {
            userError.setId(Long.valueOf(LOCKED_ACCOUNT));
            return new Gson().toJson(userError);
        } else {
            return new Gson().toJson(userDTO);
        }
    }

//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<String> login(HttpServletRequest request, @RequestParam(value = "username", required = false) String username,
//                        @RequestParam(value = "password", required = false) String password) {
//        String result = "";
//        HttpStatus httpStatus = null;
//        try {
//            if (userService.checkLogin(user)) {
//                result = jwtService.generateTokenLogin(user.getUsername());
//                httpStatus = HttpStatus.OK;
//            } else {
//                result = "Wrong userId and password";
//                httpStatus = HttpStatus.BAD_REQUEST;
//            }
//        } catch (Exception ex) {
//            result = "Server Error";
//            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<String>(result, httpStatus);
//    }

//    @PostMapping(value = "/user", produces = "text/plain;charset=UTF-8")
//    public ResponseEntity<?> registration(@RequestBody UserDTO userDTO) {
//        userService.save(userDTO);
//
//        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
//    }
}
