package com.project.pokergame.service;


import com.project.pokergame.model.Role;
import com.project.pokergame.repository.RoleRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role saveRole(Role role){
        return roleRepository.save(role);
    }
}
