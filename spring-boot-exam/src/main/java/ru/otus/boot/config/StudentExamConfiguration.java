package ru.otus.boot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

@Configuration
public class StudentExamConfiguration {

    @Value("${examData.en}")
    private String examDataEng;
    @Value("${examData.ru}")
    private String examDataRu;
    @Value("${defaultEncoding}")
    private String defaultEncoding;
    @Value("${commonDataFileName}")
    private String commonDataFileName;

    @Bean
    @Qualifier("en")
    public Resource englishResource(ResourceLoader resourceLoader) {
        return resourceLoader.getResource(examDataEng);
    }

    @Bean
    @Qualifier("ru")
    public Resource russianResource(ResourceLoader resourceLoader) {
        return resourceLoader.getResource(examDataRu);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(defaultEncoding);
        messageSource.setBasename(commonDataFileName);
        return messageSource;
    }
}
