package com.itsz.kotlin.bpmn

import org.activiti.bpmn.model.BpmnModel
import org.activiti.bpmn.model.Process

class BpmnBuilder {
    var process: Process = Process()

    fun build(): BpmnModel {
        val bpmnModel = BpmnModel()
        bpmnModel.addProcess(process)
        return bpmnModel
    }

}

fun bpmn(init: BpmnBuilder.() -> Unit): BpmnModel {
    val bpmnBuilder = BpmnBuilder().apply(init)
    return bpmnBuilder.build()
}