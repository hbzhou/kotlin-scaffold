package com.itsz.kotlin.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id


@Entity
data class Applicant(
    @Id
    @GeneratedValue
    val id: Long = 1L,
    val name: String,
    val email: String,
    val phoneName: String,
)