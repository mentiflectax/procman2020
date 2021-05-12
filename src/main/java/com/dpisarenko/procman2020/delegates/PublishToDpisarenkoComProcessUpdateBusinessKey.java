package com.dpisarenko.procman2020.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class PublishToDpisarenkoComProcessUpdateBusinessKey implements JavaDelegate {
    @Override
    public void execute(final DelegateExecution delEx) throws Exception {
        final String issueNr = (String) delEx.getVariable("BITBUCKET_ISSUE_NR");
        final String businessKey = String.format("DPISARENKO-COM-I-%s", issueNr);
        delEx.setProcessBusinessKey(businessKey);
    }
}
