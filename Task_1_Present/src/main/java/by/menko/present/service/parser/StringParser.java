package by.menko.present.service.parser;

import java.util.Arrays;
import java.util.List;

/**
 * @author Evgeniy Menko
 */
public class StringParser {
    /**
     * Parser for string with parameter object sweet.
     *
     * @param word String words.
     *
     * @return array words.
     */
    public String[] parserObjects(final String word) {
        String[] array = word.split("[ ,]+");
        return array;
    }

    /**
     * Parser for string with parameter object present.
     *
     * @param word String words.
     *
     * @return list<String>.
     */
    public List<String> parseString(final String word) {
        List<String> list = Arrays.asList(word.split("\\r?\\n"));
        return list;
    }
}
