package ru.basanov.notification.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("ru.basanov")
@Import(DataSourceConfiguration.class)
@EnableScheduling
@EnableAsync
public class ApplicationConfiguration {
}
