package com.dpisarenko.procman2020;

import org.apache.commons.io.FileUtils;
import org.camunda.bpm.engine.ParseException;
import org.camunda.bpm.engine.impl.cfg.BpmnParseFactory;
import org.camunda.bpm.engine.impl.cfg.DefaultBpmnParseFactory;
import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.engine.impl.el.ExpressionManager;
import org.camunda.bpm.engine.impl.interceptor.CommandInterceptor;
import org.camunda.bpm.engine.impl.persistence.entity.DeploymentEntity;
import org.junit.Assert;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParser;
import org.camunda.bpm.engine.impl.bpmn.parser.BpmnParse;
import org.camunda.bpm.engine.impl.context.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

final class ValidationUtils {
    static void validateFile(final String fileName) throws IOException {
        try (FileInputStream inputStream = FileUtils.openInputStream(new File(fileName))) {
            ExpressionManager expressionManager = new ExpressionManager();

            ProcessEngineConfigurationImpl processEngineConfiguration = new ProcessEngineConfigurationImpl() {
                @Override
                protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequired() {
                    return new ArrayList<>();
                }

                @Override
                protected Collection<? extends CommandInterceptor> getDefaultCommandInterceptorsTxRequiresNew() {
                    return new ArrayList<>();
                }
            };

            Context.setProcessEngineConfiguration(processEngineConfiguration);

            BpmnParseFactory bpmnParseFactory = new DefaultBpmnParseFactory();
            BpmnParser bpmnParser = new BpmnParser(expressionManager, bpmnParseFactory);
            BpmnParse bpmnParse = bpmnParser.createParse()
                    .sourceInputStream(inputStream)
                    .deployment(new DeploymentEntity())
                    .name(fileName);
            bpmnParse.execute();
        }
        catch (final ParseException exception) {
            Assert.fail(exception.getMessage());
        }
    }
}
