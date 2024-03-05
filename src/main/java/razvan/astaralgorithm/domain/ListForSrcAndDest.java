package razvan.astaralgorithm.domain;

import java.util.ArrayList;
import java.util.List;

public class ListForSrcAndDest {
    private final List<int[]> srcAndDestList;

    private boolean isFull;

    public ListForSrcAndDest() {
        this.srcAndDestList = new ArrayList<>(2);
        initialize();
    }

    public void initialize(){
        srcAndDestList.add(new int[]{-1, -1});
        srcAndDestList.add(new int[]{-1, -1});
        isFull = false;
    }

    public void add(int[] coordinates){
        if(srcAndDestList.get(0)[0] == -1 && srcAndDestList.get(0)[1] == -1){
            srcAndDestList.set(0, coordinates);
        }
        else if(srcAndDestList.get(1)[0] == -1 && srcAndDestList.get(1)[1] == -1){
            srcAndDestList.set(1, coordinates);
            isFull = true;
        }else if(srcAndDestList.get(0)[0] != -1 && srcAndDestList.get(0)[1] != -1 && srcAndDestList.get(1)[0] != -1 && srcAndDestList.get(1)[1] != -1){
            srcAndDestList.set(0, coordinates);
            srcAndDestList.set(1, new int[]{-1, -1});
            isFull = false;
        }
    }

    public boolean isFull() {
        return isFull;
    }

    public int[] getElemAtIndex(int index){
        return srcAndDestList.get(index);
    }

    public List<int[]> getList() {
        return srcAndDestList;
    }

    public void print(){
        System.out.println(String.valueOf(this.getElemAtIndex(0)[0]) + " " + String.valueOf(this.getElemAtIndex(0)[1]));
        System.out.println(String.valueOf(this.getElemAtIndex(1)[0]) + " " +  String.valueOf(this.getElemAtIndex(1)[1]));
    }
}
