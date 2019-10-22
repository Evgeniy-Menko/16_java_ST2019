package by.menko.composite;

import by.menko.composite.service.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.testng.Assert.assertThrows;

public class AppTestNegative {
    @BeforeTest
    public void writeFileAndAddStorage() {
        ReadFileAndAddStorage service = new ReadFileAndAddStorage();
        assertThrows(Exception.class, () -> service.execute("src//test//resource//data//file1.txt"));
    }

    @Test
    public void sortBySymbol() {
        SortBySymbolService service = new SortBySymbolService();
        assertThrows(Exception.class, () -> service.execute("a"));
    }

    @Test
    public void sortByCountSentenceAndWord() {
        SortByCount service = new SortByCount();
        assertThrows(Exception.class, () -> service.execute("2"));
        assertThrows(Exception.class, () -> service.execute("3"));
    }

    @Test
    public void collectText()  {
        CollectText service = new CollectText();
        assertThrows(Exception.class, service::execute);
    }

}
