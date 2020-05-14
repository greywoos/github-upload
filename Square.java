public class Square {
    protected String name; //store each individual name
    protected double price;
    protected double rent;

    public Square(){
        name = "Go";
    }

    public Square( String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public double getRent(){
        return rent;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public void setRent(double newRent){
        rent = newRent;
    }
}
