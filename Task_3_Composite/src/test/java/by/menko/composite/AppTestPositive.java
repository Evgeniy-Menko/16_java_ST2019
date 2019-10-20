package by.menko.composite;

import by.menko.composite.controller.Controller;
import by.menko.composite.dal.exception.NotInitializationException;
import by.menko.composite.dal.exception.SortException;
import by.menko.composite.service.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class AppTestPositive {
    @BeforeTest
    public void writeFileAndAddStorage() throws IOException {
        Controller c = new Controller();
        ChooseLanguage language = new ChooseLanguage();
        ReadFileAndAddStorage service = new ReadFileAndAddStorage();
        String b = service.readAndAddToStorage("src//test//resources//data//file1.txt");
        System.out.println(c.getBundle().getMessage(b));
        language.changeLanguage("2");
        System.out.println("\n" + c.getBundle().getMessage(b));
        language.changeLanguage("3");
        System.out.println("\n" + c.getBundle().getMessage(b));
    }

    @Test
    public void sortBySymbol() throws SortException, NotInitializationException {
        SortBySymbolService service = new SortBySymbolService();
        String b = service.sortBySymbol("a");
        System.out.println(b);
    }

    @Test
    public void sortByCountSentence() throws SortException, NotInitializationException {
        SortByCount service = new SortByCount();
        String b = service.sortByCountWordAndSentence("2");
        System.out.println(b);
    }

    @Test
    public void sortByCountWord() throws SortException, NotInitializationException {
        SortByCount service = new SortByCount();
        String b = service.sortByCountWordAndSentence("3");
        System.out.println(b);
    }

    @Test
    public void collectText() throws IOException, NotInitializationException {
        CollectText service = new CollectText();
        String b = service.collect();
        System.out.println(b);
    }

    @Test
    public void chooseLanguage() throws IOException, NotInitializationException {
        Controller c = new Controller();
        ChooseLanguage language = new ChooseLanguage();
        System.out.println(c.getBundle().getMessage("menu"));
        language.changeLanguage("2");
        System.out.println("\n" + c.getBundle().getMessage("menu"));
        language.changeLanguage("1");
        System.out.println("\n" + c.getBundle().getMessage("menu"));
    }
}
