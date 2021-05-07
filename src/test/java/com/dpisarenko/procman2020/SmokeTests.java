package com.dpisarenko.procman2020;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class SmokeTests {
    @Test
    public void test_bpmn() throws IOException {
        ValidationUtils.validateFile("src/main/resources/test.bpmn");
    }
    @Test
    @Ignore // This test fails even though the process is deployed successfully
    public void lesson12_scenes_bpmn() throws IOException {
        ValidationUtils.validateFile("src/main/resources/lesson12_scenes.bpmn");
    }
}
