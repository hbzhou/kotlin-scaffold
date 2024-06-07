package com.itsz.kotlin.controller

import com.itsz.kotlin.model.Applicant
import com.itsz.kotlin.repository.ApplicantRepository
import org.activiti.engine.HistoryService
import org.activiti.engine.RuntimeService
import org.activiti.engine.TaskService
import org.activiti.engine.task.TaskInfoQuery
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.function.Function
import java.util.stream.Collectors


@RestController
@RequestMapping("/activiti")
class ActivitiController(
    val runtimeService: RuntimeService,
    val taskService: TaskService,
    val historyService: HistoryService,
) {

    @GetMapping("/tasks")
    fun getTasks(): Map<String, String> = taskService.createTaskQuery().list().stream().collect(Collectors.toMap({ it.id }, { it.name}))

    @GetMapping("/startProcess")
    fun startProcess() {
        val applicant = Applicant(name = "john Doe", email = "joh.Doe@email.com", phoneName = "123213123")
        runtimeService.startProcessInstanceByKey("hireProcessWithJpa", mapOf("applicant" to applicant))
    }

    @GetMapping("/tasks/{taskId}")
    fun triggerTask(@PathVariable taskId: String) {
        taskService.complete(taskId, mapOf("telephoneInterviewOutcome" to true))
    }
}