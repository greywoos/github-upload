public class Player {
    private String name;
    private double money;
    private int boardPosition;

    public Player(){
        name = "blank";
        money = 1500;
        boardPosition = 0;
    }

    public Player(String name, double money, int boardPosition){
        this.name = name;
        this.money = money;
        this.boardPosition = boardPosition;
    }

    public void setName(String newName){
        name = newName;
    }

    public void setMoney(double newMoney){
        money = newMoney;
    }

    public void setBoardPosition(int newBoardPosition){
        boardPosition = newBoardPosition;
    }

    public String getName(){
        return name;
    }

    public double getMoney(){
        return money;
    }

    public int getBoardPosition(){
        return boardPosition;
    }
}
