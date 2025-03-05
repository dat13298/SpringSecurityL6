package com.aptech.springsecurityl6.entity.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public void save(Role role) {
        roleRepository.save(role);
    }
}
