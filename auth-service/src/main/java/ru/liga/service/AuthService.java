package ru.liga.service;

import ru.liga.dto.RegisterDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto register);
}