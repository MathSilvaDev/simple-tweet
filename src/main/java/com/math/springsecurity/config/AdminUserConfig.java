package com.math.springsecurity.config;

import com.math.springsecurity.role.entity.Role;
import com.math.springsecurity.role.repository.RoleRepository;
import com.math.springsecurity.user.entity.User;
import com.math.springsecurity.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

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
        Role roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name())
                .orElseThrow(() -> new RuntimeException("ERRO NA CLASSE AdminUserConfig"));

        Optional<User> userAdmin = userRepository.findByUsername("ADMIN");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.print("Admin ja existe");
                },
                () -> {
                    User user = new User(
                            "ADMIN",
                            passwordEncoder.encode("1352"),
                            Set.of(roleAdmin)
                    );
                    userRepository.save(user);
                }
        );
    }
}
