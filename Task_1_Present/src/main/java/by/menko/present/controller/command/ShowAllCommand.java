package by.menko.present.controller.command;

import by.menko.present.entity.Presents;
import by.menko.present.entity.Sweets;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Evgeniy Menko
 */
public class ShowAllCommand implements Command {
    /**
     * Show all present in storage.
     */
    @Override
    public void execute() {
        Map<String, Presents> storage = new HashMap<String, Presents>();
        storage = DAO.getAllPresents();
        if (!storage.isEmpty()) {
            for (Map.Entry<String, Presents> entry : storage.entrySet()) {
                System.out.println("Customer = " + entry.getKey());
                for (Map.Entry<Integer, Sweets> item : entry.getValue()
                        .getSweets().entrySet()) {
                    System.out.println(item.getKey() + item.getValue().print());
                }
            }
        } else {
            System.out.println("Storage is empty.");
        }

    }
}
