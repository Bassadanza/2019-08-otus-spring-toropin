package ru.otus.cources.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class StudentExamConfiguration {

    @Bean
    @Qualifier("en")
    public Resource englishResource(ResourceLoader resourceLoader) {
        return resourceLoader.getResource("exam_en.csv");
    }

    @Bean
    @Qualifier("ru")
    public Resource russianResource(ResourceLoader resourceLoader) {
        return resourceLoader.getResource("exam_ru.csv");
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasename("commonData");
        return messageSource;
    }
}
