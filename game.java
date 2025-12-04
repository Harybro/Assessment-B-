public class game {
    board b = new board(10, 10); // 10×10 grid
    

    public void runGame() {
        b.placeCreature(2,2,5, 'f', true);
        b.placeCreatureShape(2,2, vshape)
        b.display();

    }
}
