import java.util.List;
import java.util.ArrayList;


public class game {
    board b = new board(10, 10); // 10×10 grid

        private List<creatures> creatureList;   

        public game() {
        creatureList = new ArrayList<>();
        creatureList.add(creatures.CreatureLibary.vshape());
        creatureList.add(creatures.CreatureLibary.circleshape());
        }

        public void setupBoards() {
            b.placeCreatureShapeRandom(creatureList.get(0));
            b.placeCreatureShapeRandom(creatureList.get(1));
        }



    public void runGame() {
        setupBoards();
        b.display();

    }
}
