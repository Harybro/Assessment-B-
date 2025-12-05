public class creatures {
    public int[][] offsets;
    public char symbol;

    public creatures(char symbol, int[][] offsets) {
        this.symbol = symbol;
        this.offsets = offsets;
    }



    public class CreatureLibary {
        public static creatures vshape() {
            return new creatures('v', new int[][] {
                {0,0}, {0,2},
                {1,1}
        });
    }   

        public static creatures circleshape() {
            return new creatures( 'c', new int[][] {
                     {0,1},
            {1,0}, {1,1}, {1,2},
            {2,1}
            });
        }

}

}
