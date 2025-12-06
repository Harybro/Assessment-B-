 public class ShapeLibrary {

        public static creaturesShape vShape() {
            return new creaturesShape(new int[][] {
                {1,0}, {1,2},
                {2,1}
            });
        }

    public static creaturesShape circle() {
        return new creaturesShape(new int[][] {
            {0,1},
            {1,0}, {1,2},
            {2,1}
        });
    }

    public static creaturesShape line() {
        return new creaturesShape(new int[][] {
            {0,0},
            {1,0},
            {2,0}
        });
    }

    public static creaturesShape horizontalLine() {
        return new creaturesShape(new int[][] {
            {0,0}, {0,1}, {0,2}
        });
    }
}