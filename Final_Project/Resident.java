class Resident {

    private String name = null;
    private String firstChoice = null;

    public Resident(String _name, String _firstChoice) {
        this.name = _name;
        this.firstChoice = _firstChoice;
    }

    public String getName() {
        return this.name;
    }

    public String getFirstChoice() {
        return this.firstChoice;
    }
}