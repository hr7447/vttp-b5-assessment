package vttp.batch5.sdf.task02;

import java.util.List;

public class Game {
    public Board board;

    public Game(Board board) {
        this.board = board;
    }
    
    public void evaluatePositions(List<Position> positions) {
        for (Position pos : positions) {
            pos.setUtility(evaluateMove(pos));
        }
    }
    
    private int evaluateMove(Position pos) {
        Board newBoard = board.clone();
        char[][] grid = newBoard.getGrid();
        
        grid[pos.getY()][pos.getX()] = 'X';
        
        if (checkWin(grid, 'X')) {
            return 1;
        }
        
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++) {
                if (grid[y][x] == ' ') {
                    grid[y][x] = 'O';
                    if (checkWin(grid, 'O')) {
                        return -1;
                    }
                    grid[y][x] = ' ';
                }
            }
        }
        
        return 0;
    }
    
    private boolean checkWin(char[][] grid, char player) {
        for (int i = 0; i < 3; i++) {
            if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
                return true;
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
                return true;
            }
        }
        
        if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
            return true;
        }
        if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
            return true;
        }
        
        return false;
    }
}
