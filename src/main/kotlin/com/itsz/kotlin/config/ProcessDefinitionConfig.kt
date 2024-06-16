package com.itsz.kotlin.config

import com.itsz.kotlin.bpmn.bpmn
import com.itsz.kotlin.bpmn.process
import org.activiti.bpmn.model.ImplementationType
import org.activiti.engine.RepositoryService
import org.activiti.engine.repository.Deployment
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProcessDefinitionConfig {

    @Bean
    fun deployment(repositoryService: RepositoryService): Deployment {
        val bpmnModel = bpmn {
            process = process(id = "process", name = "dynamic-workflow") {
                startEvent {
                    id = "startEvent"
                    name = "start event"
                }
                userTask {
                    id = "task1"
                    name = "user task"
                }

                sequenceFlow {
                    sourceRef = "startEvent"
                    targetRef = "task1"
                }

                serviceTask {
                    id = "task2"
                    name = "service task"
                    implementation = "com.itsz.kotlin.service.ResumeService"
                    implementationType = ImplementationType.IMPLEMENTATION_TYPE_CLASS
                }

                sequenceFlow {
                    sourceRef = "task1"
                    targetRef = "task2"
                }

                endEvent {
                    id = "endEvent"
                    name = "end Event"
                }

                sequenceFlow {
                    sourceRef = "task2"
                    targetRef = "endEvent"
                }
            }

        }
        return repositoryService.createDeployment()
            .addBpmnModel("dynamic-workflow.bpmn", bpmnModel)
            .deploy()
    }
}