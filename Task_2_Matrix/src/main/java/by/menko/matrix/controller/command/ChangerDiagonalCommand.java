package by.menko.matrix.controller.command;

import by.menko.matrix.service.ChangerDiagonal;

public class ChangerDiagonalCommand implements Command {
    @Override
    public void execute(String value) {
        ChangerDiagonal changerDiagonal = new ChangerDiagonal();
        try {
            changerDiagonal.changeDiagonal(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
