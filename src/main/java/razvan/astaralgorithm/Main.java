package razvan.astaralgorithm;

import razvan.astaralgorithm.Domain.Algorithm;

public class Main {
    int[][] grid = {
            {1, 1, 1, 0, 1, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
            {1, 1, 0, 0, 1, 0, 0, 0, 0, 1},
            {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 1, 0, 0, 0, 1, 1, 1, 0},
            {1, 1, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 1, 0, 0, 0, 1, 1, 1},
            {1, 1, 0, 0, 1, 1, 0, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
            {1, 1, 0, 1, 1, 0, 0, 1, 1, 0}
    };

    public void printGrid(){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid.length; j++){
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.printGrid();

        // Source is the left-most bottom-most corner
        int[] src = {4, 0};

        // Destination is the left-most top-most corner
        int[] dest = {9, 1};

        Algorithm algorithm = new Algorithm(main.getGrid());

        algorithm.aStarSearch(src, dest);
    }
}
