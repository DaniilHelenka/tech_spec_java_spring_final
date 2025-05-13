package com.example.tech_spec_java_spring_final.service;

import com.example.tech_spec_java_spring_final.entity.Subscription;
import com.example.tech_spec_java_spring_final.entity.User;
import com.example.tech_spec_java_spring_final.exception.UserNotFoundException;
import com.example.tech_spec_java_spring_final.repository.SubscriptionRepository;
import com.example.tech_spec_java_spring_final.repository.UserRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Validated
public class SubscriptionService {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository, UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Subscription createSubscription(@Valid Subscription subscription, Long userId) {
        logger.info("Создание подписки для пользователя: userId={}, serviceName={}", userId, subscription.getServiceName());
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        subscription.setUser(user);
        subscription.setCreatedAt(LocalDateTime.now());
        try {
            return subscriptionRepository.save(subscription);
        } catch (Exception e) {
            logger.error("Ошибка при создании подписки: userId={}, serviceName={}", userId, subscription.getServiceName(), e);
            throw e;
        }
    }

    public List<Subscription> getSubscriptionsByUserId(Long userId) {
        logger.info("Получение подписок для пользователя: userId={}", userId);
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }
        return subscriptionRepository.findByUserId(userId);
    }

    public Subscription getSubscriptionById(Long subscriptionId) {
        logger.info("Получение подписки: id={}", subscriptionId);
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new IllegalArgumentException("Подписка с ID " + subscriptionId + " не найдена"));
    }

    public void deleteSubscription(Long subscriptionId) {
        logger.info("Удаление подписки: id={}", subscriptionId);
        if (!subscriptionRepository.existsById(subscriptionId)) {
            throw new IllegalArgumentException("Подписка с ID " + subscriptionId + " не найдена");
        }
        try {
            subscriptionRepository.deleteById(subscriptionId);
        } catch (Exception e) {
            logger.error("Ошибка при удалении подписки: id={}", subscriptionId, e);
            throw e;
        }
    }

    public List<Map<String, Object>> getTop3PopularSubscriptions() {
        logger.info("Получение ТОП-3 популярных подписок");
        List<Object[]> results = subscriptionRepository.findTop3PopularSubscriptions();
        return results.stream()
                .map(result -> Map.of(
                        "serviceName", result[0],
                        "subscriptionCount", result[1]
                ))
                .collect(Collectors.toList());
    }
}