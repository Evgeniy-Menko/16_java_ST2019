import by.menko.xmlparsing.service.validator.ValidatorXML;

public class Main {
    public static void main(String[] args) {
        String fileName = "src/main/resources/data/candies.xml";
        ValidatorXML v = new ValidatorXML();
        System.out.println(v.isValid(fileName));
    }
}
