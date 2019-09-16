package ru.otus.boot.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.MessageSource;
import ru.otus.boot.repository.ExamDataRepository;

import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class LocaleServiceTest {

    private static final String RU_LOCALE_CODE = "ru";
    private static final String RUS_COUNTRY = "RU";
    private static final String EN_LOCALE_CODE = "en";
    private static final String MESSAGE = "message";
    private static final String ANY_ANOTHER_LOCALE_CODE = "any another code";
    private LocaleService localeService;
    private ExamDataRepository examDataRepository;
    private MessageSource messageSource;

    @Before
    public void before() {
        messageSource = mock(MessageSource.class);
        examDataRepository = mock(ExamDataRepository.class);
        localeService = new LocaleService(messageSource, examDataRepository);
    }

    @Test
    public void getRussianCommonMessage() {
        ArgumentCaptor<Locale> argumentCaptor = ArgumentCaptor.forClass(Locale.class);
        localeService.changeLocale(RU_LOCALE_CODE);
        localeService.getCommonMessage(MESSAGE);
        verify(messageSource).getMessage(eq(MESSAGE), eq(null), argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), new Locale(RU_LOCALE_CODE, RUS_COUNTRY));
    }

    @Test
    public void getEnglishCommonMessage() {
        ArgumentCaptor<Locale> argumentCaptor = ArgumentCaptor.forClass(Locale.class);
        localeService.changeLocale(EN_LOCALE_CODE);
        localeService.getCommonMessage(MESSAGE);
        verify(messageSource).getMessage(eq(MESSAGE), eq(null), argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), Locale.ENGLISH);
    }

    @Test
    public void getDefaultCommonMessage() {
        ArgumentCaptor<Locale> argumentCaptor = ArgumentCaptor.forClass(Locale.class);
        localeService.changeLocale(ANY_ANOTHER_LOCALE_CODE);
        localeService.getCommonMessage(MESSAGE);
        verify(messageSource).getMessage(eq(MESSAGE), eq(null), argumentCaptor.capture());
        assertEquals(argumentCaptor.getValue(), Locale.ENGLISH);
    }

    @Test
    public void changeLocaleEn() {
        localeService.changeLocale(EN_LOCALE_CODE);
        verify(examDataRepository, times(1)).getEngExamData();
    }

    @Test
    public void changeLocaleRu() {
        localeService.changeLocale(RU_LOCALE_CODE);
        verify(examDataRepository, times(1)).getRusExamData();
    }

    @Test
    public void changeLocalDefault() {
        localeService.changeLocale(ANY_ANOTHER_LOCALE_CODE);
        verify(examDataRepository, times(1)).getEngExamData();
    }
}