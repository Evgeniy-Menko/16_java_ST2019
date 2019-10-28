import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.service.parser.CandyDOMBuilder;
import by.menko.xmlparsing.service.validator.ValidatorXML;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/resources/data/candies.xml";
        CandyDOMBuilder c = new CandyDOMBuilder();
        c.buildSetCandies(fileName);
        List<Candy>  a = c.getCandies();
System.out.println("a");
    }
}
