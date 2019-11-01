package by.menko.xmlparsing.dal;

import by.menko.xmlparsing.bean.Candy;

import java.util.List;

public interface Spetification {
    List<Candy> buildSetCandies(String fileName);

    List<Candy> getCandies();
}
