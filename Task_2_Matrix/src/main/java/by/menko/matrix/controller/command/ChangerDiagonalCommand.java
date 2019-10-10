package by.menko.matrix.controller.command;

import by.menko.matrix.service.ChangerDiagonal;
import by.menko.matrix.view.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangerDiagonalCommand implements Command {
    /**
     * log4j2.
     */
    private Logger logger = LogManager.getLogger();

    /**
     * Calls the service change diagonal.
     *
     * @param action for service.
     */
    @Override
    public void execute(final String action) {
        ChangerDiagonal changerDiagonal = new ChangerDiagonal();
        Menu menu = new Menu();
        try {
            menu.printTextForChangeDiagonal();
            String response = changerDiagonal.changeDiagonal(action);
            logger.info(response);
        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName()
                    + " dead");
        }
    }
}
