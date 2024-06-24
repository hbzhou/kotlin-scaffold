package com.itsz.kotlin.controller

import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/processes")
class ProcessController(val repositoryService: RepositoryService, val runtimeService: RuntimeService) {

    @GetMapping("/{id}")
    fun startProcess(@PathVariable id: String): String {
        val processInstance = runtimeService.startProcessInstanceById(id)
        return processInstance.processDefinitionKey
    }

    @GetMapping
    fun listProcesses(): Map<String, String> {
        return repositoryService.createProcessDefinitionQuery().active().list().associate { it.id to it.name }
    }

    @GetMapping("/instances")
    fun listProcessInstances(): Map<String, Boolean> {
        return runtimeService.createProcessInstanceQuery().list().associate { it.id to it.isEnded }
    }
}