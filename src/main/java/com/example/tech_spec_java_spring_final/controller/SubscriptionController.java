package com.example.tech_spec_java_spring_final.controller;

import com.example.tech_spec_java_spring_final.dto.SubscriptionDTO;
import com.example.tech_spec_java_spring_final.entity.Subscription;
import com.example.tech_spec_java_spring_final.service.SubscriptionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{id}/subscriptions")
public class SubscriptionController {

    private static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@PathVariable Long id, @Valid @RequestBody SubscriptionDTO subscriptionDTO) {
        logger.info("Создание подписки для пользователя: userId={}, serviceName={}", id, subscriptionDTO.getServiceName());
        Subscription subscription = new Subscription();
        subscription.setServiceName(subscriptionDTO.getServiceName());
        Subscription createdSubscription = subscriptionService.createSubscription(subscription, id);
        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Subscription>> getSubscriptions(@PathVariable Long id) {
        logger.info("Получение подписок для пользователя: userId={}", id);
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(id);
        return ResponseEntity.ok(subscriptions);
    }

    @DeleteMapping("/{sub_id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable("id") Long userId, @PathVariable("sub_id") Long subId) {
        logger.info("Удаление подписки: userId={}, subId={}", userId, subId);
        subscriptionService.deleteSubscription(subId);
        return ResponseEntity.noContent().build();
    }
}