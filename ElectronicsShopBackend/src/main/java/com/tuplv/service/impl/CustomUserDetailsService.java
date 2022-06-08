package com.tuplv.service.impl;

import com.tuplv.dto.MyUser;
import com.tuplv.entity.RoleEntity;
import com.tuplv.entity.UserEntity;
import com.tuplv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.tuplv.constant.SystemConstant.ACTIVE_STATUS;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByUserNameAndStatus(username, ACTIVE_STATUS);

        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : userEntity.getListRole()) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        //put thông tin vào security duy trì thông tin khi user login vào hệ thống
        MyUser myUser = new MyUser(userEntity.getUserName(), userEntity.getPassword(),
                true, true, true, true, authorities);
        myUser.setFullName(userEntity.getFullName());

        return myUser;
    }
}
