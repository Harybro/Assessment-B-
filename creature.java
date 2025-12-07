public class creature {

    //attributes
    private String name; 
    private char symbol;

    private int totalParts;
    private int hitCount;

    public creature(String name, char symbol) { //constructor
        this.name = name;
        this.symbol = symbol;
        this.totalParts = totalParts;
        this.hitCount = 0;
    }

    public void setTotalParts(int totalParts) { //sets total parts of the creature
        this.totalParts = totalParts;
    }

    public void addHit() { //increments hit count when creature is hit
        hitCount++;
    }

    public boolean isDestroyed() { //checks if creature is destroyed
        return hitCount >= totalParts;
    }

    public void setHitCount(int hits) { //sets hit count
        this.hitCount = hits;
    }

    public int getHitCount() { //returns current hit count
        return hitCount;
    }

    public int getTotalParts() { //returns total parts of the creature
        return totalParts;
    }

    public String getName() { //returns creature name
        return name;
    }

    public char getSymbol() { //returns creature symbol
        return symbol;
    }
}
