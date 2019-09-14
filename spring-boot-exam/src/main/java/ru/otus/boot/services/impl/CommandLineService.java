package ru.otus.boot.services.impl;

import org.springframework.stereotype.Service;
import ru.otus.boot.services.IOService;

import javax.annotation.PreDestroy;
import java.util.Scanner;

@Service
public class CommandLineService implements IOService {

    private static final Scanner SCANNER = new Scanner(System.in);

    public String askQuestion(String question) {
        printMessage(question);
        return SCANNER.nextLine().trim();
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    @PreDestroy
    private void closeScanner() {
        SCANNER.close();
    }
}
