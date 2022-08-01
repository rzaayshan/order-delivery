package az.task.parceldelivery.service.impl;

import az.task.parceldelivery.model.entity.UserEntity;
import az.task.parceldelivery.model.security.UserrDetails;
import az.task.parceldelivery.repo.UserRepo;
import az.task.parceldelivery.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Get Arrays (https://www.getarrays.io/)
 * @version 1.0
 * @since 7/10/2021
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepo.findByUsername(username)
                .orElseThrow(() -> {
                    log.error("User not found in the database");
                    throw new UsernameNotFoundException("User not found in the database");
                });

        log.info("User found in the database: {}", username);

        return new UserrDetails(user.getId().toString(), user.getName(),
                user.getPassword(), user.getUsername(), user.getRoles());
    }

    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("Saving new user {} to the database", user.getName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public UserEntity getUser(String username) {
        log.info("Fetching user {}", username);
        return userRepo.findByUsername(username).orElseThrow(()->{throw new UsernameNotFoundException("User not found");});
    }

    @Override
    public List<UserEntity> getUsers() {
        log.info("Fetching all users");
        return userRepo.findAll();
    }
}
