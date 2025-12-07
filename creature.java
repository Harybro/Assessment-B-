public class creature {

    private String name; 
    private char symbol;

    private creaturesShape shape;  // shape assigned AT PLACEMENT time
    private int totalParts;
    private int hitCount = 0;

    public creature(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
        this.shape = null;        // will be assigned when placed
        this.totalParts = 0;      // will be calculated when placed
    }

    public void assignShape(creaturesShape shape) {
        this.shape = shape;
        this.totalParts = shape.getOffsets().length;
    }

    public void addHit() {
        if (hitCount < totalParts) {
            hitCount++;
        }
    }

    public boolean isDestroyed() {
        return hitCount >= totalParts;
    }

    // getters
    public char getSymbol() { return symbol; }
    public creaturesShape getShape() { return shape; }
    public String getName() { return name; }
}
