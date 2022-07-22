package com.mec.apportfoliobackend.config;

import com.mec.apportfoliobackend.security.role.IRoleRepository;
import com.mec.apportfoliobackend.security.role.Role;
import com.mec.apportfoliobackend.security.user.IUserRepository;
import com.mec.apportfoliobackend.security.user.User;
import com.mec.apportfoliobackend.security.util.RoleName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BasicRoleAndUserSeeder implements CommandLineRunner {
    private final static Logger LOGGER = LoggerFactory.getLogger(BasicRoleAndUserSeeder.class);
    private final IRoleRepository roleRepository;
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public BasicRoleAndUserSeeder(IRoleRepository roleRepository, IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        try {
            if (roleRepository.count() == 0) {
                LOGGER.info("generate basic roles");
                Role roleUser = new Role();
                roleUser.setDescription("standart user: only read, create and update");
                roleUser.setName(RoleName.ROLE_USER);
                roleRepository.save(roleUser);
                Role roleAdmin = new Role();
                roleAdmin.setDescription("admin user: all methods available");
                roleAdmin.setName(RoleName.ROLE_ADMIN);
                roleRepository.save(roleAdmin);
                LOGGER.info("generate basic users");
                userRepository.save(buildBasicUser(roleUser));
                userRepository.save(buildBasicUser(roleAdmin));
                LOGGER.info("Basic roles and user generation complete");
            } else {
                LOGGER.info("skip role seeder");
            }
        }
        catch (Exception exception){
            LOGGER.error(exception.getMessage());
        }
    }
    private User buildBasicUser(Role role){
        User user = new User();
        if(role.getName()==RoleName.ROLE_ADMIN){
            user.setRole(role);
            user.setEmail("admin@admin");
            user.setPassword(passwordEncoder.encode("defaultAdmin#2022"));
            user.setUsername("admin");
        }
        if (role.getName()==RoleName.ROLE_USER){

            user.setUsername("user");
            user.setRole(role);
            user.setPassword(passwordEncoder.encode("defaultBasicUser#2022"));
            user.setEmail("user@user");
        }
        return user;
    }

}
