class Knapsack {

    private int capacity = 0;
    private LinkedList contents = null;
    private float value = 0;

    public Knapsack(int _capacity) {
        this.capacity = _capacity;
        this.contents = new LinkedList();
        this.value = 0;
    }

    public void print() {
        System.out.println("Knapsack of capacity " + this.capacity + 
                            " is worth " + this.value + 
                            " and contains\n" + this.getContents());
    }

    public String getContents() {
        int orangeCnt = 0;
        int blueCnt = 0;
        int greeCnt = 0;
        int redCnt = 0;
        for (int i=0; i<this.contents.getSize(); i++) {
            switch (this.contents.getAt(i).getName()) {
                case "orange":
                orangeCnt++;
                    break;
                case "blue":
                    blueCnt++;
                    break;
                case "green":
                    greeCnt++;
                    break;
                case "red":
                    redCnt++;
                    break;
            }
        }
        String contents = (orangeCnt != 0 ? orangeCnt + " scoops of orange\n" : "") +
                            (blueCnt != 0 ? blueCnt + " scoops of blue\n" : "") +
                            (greeCnt != 0 ? greeCnt + " scoops of green\n" : "") +
                            (redCnt != 0 ? redCnt + " scoops of red\n" : "");
        return contents;
    }

    public boolean isFull() {
        return this.capacity == this.contents.getSize();
    }

    public void fill(Spice spice) {
        this.contents.add(spice.getColor());
        this.updateValue(spice);
    }

    public void updateValue(Spice spice) {
        this.value += spice.getUnitPrice();
    }

    public float getValue() {
        return this.value;
    }
}