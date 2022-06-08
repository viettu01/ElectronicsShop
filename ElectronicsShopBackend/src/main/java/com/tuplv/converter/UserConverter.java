package com.tuplv.converter;

import com.tuplv.dto.OrderDTO;
import com.tuplv.dto.RoleDTO;
import com.tuplv.dto.UserDTO;
import com.tuplv.entity.OrderEntity;
import com.tuplv.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    @Autowired
    RoleConverter roleConverter;

    @Autowired
    OrderConverter orderConverter;

    public UserDTO toDTO(UserEntity entity) {
        UserDTO result = new UserDTO();
        result.setId(entity.getId());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());

        result.setUserName(entity.getUserName());
        result.setPassword(entity.getPassword());
        result.setFullName(entity.getFullName());
        result.setGender(entity.getGender());
        result.setBirthday(entity.getBirthday());
        result.setPhone(entity.getPhone());
        result.setAddress(entity.getAddress());
        result.setEmail(entity.getEmail());
        result.setAvatar(entity.getAvatar());
        result.setJobs(entity.getJobs());
        result.setFacebook(entity.getFacebook());
        result.setStatus(entity.getStatus());
        result.setCartId(entity.getCart().getId());

        List<RoleDTO> listRoleDTO = new ArrayList<>();
        for (int i = 0; i < entity.getListRole().size(); i++) {
            listRoleDTO.add(roleConverter.toDTO(entity.getListRole().get(i)));
        }
        result.setListRole(listRoleDTO);

        List<OrderDTO> listOrderDTO = new ArrayList<>();
        for (OrderEntity orderEntity: entity.getOrders()) {
            listOrderDTO.add(orderConverter.toDTO(orderEntity));
        }
        result.setOrders(listOrderDTO);

        return result;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity result = new UserEntity();

        result.setUserName(dto.getUserName());
        result.setPassword(dto.getPassword());
        result.setFullName(dto.getFullName());
        result.setGender(dto.getGender());
        result.setBirthday(dto.getBirthday());
        result.setPhone(dto.getPhone());
        result.setAddress(dto.getAddress());
        result.setEmail(dto.getEmail());
        result.setAvatar(dto.getAvatar());
        result.setJobs(dto.getJobs());
        result.setFacebook(dto.getFacebook());
        result.setStatus(dto.getStatus());

        return result;
    }

    //Update
    public UserEntity toEntity(UserEntity userEntity, UserDTO userDTO) {
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setFullName(userDTO.getFullName());
        userEntity.setGender(userDTO.getGender());
        userEntity.setBirthday(userDTO.getBirthday());
        userEntity.setPhone(userDTO.getPhone());
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setAvatar(userDTO.getAvatar());
        userEntity.setJobs(userDTO.getJobs());
        userEntity.setFacebook(userDTO.getFacebook());
        userEntity.setStatus(userDTO.getStatus());

        return userEntity;
    }
}
