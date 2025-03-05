package com.aptech.springsecurityl6;

import com.aptech.springsecurityl6.entity.role.Role;
import com.aptech.springsecurityl6.entity.role.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecurityL6ApplicationTests {
    @Autowired
    private RoleRepository roleRepository;

    @Test
    void contextLoads() {
        Role role = Role.builder().name("ROLE_ADMIN").build();
        roleRepository.save(role);
    }

}
