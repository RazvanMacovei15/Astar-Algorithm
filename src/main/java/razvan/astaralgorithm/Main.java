package razvan.astaralgorithm;

import razvan.astaralgorithm.Domain.Algorithm;
import razvan.astaralgorithm.Domain.GridCreator;
import razvan.astaralgorithm.Domain.MyCell;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int row = 10;
        int col = 10;
        GridCreator gridCreator = new GridCreator(row, col);
        MyCell[][] grid = gridCreator.getMyCellGrid();

        Algorithm algorithm = new Algorithm(grid);
        algorithm.printGrid(grid);

        // Source is the left-most bottom-most corner
        int[] src = {0, 0};
        System.out.println("Enter the source and destination coordinates");
        System.out.println("Enter the source coordinates-->");
        System.out.println("Enter the x coordinate-->");
        src[0] = scanner.nextInt();
        System.out.println("Enter the y coordinate-->");
        src[1] = scanner.nextInt();


        // Destination is the left-most top-most corner
        int[] dest = {0, 0};
        System.out.println("Enter the destination coordinates-->");
        System.out.println("Enter the x coordinate-->");
        dest[0] = scanner.nextInt();
        System.out.println("Enter the y coordinate-->");
        dest[1] = scanner.nextInt();



        algorithm.aStarSearch(src, dest);
    }
}
