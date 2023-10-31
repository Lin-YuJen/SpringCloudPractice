package com.enix.provider.entity

import jakarta.persistence.*

@Entity
@Table(name = "payment")
data class PaymentEntity(
    @Id
    @Column(name = "id")
    val id: Long,
    @Column(name = "serial")
    val serial: String
)