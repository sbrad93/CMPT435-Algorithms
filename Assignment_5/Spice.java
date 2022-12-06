class Spice {

    private String color = null;
    private float totalPrice = 0;
    private int quantity = 0;
    private float unitPrice = 0;

    public Spice(String _color, float _totalPrice, int _quantity) {
        this.color = _color;
        this.totalPrice = _totalPrice;
        this.quantity = _quantity;
        this.unitPrice = _totalPrice / _quantity;
    }

    public void print() {
        System.out.println("color: " + this.color + 
                            "\ntotal price: " + this.totalPrice + 
                            "\nquantity: " + this.quantity + 
                            "\nunit price: " + this.unitPrice + "\n");
    }

    public String getColor() {
        return this.color;
    }

    public float getTotalPrice() {
        return this.totalPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public float getUnitPrice() {
        return this.unitPrice;
    }
}