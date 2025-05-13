package com.example.tech_spec_java_spring_final.repository;

import com.example.tech_spec_java_spring_final.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {


    List<Subscription> findByUserId(Long userId);

    @Query("SELECT s FROM Subscription s WHERE s.user.id = :userId")
    List<Subscription> findSubscriptionsByUserId(@Param("userId") Long userId);

    @Query("SELECT s.serviceName, COUNT(s) as subscriptionCount " +
           "FROM Subscription s " +
           "GROUP BY s.serviceName " +
           "ORDER BY subscriptionCount DESC")
    List<Object[]> findTop3PopularSubscriptions();
}