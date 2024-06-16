package com.itsz.kotlin.service

import org.activiti.engine.delegate.DelegateExecution
import org.activiti.engine.delegate.JavaDelegate
import org.springframework.stereotype.Component

@Component
class ResumeService: JavaDelegate {

    override fun execute(execution: DelegateExecution) {
        println("storing resume ....")
    }
}