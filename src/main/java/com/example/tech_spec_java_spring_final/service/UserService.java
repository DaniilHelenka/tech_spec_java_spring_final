package com.example.tech_spec_java_spring_final.service;

import com.example.tech_spec_java_spring_final.entity.User;
import com.example.tech_spec_java_spring_final.exception.UserNotFoundException;
import com.example.tech_spec_java_spring_final.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Validated
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@Valid User user) {
        user.setCreatedAt(LocalDateTime.now());
        logger.info("Создание пользователя: email={}", user.getEmail());
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            logger.error("Ошибка при создании пользователя: email={}", user.getEmail(), e);
            throw e;
        }
    }

    public User getUserById(Long userId) {
        logger.info("Получение пользователя: id={}", userId);
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    public List<User> getAllUsers() {
        logger.info("Получение списка всех пользователей");
        return userRepository.findAll();
    }

    public User updateUser(Long userId, @Valid User updatedUser) {
        logger.info("Обновление пользователя: id={}", userId);
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        try {
            return userRepository.save(existingUser);
        } catch (Exception e) {
            logger.error("Ошибка при обновлении пользователя: id={}", userId, e);
            throw e;
        }
    }

    public void deleteUser(Long userId) {
        logger.info("Удаление пользователя: id={}", userId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            logger.error("Ошибка при удалении пользователя: id={}", userId, e);
            throw e;
        }
    }
}