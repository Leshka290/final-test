package ru.liga.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.liga.dto.RegisterDto;
import ru.liga.dto.Role;
import ru.liga.dto.UserDetailsCustom;
import ru.liga.entity.User;
import ru.liga.exception.IncorrectUsernameException;
import ru.liga.repository.UserRepository;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDetailsManagerCustom implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByEmailIgnoreCase(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new UserDetailsCustom(user);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public void createUser(RegisterDto register) {
        if (userRepository.findUserByEmailIgnoreCase(register.getUsername()).isPresent()) {
            throw new IncorrectUsernameException();
        }

        User user = new User();
        user.setEmail(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setRole(Role.CLIENT);
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        log.info("Сохранение в БД user");
        log.info(String.valueOf(user));
        userRepository.save(user);
    }
}
