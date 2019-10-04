import by.menko.matrix.service.file.ServiceFile;
import by.menko.matrix.service.parser.ParserString;
import by.menko.matrix.service.validate.Validator;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ServiceFile s = new ServiceFile();
        ParserString p = new ParserString();
        Validator v = new Validator();
        List<String> l = p.parseString(s.fileReader("data//File.txt"));
        boolean b = v.validateMatrix(l);
        System.out.println(b);

    }
}
