public class TaxSquare extends Square {
    private double taxPrice;

    public TaxSquare(){
        super();
        taxPrice = 0.0;
    }

    public TaxSquare(String name, double taxPrice){
        super(name);
        this.taxPrice = taxPrice;
    }

    public double getTaxPrice(){
        return taxPrice;
    }
}
