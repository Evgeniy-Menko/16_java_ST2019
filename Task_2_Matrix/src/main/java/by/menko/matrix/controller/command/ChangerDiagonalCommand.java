package by.menko.matrix.controller.command;

import by.menko.matrix.service.ChangerDiagonal;

public class ChangerDiagonalCommand implements Command {
    /**
     * Calls the service change diagonal.
     *
     * @param action for service.
     */
    @Override
    public void execute(final String action) {
        ChangerDiagonal changerDiagonal = new ChangerDiagonal();
        try {
            changerDiagonal.changeDiagonal(action);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
