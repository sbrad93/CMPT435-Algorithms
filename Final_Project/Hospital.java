class Hospital {

    private int capacity = 0;
    private String name = null;

    public Hospital(String _name, int _capacity) {
        this.capacity = _capacity;
        this.name = _name;
    }

    public int getCapactiy() {
        return this.capacity;
    }

    public String getName() {
        return this.name;
    }
}