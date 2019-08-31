package ru.otus.cources.services;

import javax.annotation.PreDestroy;
import java.util.Scanner;

public class CommandLineService {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String askQuestion(String question) {
        System.out.println(question);
        return SCANNER.nextLine().trim();
    }

    @PreDestroy
    private void closeScanner() {
        SCANNER.close();
    }
}
