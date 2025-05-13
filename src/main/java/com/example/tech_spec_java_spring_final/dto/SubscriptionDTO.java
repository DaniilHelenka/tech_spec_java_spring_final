package com.example.tech_spec_java_spring_final.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubscriptionDTO {
    @NotBlank(message = "Название сервиса не может быть пустым")
    private String serviceName;
}