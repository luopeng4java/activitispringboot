package com.zp.activitispringboot.controller;

import com.zp.activitispringboot.config.SecurityUtil;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTest {


    private Logger logger = LoggerFactory.getLogger(ControllerTest.class);

    /**
     * 流程定义相关操作
     */
    @Autowired
    private ProcessRuntime processRuntime;

    /**
     * 任务相关操作
     */
    @Autowired
    private TaskRuntime taskRuntime;

    /**
     * security工具类
     */
    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping("/definition/list")
    public Object testDefinition() {
//        securityUtil.logInAs("f1");
        Page processDefinitionPage = processRuntime
                .processDefinitions(Pageable.of(0, 100));
        logger.info("> Available Process definitions: " +
                processDefinitionPage.getTotalItems());
        for (Object pd : processDefinitionPage.getContent()) {
            logger.info("\t > Process definition: " + pd);
        }

        return processDefinitionPage;
    }
}