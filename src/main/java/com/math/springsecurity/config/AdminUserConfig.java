package com.math.springsecurity.config;

import com.math.springsecurity.role.entity.Role;
import com.math.springsecurity.role.repository.RoleRepository;
import com.math.springsecurity.user.entity.User;
import com.math.springsecurity.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class AdminUserConfig implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AdminUserConfig.class);

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role adminRole  = roleRepository.findByName(Role.Values.ADMIN.name())
                .orElseThrow(() -> new RuntimeException("Admin Role NotFound"));

        if (userRepository.existsByUsername("ADMIN")) {
            log.info("User ADMIN Already exists");
            return;
        }

        User admin = new User(
                "ADMIN",
                passwordEncoder.encode("1352"),
                Set.of(adminRole)
        );

        userRepository.save(admin);
        log.info("created ADMIN");
    }
}
