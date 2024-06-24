package com.itsz.kotlin.bpmn

import org.activiti.bpmn.model.*

class ProcessBuilder(
    private val id: String,
    private val name: String
) {
    private val flowElements = mutableListOf<FlowElement>()

    fun startEvent(init: StartEvent.() -> Unit) {
        val startEvent = StartEvent().apply(init)
        flowElements.add(startEvent)
    }

    fun endEvent(init: EndEvent.() -> Unit){
        val endEvent = EndEvent().apply(init)
        flowElements.add(endEvent)
    }

    fun userTask(init: UserTask.() -> Unit){
        val userTask = UserTask().apply(init)
        flowElements.add(userTask)
    }

    fun serviceTask(init: ServiceTask.() -> Unit){
        val serviceTask = ServiceTask().apply(init)
        flowElements.add(serviceTask)
    }

    fun inclusiveGateway(init: InclusiveGateway.() -> Unit){
        val inclusiveGateway = InclusiveGateway().apply(init)
        flowElements.add(inclusiveGateway)
    }

    fun exclusiveGateway(init:ExclusiveGateway.() -> Unit){
        val exclusiveGateway = ExclusiveGateway().apply(init)
        flowElements.add(exclusiveGateway)
    }

    fun parallelGateway(init: ParallelGateway.() -> Unit){
        val parallelGateway = ParallelGateway().apply(init)
        flowElements.add(parallelGateway)
    }

    fun sequenceFlow(init: SequenceFlow.()-> Unit){
        val sequenceFlow = SequenceFlow().apply(init)
        flowElements.add(sequenceFlow)
    }

    fun build(): Process {
        val process = Process()
        process.id = id
        process.name = name
        process.flowElements.addAll(flowElements)
        return process
    }

}

fun process(id: String, name: String, init: ProcessBuilder.() -> Unit): Process {
    return ProcessBuilder(id, name).apply(init).build()
}