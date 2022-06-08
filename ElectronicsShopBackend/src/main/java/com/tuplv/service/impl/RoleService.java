package com.tuplv.service.impl;

import com.tuplv.converter.RoleConverter;
import com.tuplv.dto.RoleDTO;
import com.tuplv.entity.RoleEntity;
import com.tuplv.repository.RoleRepository;
import com.tuplv.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleConverter roleConverter;

    @Override
    public List<RoleDTO> findAll(Pageable pageable) {
        List<RoleDTO> models = new ArrayList<>();
        List<RoleEntity> entities = roleRepository.findAll(pageable).getContent();
        for (RoleEntity item : entities) {
            RoleDTO dto = roleConverter.toDTO(item);
            models.add(dto);
        }
        return models;
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> result = new HashMap<>();
        List<RoleEntity> entities = roleRepository.findAll();
        for (RoleEntity item : entities) {
            result.put(String.valueOf(item.getId()), item.getName());
        }
        return result;
    }

    @Override
    public int getTotalItem() {
        return 0;
    }

    @Override
    public RoleDTO findById(Long id) {
        return null;
    }

    @Override
    public RoleDTO findByCode(String code) {
        return roleConverter.toDTO(roleRepository.findOneByCode(code));
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        return null;
    }

    @Override
    public void delete(long[] ids) {

    }
}
