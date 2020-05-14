public class PropertySquare extends Square {
    private String color;
    private double price1House;
    private double price2House;
    private double price3House;
    private double price4House;

    public PropertySquare(){
        super();
        color = "White";
        price = 0.0;
        rent = 0.0;
        price1House = 0.0;
        price2House = 0.0;
        price3House = 0.0;
        price4House = 0.0;
    }

    public PropertySquare(String name, String color, double price, double rent, double price1House,
            double price2House, double price3House, double price4House){
        super(name);
        this.color = color;
        this.price = price;
        this.rent = rent;
        this.price1House = price1House;
        this.price2House = price2House;
        this.price3House = price3House;
        this.price4House = price4House;
    }

    public double getPrice1House(){
        return price1House;
    }

    public double getPrice2House(){
        return price2House;
    }

    public double getPrice3House(){
        return price3House;
    }

    public double getPrice4House(){
        return price4House;
    }
}
