import java.text.DecimalFormat;

class Hospital {

    public static final DecimalFormat df = new DecimalFormat("0.00");

    private int capacity = 0;
    private String name = null;
    private double acceptanceRate = 0;

    public Hospital(String _name, int _capacity) {
        this.capacity = _capacity;
        this.name = _name;
        this.acceptanceRate = 0;
    }

    public void print() {
        System.out.println("Hospital: " + this.name +
                            "\nAcceptance Rate: " + df.format(this.acceptanceRate * 100));
    }

    public int getCapacity() {
        return this.capacity;
    }

    public String getName() {
        return this.name;
    }

    public double getAcceptanceRate() {
        return this.acceptanceRate;
    }

    public void setAcceptanceRate(double newRate) {
        this.acceptanceRate = newRate;
    }
}