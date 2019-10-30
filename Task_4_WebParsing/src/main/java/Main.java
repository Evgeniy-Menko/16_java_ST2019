import by.menko.xmlparsing.bean.Candy;
import by.menko.xmlparsing.dal.impl.CandyDOMBuilder;
import by.menko.xmlparsing.dal.impl.CandySaxBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/resources/data/candies.xml";
        CandySaxBuilder c = new CandySaxBuilder();
        c.buildSetCandy(fileName);
        List<Candy>  a = c.getCandies();
System.out.println("a");
    }
}
