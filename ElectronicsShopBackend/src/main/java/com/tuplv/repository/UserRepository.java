package com.tuplv.repository;

import com.tuplv.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findOneByUserNameAndStatus(String name, Integer status);
    UserEntity findOneByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
