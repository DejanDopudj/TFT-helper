package com.ftn.sbnz.service.service;

import com.ftn.sbnz.service.model.Role;
import com.ftn.sbnz.service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findByName(String name) {
        return this.roleRepository.findByName(name);
    }
}
