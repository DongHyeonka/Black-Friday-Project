package com.fastcampus.payment_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.payment_service.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {

    List<PaymentMethod> findByUserId(Long userId);
}
