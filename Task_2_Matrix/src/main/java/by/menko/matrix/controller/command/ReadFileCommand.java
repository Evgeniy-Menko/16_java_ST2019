package by.menko.matrix.controller.command;

import by.menko.matrix.service.ReadFile;

import java.io.IOException;

public class ReadFileCommand implements Command {


    @Override
    public void execute(String value) {
        ReadFile readFile = new ReadFile();
        try {
            readFile.readFileAndAdd();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
