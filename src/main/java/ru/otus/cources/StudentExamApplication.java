package ru.otus.cources;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.otus.cources.config.StudentExamConfiguration;
import ru.otus.cources.services.ExamService;

@ComponentScan
public class StudentExamApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(StudentExamApplication.class);
        context.refresh();

        ExamService examService = context.getBean(ExamService.class);
        examService.proceedExam();
    }
}
