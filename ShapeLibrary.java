public class ShapeLibrary {

    public static creaturesShape vShape() { //defines a V shape
        return new creaturesShape(new int[][] {
            {1,0}, {1,2}, {2,1}
        });
    }

    public static creaturesShape circle() { //defines a circle shape
        return new creaturesShape(new int[][] {
            {0,1}, {1,0}, {1,2}, {2,1}
        });
    }

    public static creaturesShape line() { //defines a line shape
        return new creaturesShape(new int[][] {
            {0,0}, {1,0}, {2,0}
        });
    }

    
    public static creaturesShape getShapeForSymbol(char symbol) { //returns shape based on symbol
        return switch (symbol) {
            case 'F' -> vShape();
            case 'S' -> circle();
            case 'C' -> line();
            default -> throw new IllegalArgumentException("Unknown symbol: " + symbol);
        };
    }
}
