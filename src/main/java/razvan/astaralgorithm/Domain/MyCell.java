package razvan.astaralgorithm.Domain;

import javafx.scene.layout.VBox;

public class MyCell {
    // GUI
    private VBox vbox;
    private int row, col;
    private boolean isObstacle;

    // A* algorithm
    int parent_i, parent_j;
    double f, g, h;

    // Constructor
    public MyCell(VBox vbox, int row, int col) {
        this.vbox = vbox;
        this.row = row;
        this.col = col;
        this.isObstacle = false;
    }

    public MyCell(){}

    public MyCell(VBox vbox, int row, int col, boolean isObstacle, int parent_i, int parent_j, double f, double g, double h) {
        this.vbox = vbox;
        this.row = row;
        this.col = col;
        this.isObstacle = isObstacle;
        this.parent_i = parent_i;
        this.parent_j = parent_j;
        this.f = f;
        this.g = g;
        this.h = h;
    }

    // Getters and setters
    public VBox getVbox() {
        return vbox;
    }

    public void setVbox(VBox vbox) {
        this.vbox = vbox;
    }

    public boolean isObstacle() {
        return isObstacle;
    }

    public void setObstacle(boolean isObstacle) {
        this.isObstacle = isObstacle;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getParent_i() {
        return parent_i;
    }

    public void setParent_i(int parent_i) {
        this.parent_i = parent_i;
    }

    public int getParent_j() {
        return parent_j;
    }

    public void setParent_j(int parent_j) {
        this.parent_j = parent_j;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getG() {
        return g;
    }

    public void setG(double g) {
        this.g = g;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }
}
