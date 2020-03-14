package com.yicheng.tourism.task;

import com.yicheng.tourism.config.TaskCornConfig;
import com.yicheng.tourism.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.config.Task;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@EnableScheduling
public class TimerTask implements SchedulingConfigurer {

    @Autowired
    private UserService userService;
    @Autowired
    private TaskCornConfig taskCornConfig;

    /**
     * Callback allowing a {@link TaskScheduler
     * TaskScheduler} and specific {@link Task Task}
     * instances to be registered against the given the {@link ScheduledTaskRegistrar}.
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
//        taskRegistrar.addTriggerTask(()->userService.scanDir(),getTrigger(taskCornConfig.getCorn()));
    }

    private Trigger getTrigger(String cronExpression) {
        return triggerContext -> {
            CronTrigger cronTrigger = new CronTrigger(cronExpression);
            return cronTrigger.nextExecutionTime(triggerContext);
        };
    }
}
