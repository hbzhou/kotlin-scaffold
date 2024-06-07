package com.itsz.kotlin.repository

import com.itsz.kotlin.model.Applicant
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicantRepository: JpaRepository<Applicant, Long>