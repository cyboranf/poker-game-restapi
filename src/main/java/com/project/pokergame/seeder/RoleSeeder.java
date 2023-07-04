package com.project.pokergame.seeder;

import com.project.pokergame.model.enumerated.Role;
import com.project.pokergame.service.RoleService;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Profile("seed")
public class RoleSeeder implements DatabaseSeeder {
    private final RoleService roleService;

    public RoleSeeder(RoleService roleService) {
        this.roleService = roleService;
    }


    /**
     * @param args incoming main method arguments
     * @throws Exception
     */

    /**
     * Role:
     *   "1": "USER",
     *   "2": "MOD",
     *   "3": "ADMIN"
     */
    @Override
    public void run(String... args) throws Exception {
        for (Role role : Role.values()) {
            com.project.pokergame.model.Role roleEntity = new com.project.pokergame.model.Role();
            roleEntity.setName(role);
            roleService.saveRole(roleEntity);
        }
    }
}
