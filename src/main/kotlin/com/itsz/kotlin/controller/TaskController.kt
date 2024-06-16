package com.itsz.kotlin.controller

import com.itsz.kotlin.model.Applicant
import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService
import org.activiti.engine.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.stream.Collectors


@RestController
@RequestMapping("/tasks")
class TaskController(val taskService: TaskService) {

    @GetMapping()
    fun getTasks(): Map<String, String> =
        taskService.createTaskQuery().active().list().stream().collect(Collectors.toMap({ it.id }, { it.name }))


    @PostMapping("/{taskId}")
    fun completeTask(@PathVariable taskId: String) {
        taskService.complete(taskId)
    }


}