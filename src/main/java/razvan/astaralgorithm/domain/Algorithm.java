package razvan.astaralgorithm.domain;

import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.*;

public class Algorithm {
    private int drawCount = 0;
    private boolean foundDest = false;
    private int ROW;
    private int COL;
    private double gNew;
    private double hNew;
    private double fNew;
    private List<int[]> pathList = null;

    private MyCell[][] myCellGrid;

    public Algorithm(MyCell[][] myCellGrid) {
        this.myCellGrid = myCellGrid;
        this.ROW = myCellGrid.length;
        this.COL = myCellGrid[0].length;
    }

    public List<int[]> getPathList() {
        return pathList;
    }

    private boolean isValid(int row, int col) {
        return (row >= 0) && (row < ROW) && (col >= 0) && (col < COL);
    }

    private boolean isUnBlocked(MyCell[][] grid, int row, int col) {
        return grid[row][col].isObstacle();
    }

    private boolean isDestination(int row, int col, int[] dest) {
        return row == dest[0] && col == dest[1];
    }

    private double calculateHValue(int row, int col, int[] dest) {
        return Math.sqrt((row - dest[0]) * (row - dest[0]) + (col - dest[1]) * (col - dest[1]));
    }

    public void tracePath(MyCell[][] myCellGrid, int[] dest) {
//        System.out.println("The Path is ");

        int row = dest[0];
        int col = dest[1];

        // Create a map to store the path
        Map<int[], Boolean> path = new LinkedHashMap<>();

        // Backtrack from the destination to the source
        while (!(myCellGrid[row][col].getParent_i() == row && myCellGrid[row][col].getParent_j() == col)) {
            path.put(new int[]{row, col}, true);
            int temp_row = myCellGrid[row][col].getParent_i();
            int temp_col = myCellGrid[row][col].getParent_j();
            row = temp_row;
            col = temp_col;
        }

        path.put(new int[]{row, col}, true); // Add the source cell to the path
        pathList = new ArrayList<>(path.keySet()); // Convert the map to a list
        Collections.reverse(pathList); // Reverse the list to get the correct order

        pathList.forEach(p -> {
            if (p[0] == 2 || p[0] == 1) {
                System.out.print("-> (" + p[0] + ", " + (p[1]) + ")");
            } else {
                System.out.print("-> (" + p[0] + ", " + p[1] + ")");
            }
        });
        System.out.println();// Print the path

        drawPath(pathList, myCellGrid);


    }

    private boolean ifDestination(int i, int j, int o, int r, MyCell[][] myCellGrid, int[] dest) {
        foundDestination(o, r, myCellGrid);
        myCellGrid[o][r].setParent_i(i);
        myCellGrid[o][r].setParent_j(j);
        tracePath(myCellGrid, dest);
        foundDest = true;
        return true;
    }

