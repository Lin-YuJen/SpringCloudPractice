package com.enix.provider.repository

import com.enix.provider.entity.PaymentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.transaction.annotation.Transactional

interface PaymentRepository : JpaRepository<PaymentEntity, Long> {

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM payment WHERE id = :id"
    )
    fun getPaymentById(id: Long): PaymentEntity


    @Modifying // UPDATE,INSERT 等 SQL 需要這個 Annotation
    @Transactional
    @Query(
        nativeQuery = true,
        value = """
            INSERT INTO payment VALUES (
                #{#payment.id},
                #{#payment.serial}
            )
        """
    )
    fun createPayment(payment: PaymentEntity)
}