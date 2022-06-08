package com.tuplv.service;

import com.tuplv.dto.RoleDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IRoleService {
    List<RoleDTO> findAll(Pageable pageable);
    Map<String, String> findAll();
    int getTotalItem();
    RoleDTO findById(Long id);
    RoleDTO findByCode(String code);
    RoleDTO save(RoleDTO dto);
    void delete(long[] ids);
}
