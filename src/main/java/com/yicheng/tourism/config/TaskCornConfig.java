package com.yicheng.tourism.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "bs.yicheng.task")
public class TaskCornConfig {
    private String corn;
    private String path;
}
