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
    fun processDeployment(repositoryService: RepositoryService): Deployment {
        val bpmnModel = bpmn {
            process = process(id = "processes", name = "dynamic-workflow") {
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

    @Bean
    fun strategyDeployment(repositoryService: RepositoryService): Deployment{
        val bpmnModel = bpmn {
            process = process("strategyGoLive", "strategy go live"){
                startEvent {
                    id = "startEvent"
                    name = "Start Event"
                }
                sequenceFlow {
                    sourceRef = "startEvent"
                    targetRef = "userTask1"
                }

                userTask {
                    id = "userTask1"
                    name = "Edit the strategy"
                }

                sequenceFlow {
                    sourceRef = "userTask1"
                    targetRef = "forkGateway"
                }
                parallelGateway {
                    id = "forkGateway"
                    name = "fork gateway"
                }

                sequenceFlow {
                    sourceRef = "forkGateway"
                    targetRef = "userTask2"
                }

                userTask {
                    id = "userTask2"
                    name = "senor team 1 review"
                }

                sequenceFlow {
                    sourceRef = "forkGateway"
                    targetRef = "userTask3"
                }

                userTask {
                    id = "userTask3"
                    name = "senor team 2 review"
                }

                sequenceFlow {
                    sourceRef = "userTask2"
                    targetRef = "joinGateway"
                }

                sequenceFlow {
                    sourceRef = "userTask3"
                    targetRef = "joinGateway"
                }

                parallelGateway {
                    id = "joinGateway"
                    name = "joinGateway"
                }

                sequenceFlow {
                    sourceRef = "joinGateway"
                    targetRef = "exclusiveGateway1"
                }

                exclusiveGateway {
                    id = "exclusiveGateway1"
                    name = "exclusive Gateway 1"
                    isNotExclusive = false
                    defaultFlow = "fallbackSequenceFlow1"
                }

                sequenceFlow {
                    id = "fallbackSequenceFlow1"
                    sourceRef = "exclusiveGateway1"
                    targetRef = "userTask1"
                }

                sequenceFlow {
                    sourceRef = "exclusiveGateway1"
                    targetRef = "endEvent"
                }

                endEvent {
                    id = "endEvent"
                    name = "end Event"
                }
            }

        }
        return repositoryService.createDeployment()
            .addBpmnModel("strategyOnboarding.bpmn", bpmnModel)
            .deploy()
    }
}