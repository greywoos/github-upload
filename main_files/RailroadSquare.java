public class RailroadSquare extends Square {

    public RailroadSquare(){
        super();
        rent = 0.0;
        price = 0.0;
    }

    public RailroadSquare(String name, double rent, double price){
        super(name);
        this.rent = rent;
        this.price = price;
    }
}
