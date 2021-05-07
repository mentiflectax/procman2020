package com.dpisarenko.procman2020.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class GenerateBusinessKeyForLesson12SceneOutliningProcess implements JavaDelegate {
    @Override
    public void execute(final DelegateExecution delEx) throws Exception {
        final String project = (String) delEx.getVariable("PROJECT");
        final String scene = (String)delEx.getVariable("SCENE");
        final String businessKey = String.format("%s-L12-%s", project, scene);
        delEx.setProcessBusinessKey(businessKey);
    }
}
