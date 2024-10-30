package vttp.batch5.sdf.task02;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Please provide a TTT configuration file.");
            System.exit(1);
        }

        try {
            Board board = new Board();
            board.loadFile(args[0]);

            System.out.println("Processing: " + args[0]);
            System.out.println("\nBoard:");
            board.print();

            List<Position> legalPositions = board.findLegalPositions();
            Game game = new Game(board);
            game.evaluatePositions(legalPositions);

            System.out.println("\n------------------------");
            for (Position pos : legalPositions) {
                System.out.printf("y = %d, x = %d, utility = %d%n", pos.getY(), pos.getX(), pos.getUtility());
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
