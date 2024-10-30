package vttp.batch5.sdf.task02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private char[][] grid;

    public Board() {
        grid = new char[3][3];
    }

    public void loadFile(String file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int row = 0;
            while ((line = br.readLine()) != null && row < 3) {
                for (int col = 0; col < line.length() && col < 3; col++) {
                    char c = line.charAt(col);
                    if (c == '.') {
                        grid[row][col] = ' ';
                    } else {
                        grid[row][col] = c;
                    }
                }
                row++;
            }
        }
    }

    public void print() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = grid[i][j];
                if (c == ' ') {
                    System.out.print('.');
                } else {
                    System.out.print(c);
                }
            }
            System.out.println();
        }
    }

    public List<Position> findLegalPositions() {
        List<Position> positions = new ArrayList<>();
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x] == ' ') {
                    positions.add(new Position(x, y));
                }
            }
        }
        return positions;
    }

    public char[][] getGrid() {
        return grid;
    }

    public Board clone() {
        Board newBoard = new Board();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(grid[i], 0, newBoard.grid[i], 0, 3);
        }
        return newBoard;
    }
}