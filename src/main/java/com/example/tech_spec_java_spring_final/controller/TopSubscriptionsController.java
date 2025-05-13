package com.example.tech_spec_java_spring_final.controller;

import com.example.tech_spec_java_spring_final.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/subscriptions")
public class TopSubscriptionsController {

    private static final Logger logger = LoggerFactory.getLogger(TopSubscriptionsController.class);

    private final SubscriptionService subscriptionService;

    public TopSubscriptionsController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @GetMapping("/top")
    public ResponseEntity<List<Map<String, Object>>> getTopSubscriptions() {
        logger.info("Получение ТОП-3 популярных подписок");
        List<Map<String, Object>> topSubscriptions = subscriptionService.getTop3PopularSubscriptions();
        return ResponseEntity.ok(topSubscriptions);
    }
}