    // 1st Successor (North)
    public boolean firstSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        // 1st Successor (North)
        if (isValid(i - 1, j)) {
            Background retrievedBackground = myCellGrid[i - 1][j].getVbox().getBackground();// Get the background color
            highlightSearch(i - 1, j, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i - 1, j, dest)) {
                return ifDestination(i, j, i - 1, j, myCellGrid, dest);
            } else if (!closedList[i - 1][j] && isUnBlocked(myCellGrid, i - 1, j)) {
                gNew = myCellGrid[i][j].getG() + 1;
                hNew = calculateHValue(i - 1, j, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i - 1][j].getF() == Double.POSITIVE_INFINITY
                        || myCellGrid[i - 1][j].getF() > fNew) {
                    openList.put(fNew, new int[]{i - 1, j});

                    myCellGrid[i - 1][j].setF(fNew);
                    myCellGrid[i - 1][j].setG(gNew);
                    myCellGrid[i - 1][j].setH(hNew);
                    myCellGrid[i - 1][j].setParent_i(i);
                    myCellGrid[i - 1][j].setParent_j(j);
                }

            }
        }
        return false;
    }

    //2nd Successor (South)
    public boolean secondSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i + 1, j)) {
            Background retrievedBackground = myCellGrid[i + 1][j].getVbox().getBackground();// Get the background color
            highlightSearch(i + 1, j, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i + 1, j, dest)) {
                return ifDestination(i, j, i + 1, j, myCellGrid, dest);
            } else if (!closedList[i + 1][j] && isUnBlocked(myCellGrid, i + 1, j)) {
                gNew = myCellGrid[i][j].getG() + 1;
                hNew = calculateHValue(i + 1, j, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i + 1][j].getF() == Double.POSITIVE_INFINITY || myCellGrid[i + 1][j].getF() > fNew) {
                    openList.put(fNew, new int[]{i + 1, j});

                    myCellGrid[i + 1][j].setF(fNew);
                    myCellGrid[i + 1][j].setG(gNew);
                    myCellGrid[i + 1][j].setH(hNew);
                    myCellGrid[i + 1][j].setParent_i(i);
                    myCellGrid[i + 1][j].setParent_j(j);
                }
            }
        }
        return false;
    }

    //3rd Successor (East)
    public boolean thirdSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i, j + 1)) {
            Background retrievedBackground = myCellGrid[i][j + 1].getVbox().getBackground();// Get the background color
            highlightSearch(i, j + 1, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i, j + 1, dest)) {
                return ifDestination(i, j, i, j + 1, myCellGrid, dest);
            } else if (!closedList[i][j + 1] && isUnBlocked(myCellGrid, i, j + 1)) {
                gNew = myCellGrid[i][j].getG() + 1;
                hNew = calculateHValue(i, j + 1, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i][j + 1].getF() == Double.POSITIVE_INFINITY || myCellGrid[i][j + 1].getF() > fNew) {
                    openList.put(fNew, new int[]{i, j + 1});

                    myCellGrid[i][j + 1].setF(fNew);
                    myCellGrid[i][j + 1].setG(gNew);
                    myCellGrid[i][j + 1].setH(hNew);
                    myCellGrid[i][j + 1].setParent_i(i);
                    myCellGrid[i][j + 1].setParent_j(j);
                }
            }
        }
        return false;
    }

    //4th Successor (West)
    public boolean forthSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i, j - 1)) {
            if (isDestination(i, j - 1, dest)) {
                Background retrievedBackground = myCellGrid[i][j - 1].getVbox().getBackground();// Get the background color
                highlightSearch(i, j - 1, myCellGrid, retrievedBackground);// Highlight the cell
                return ifDestination(i, j, i, j - 1, myCellGrid, dest);
            } else if (!closedList[i][j - 1] && isUnBlocked(myCellGrid, i, j - 1)) {

                gNew = myCellGrid[i][j].getG() + 1;
                hNew = calculateHValue(i, j - 1, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i][j - 1].getF() == Double.POSITIVE_INFINITY || myCellGrid[i][j - 1].getF() > fNew) {
                    openList.put(fNew, new int[]{i, j - 1});

                    myCellGrid[i][j - 1].setF(fNew);
                    myCellGrid[i][j - 1].setG(gNew);
                    myCellGrid[i][j - 1].setH(hNew);
                    myCellGrid[i][j - 1].setParent_i(i);
                    myCellGrid[i][j - 1].setParent_j(j);
                }
            }
        }
        return false;
    }

    //5th Successor (North-East)
    public boolean fifthSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i - 1, j + 1)) {
            Background retrievedBackground = myCellGrid[i - 1][j + 1].getVbox().getBackground();// Get the background color
            highlightSearch(i - 1, j + 1, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i - 1, j + 1, dest)) {
                return ifDestination(i, j, i - 1, j + 1, myCellGrid, dest);
            } else if (!closedList[i - 1][j + 1] && isUnBlocked(myCellGrid, i - 1, j + 1)) {
                gNew = myCellGrid[i][j].getG() + 1.414;
                hNew = calculateHValue(i - 1, j + 1, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i - 1][j + 1].getF() == Double.POSITIVE_INFINITY || myCellGrid[i - 1][j + 1].getF() > fNew) {
                    openList.put(fNew, new int[]{i - 1, j + 1});

                    myCellGrid[i - 1][j + 1].setF(fNew);
                    myCellGrid[i - 1][j + 1].setG(gNew);
                    myCellGrid[i - 1][j + 1].setH(hNew);
                    myCellGrid[i - 1][j + 1].setParent_i(i);
                    myCellGrid[i - 1][j + 1].setParent_j(j);
                }
            }
        }
        return false;
    }

    //6th Successor (North-West)
    public boolean sixthSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i - 1, j - 1)) {
            Background retrievedBackground = myCellGrid[i - 1][j - 1].getVbox().getBackground();// Get the background color
            highlightSearch(i - 1, j - 1, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i - 1, j - 1, dest)) {
                return ifDestination(i, j, i - 1, j - 1, myCellGrid, dest);
            } else if (!closedList[i - 1][j - 1] && isUnBlocked(myCellGrid, i - 1, j - 1)) {
                gNew = myCellGrid[i][j].getG() + 1.414;
                hNew = calculateHValue(i - 1, j - 1, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i - 1][j - 1].getF() == Double.POSITIVE_INFINITY || myCellGrid[i - 1][j - 1].getF() > fNew) {
                    openList.put(fNew, new int[]{i - 1, j - 1});

                    myCellGrid[i - 1][j - 1].setF(fNew);
                    myCellGrid[i - 1][j - 1].setG(gNew);
                    myCellGrid[i - 1][j - 1].setH(hNew);
                    myCellGrid[i - 1][j - 1].setParent_i(i);
                    myCellGrid[i - 1][j - 1].setParent_j(j);
                }
            }
        }
        return false;
    }

    //7th Successor (South-East)
    public boolean seventhSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i + 1, j + 1)) {
            Background retrievedBackground = myCellGrid[i + 1][j + 1].getVbox().getBackground();// Get the background color
            highlightSearch(i + 1, j + 1, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i + 1, j + 1, dest)) {
                return ifDestination(i, j, i + 1, j + 1, myCellGrid, dest);
            } else if (!closedList[i + 1][j + 1] && isUnBlocked(myCellGrid, i + 1, j + 1)) {
                gNew = myCellGrid[i][j].getG() + 1.414;
                hNew = calculateHValue(i + 1, j + 1, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i + 1][j + 1].getF() == Double.POSITIVE_INFINITY || myCellGrid[i + 1][j + 1].getF() > fNew) {
                    openList.put(fNew, new int[]{i + 1, j + 1});

                    myCellGrid[i + 1][j + 1].setF(fNew);
                    myCellGrid[i + 1][j + 1].setG(gNew);
                    myCellGrid[i + 1][j + 1].setH(hNew);
                    myCellGrid[i + 1][j + 1].setParent_i(i);
                    myCellGrid[i + 1][j + 1].setParent_j(j);
                }
            }
        }
        return false;
    }

    //8th Successor (South-West)
    public boolean eightSuccessor(int i, int j, MyCell[][] myCellGrid, boolean[][] closedList, Map<Double, int[]> openList, int[] dest, int count) {
        if (isValid(i + 1, j - 1)) {
            Background retrievedBackground = myCellGrid[i + 1][j - 1].getVbox().getBackground();// Get the background color
            highlightSearch(i + 1, j - 1, myCellGrid, retrievedBackground);// Highlight the cell
            if (isDestination(i + 1, j - 1, dest)) {
                return ifDestination(i, j, i + 1, j - 1, myCellGrid, dest);
            } else if (!closedList[i + 1][j - 1] && isUnBlocked(myCellGrid, i + 1, j - 1)) {
                gNew = myCellGrid[i][j].getG() + 1.414;
                hNew = calculateHValue(i + 1, j - 1, dest);
                fNew = gNew + hNew;

                if (myCellGrid[i + 1][j - 1].getF() == Double.POSITIVE_INFINITY || myCellGrid[i + 1][j - 1].getF() > fNew) {
                    openList.put(fNew, new int[]{i + 1, j - 1});

                    myCellGrid[i + 1][j - 1].setF(fNew);
                    myCellGrid[i + 1][j - 1].setG(gNew);
                    myCellGrid[i + 1][j - 1].setH(hNew);
                    myCellGrid[i + 1][j - 1].setParent_i(i);
                    myCellGrid[i + 1][j - 1].setParent_j(j);
                }
            }
        }
        return false;
    }

    public void printGrid(MyCell[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].isObstacle()) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public void aStarSearch(int[] src, int[] dest, int count) {

        drawCount = count;

        if (!isValid(src[0], src[1])) {
            System.out.println("Source is invalid");
            return;
        }

        if (!isValid(dest[0], dest[1])) {
            System.out.println("Destination is invalid");
            return;
        }

        if (!isUnBlocked(myCellGrid, src[0], src[1]) || !isUnBlocked(myCellGrid, dest[0], dest[1])) {
            System.out.println("Source or the destination is blocked");
            return;
        }

        if (isDestination(src[0], src[1], dest)) {
            System.out.println("We are already at the destination");
            return;
        }

        boolean[][] closedList = new boolean[ROW][COL];

        // Initialize the cells
        int i = src[0], j = src[1];
        myCellGrid[i][j].setF(0);
        myCellGrid[i][j].setG(0);
        myCellGrid[i][j].setH(0);
        myCellGrid[i][j].setParent_i(i);
        myCellGrid[i][j].setParent_j(j);

        Map<Double, int[]> openList = new HashMap<>();  // Create an open list to store the cells to be evaluated
        openList.put(0.0, new int[]{i, j}); // Add the starting cell to the open list

        while (!openList.isEmpty()) {
            Map.Entry<Double, int[]> p = openList.entrySet().iterator().next(); // Get the cell with the lowest f value
            openList.remove(p.getKey());// Remove this vertex from the open list

            i = p.getValue()[0];
            j = p.getValue()[1];
            closedList[i][j] = true;

            highlightSearchedCell(i, j, myCellGrid);// Highlight the cell

            // 1st Successor (North)
            if (firstSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 2nd Successor (South)
            if (secondSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 3rd Successor (East)
            if (thirdSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 4th Successor (West)
            if (forthSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 5th Successor (North-East)
            if (fifthSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 6th Successor (North-West)
            if (sixthSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 7th Successor (South-East)
            if (seventhSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
            // 8th Successor (South-West)
            if (eightSuccessor(i, j, myCellGrid, closedList, openList, dest, count)) return;
        }


        if (!foundDest) {
            System.out.println("Failed to find the destination cell");
        }
    }

    public void drawPath(List<int[]> path, MyCell[][] grid) {
        for (int[] cell : path) {
            //iterate through MyCell[][] grid and find the cell with the same row and col as the cell in path
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    int finalI = i;
                    int finalJ = j;
                    if (grid[i][j].getRow() == cell[0] && grid[i][j].getCol() == cell[1]) {
                        Platform.runLater(()->{
                            grid[finalI][finalJ].getVbox().setStyle("-fx-background-color: #00ff00");
                            grid[finalI][finalJ].getLabel().setText(String.valueOf(drawCount));
                            grid[finalI][finalJ].getLabel().setTextFill(Color.BLACK);
                            grid[finalI][finalJ].getLabel().setStyle("-fx-font-weight: bold");
                        });
                    }
                }
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void highlightSearch(int p, int q, MyCell[][] myCellGrid, Background background) {
        try {
            Platform.runLater(() -> {
                // Set the background color
                BackgroundFill backgroundFill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
                Background newBackground = new Background(backgroundFill);
                myCellGrid[p][q].getVbox().setBackground(newBackground);
            });

            Thread.sleep(100);

            backToOriginalColor(p, q, myCellGrid, background);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void highlightSearchedCell(int p, int q, MyCell[][] myCellGrid) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Platform.runLater(() -> {
            // Set the background color
            BackgroundFill backgroundFill = new BackgroundFill(Color.RED, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
            Background newBackground = new Background(backgroundFill);
            myCellGrid[p][q].getVbox().setBackground(newBackground);
        });
    }




    private void backToOriginalColor(int p, int q, MyCell[][] myCellGrid, Background background) {
        Platform.runLater(() -> {
            myCellGrid[p][q].getVbox().setBackground(background);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void foundDestination(int p, int q, MyCell[][] myCellGrid) {
        Platform.runLater(() -> {
            // Set the background color
            BackgroundFill backgroundFill = new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY);
            Background newBackground = new Background(backgroundFill);
            myCellGrid[p][q].getVbox().setBackground(newBackground);
        });
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
