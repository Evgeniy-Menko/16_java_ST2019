package by.menko.matrix.view;


public class Menu {
    /**
     * Print menu application.
     */
    public void printMenu() {
        System.out.println("Enter a command:\n"
                + "1) Read the file.\n"
                + "2) Change diagonal with lock.\n"
                + "3) Change diagonal with executor.\n"
                + "4) Change diagonal with semaphore.\n"
                + "5) Change diagonal with thread.\n"
                + "6) Exit.\n");
    }

    /**
     * Print matrix.
     *
     * @param matrix .
     */
    public void printMatrix(final int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

    }

}
