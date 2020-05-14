public class UtilitySquare extends Square {
    private double multiplierValue;

    public UtilitySquare(){
        super();
        price = 0.0;
        multiplierValue = 1.0;
    }

    public UtilitySquare(String name, double price, double multiplierValue){
        super(name);
        this.price = price;
        this.multiplierValue = multiplierValue;
    }

    public double getMultiplierValue(){
        return multiplierValue;
    }
}
