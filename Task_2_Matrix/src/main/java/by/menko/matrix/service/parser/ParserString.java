package by.menko.matrix.service.parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParserString {
    /**
     * Parser for string with delimiter.
     *
     * @param word string values.
     *
     * @return array words.
     */
    public String[] parserString(final String word) {
        return word.split(" ");
    }

    /**
     * Parser string to list with delimiter.
     *
     * @param word string values.
     *
     * @return list<String>.
     */
    public List<String> parseToList(final String word) {
        List<String> list = Arrays.asList(word.split("\\r?\\n"));
        return list.stream().filter(c -> !c.equals(""))
                .collect(Collectors.toList());
    }


}
