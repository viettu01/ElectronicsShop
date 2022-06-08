package com.tuplv.converter;

import com.tuplv.dto.RoleDTO;
import com.tuplv.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDTO toDTO(RoleEntity entity) {
        RoleDTO result = new RoleDTO();
        result.setId(entity.getId());
        result.setName(entity.getName());
        result.setCode(entity.getCode());
        result.setCreatedAt(entity.getCreatedAt());
        result.setCreatedBy(entity.getCreatedBy());
        result.setUpdatedAt(entity.getUpdatedAt());
        result.setUpdatedBy(entity.getUpdatedBy());
        return result;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity result = new RoleEntity();
        result.setName(dto.getName());
        result.setCode(dto.getCode());
        return result;
    }

    public RoleEntity toEntity(RoleEntity roleEntity, RoleDTO roleDTO) {
        roleEntity.setName(roleDTO.getName());
        roleEntity.setCode(roleDTO.getCode());
        return roleEntity;
    }
}
