package by.menko.composite.service.file;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class ServiceFileTest {
    private ServiceFile service = new ServiceFile();

    @DataProvider(name = "dataForPositiveReadFile")
    public Object[][] createDataForPositiveValidateFile() {
        return new Object[][]{
                {"src//test//resources//data//File4.txt", "a"},
                {"src//test//resources//data//File2.txt", ""},
                {"src//test//resources//data//File3.txt", "work"},
        };
    }

    @DataProvider(name = "dataForNegativeReadFile")
    public Object[] createDataForNegativeValidateFile() {
        return new Object[]{
                "src//test//resource//data//File.txt",
                "test//resource//data//File2.txt",
                "File3.txt"
        };
    }

    @Test(description = " positive_read_file", dataProvider = "dataForPositiveReadFile")
    public void positiveReadFile(String nameFile, String result) throws IOException {
        String actual = service.fileReader(nameFile);
        assertEquals(actual, result);
    }

    @Test(description = " negative_read_file", dataProvider = "dataForNegativeReadFile")
    public void negativeReadFile(String nameFile) {
        assertThrows(Exception.class, () -> service.fileReader(nameFile));
    }

}

