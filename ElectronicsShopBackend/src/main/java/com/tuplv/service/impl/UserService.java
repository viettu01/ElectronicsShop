package com.tuplv.service.impl;

import com.tuplv.converter.UserConverter;
import com.tuplv.dto.UserDTO;
import com.tuplv.entity.RoleEntity;
import com.tuplv.entity.UserEntity;
import com.tuplv.repository.RoleRepository;
import com.tuplv.repository.UserRepository;
import com.tuplv.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.tuplv.constant.SystemConstant.*;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ServletContext context;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> models = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll(pageable).getContent();
        for (UserEntity item : entities) {
            UserDTO dto = userConverter.toDTO(item);
            models.add(dto);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) userRepository.count();
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity userEntity = userRepository.findOne(id);
        return userConverter.toDTO(userEntity);
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        List<RoleEntity> roleEntities = new ArrayList<>();
        for (int i = 0; i < dto.getListRole().size(); i++) {
            roleEntities.add(roleRepository.findOne(dto.getListRole().get(i).getId()));
        }

        if (dto.getId() != null) {
            UserEntity oldUser = userRepository.findOne(dto.getId());
            oldUser.setListRole(roleEntities);
            if (oldUser.getPassword().equals(dto.getPassword())) {
                dto.setPassword(oldUser.getPassword());
            } else {
                dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            }
            if (dto.getAvatar() == null) {
                dto.setAvatar(oldUser.getAvatar());
            } else {
                File file = new File(context.getRealPath("/template/images/user") + File.separator + oldUser.getAvatar());
                if (file.delete()) {
                    System.out.println("Xóa thành công");
                } else {
                    System.out.println("Xóa thất bại");
                }
            }
            userEntity = userConverter.toEntity(oldUser, dto);
        } else {
            userEntity = userConverter.toEntity(dto);
            userEntity.setListRole(roleEntities);
            userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        }
        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public int exitsByUserName(String userName) {
        if (userRepository.existsByUserName(userName))
            return USERNAME_EXISTS;
        return 0;
    }

    @Override
    public int exitsByEmail(String email) {
        if (userRepository.existsByEmail(email))
            return EMAIL_EXISTS;
        return 0;
    }

    @Override
    public int exitsByPhone(String phone) {
        if (userRepository.existsByPhone(phone))
            return PHONE_EXISTS;
        return 0;
    }

    @Override
    public UserDTO login(String userName, String password) {
        UserEntity entity = userRepository.findOneByUserName(userName);
        if (entity != null) {
            if (bCryptPasswordEncoder.matches(password, entity.getPassword())) {
                return userConverter.toDTO(entity);
            }
        }
        return null;
    }
}
