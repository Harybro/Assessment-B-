public class creature {

    private String name;
    private char symbol;
    private creaturesShape shape;

    public creature(String name, char symbol, creaturesShape shape) {
        this.name = name;
        this.symbol = symbol;
        this.shape = shape;
    }

    public char getSymbol() { return symbol; }
    public creaturesShape getShape() { return shape; }
    public String getName() { return name; }
}