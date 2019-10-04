package by.menko.matrix.service.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserString {
    /**
     * Parser for string with delimiter.
     *
     * @param word String words.
     *
     * @return array words.
     */
    public String[] parserString(final String word) {
        return word.split(" ");
    }

    public List<String> parseString(final String word) {
        List<String> list = Arrays.asList(word.split("\\r?\\n"));
        return list.stream().filter(c -> !c.equals("")).collect(Collectors.toList());
    }


}
