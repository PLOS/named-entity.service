package org.plos.namedentity.spring.config;

import org.plos.namedentity.service.NamedEntityService;
import org.plos.namedentity.service.NamedEntityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:spring-beans.xml")
public class ContextConfig {
}
