package by.menko.matrix.controller.command;

import by.menko.matrix.service.ReadFile;

import java.io.IOException;

public class ReadFileCommand implements Command {
    /**
     * Calls the service read file.
     *
     * @param action .
     */

    @Override
    public void execute(final String action) {
        ReadFile readFile = new ReadFile();
        try {
            readFile.readFileAndAdd();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
