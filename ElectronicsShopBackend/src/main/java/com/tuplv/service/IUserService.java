package com.tuplv.service;

import com.tuplv.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    List<UserDTO> findAll(Pageable pageable);
    int getTotalItem();
    UserDTO findById(Long id);
    UserDTO save(UserDTO dto);
    int exitsByUserName(String userName);
    int exitsByEmail(String email);
    int exitsByPhone(String phone);
    UserDTO login(String userName, String password);
}
