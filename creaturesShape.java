public class creaturesShape {

    private int[][] offsets; //array of coordinate offsets defining the shape

    public creaturesShape(int[][] offsets) { //constructor
        this.offsets = offsets;
    }

    public int[][] getOffsets() { //returns the shape offsets
        return offsets;
    }

    public int size() { //returns number of parts in the shape  
        return offsets.length;
    }
}
