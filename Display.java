import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Display implements  Cards{

    private int dice1;
    private int dice2;
    private int diceT;
    private int turn = 0;
    private int playerturn = 0;
    private double utilityRent;
    private String gameInfo1;
    private String gameInfo2;
    private String gameInfo4;
    private String gameInfo5;
    private String gameInfo6;
    private String gameInfo7;
    private String playerInfo1;
    private String playerInfo2;
    private String playerInfo3;
    private String playerInfo4;
    private String player1Position;
    private String player2Position;
    private String player3Position;
    private String player4Position;
    private String showTurn;
    private String allInfo;
    private Square currentSquare;
    private Player currentPlayer;
    private Player jailedPlayer;

    public Display(){
        Monopoly mono = new Monopoly();
        Square[] squares = mono.getSquare();
        Player[] players = mono.getPlayer();


        //Window
        JFrame frame = new JFrame("Monopoly");
        frame.setTitle("Monopoly");
        frame.setPreferredSize(new Dimension(1200, 750));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);


        //panel that holds board and stuff
        JPanel panel1 = new JPanel(new BorderLayout());

        //gameboard
        JLayeredPane board = new JLayeredPane();
        board.setPreferredSize(new Dimension(700,700));
        ImageIcon img = new ImageIcon(Display.class.getResource("gameboard1.jpg"));
        JLabel pic = new JLabel(img);
        pic.setVerticalAlignment(1);
        pic.setHorizontalAlignment(0);
        pic.setSize(new Dimension(700, 700));
        board.add(pic, JLayeredPane.DEFAULT_LAYER);
        //player icons
        //p1
        ImageIcon img2 = new ImageIcon(Display.class.getResource("car1.png"));
        JLabel pic2 = new JLabel(img2);
        pic2.setVerticalAlignment(0);
        pic2.setHorizontalAlignment(0);
        pic2.setSize(new Dimension(65, 37));
        board.add(pic2, 0);
        pic2.setLocation(625, 625);
        //p2
        ImageIcon img3 = new ImageIcon(Display.class.getResource("bin1.png"));
        JLabel pic3 = new JLabel(img3);
        pic3.setVerticalAlignment(0);
        pic3.setHorizontalAlignment(0);
        pic3.setSize(new Dimension(43, 55));
        board.add(pic3, 0);
        pic3.setLocation(625, 625);
        //p3
        ImageIcon img4 = new ImageIcon(Display.class.getResource("ship1.png"));
        JLabel pic4 = new JLabel(img4);
        pic4.setVerticalAlignment(0);
        pic4.setHorizontalAlignment(0);
        pic4.setSize(new Dimension(65, 55));
        board.add(pic4, 1);
        pic4.setLocation(625, 625);
        //p4
        ImageIcon img5 = new ImageIcon(Display.class.getResource("dog1.png"));
        JLabel pic5 = new JLabel(img5);
        pic5.setVerticalAlignment(0);
        pic5.setHorizontalAlignment(0);
        pic5.setSize(new Dimension(65, 55));
        board.add(pic5, 2);
        pic5.setLocation(625, 625);
        panel1.add(board, BorderLayout.WEST);

        //Part of display that holds buttons/player info/log etc.
        JPanel stuff = new JPanel(new BorderLayout(4, 4));

        //Buttons
        JPanel buttons = new JPanel(new GridLayout(2, 2));
        JButton roll = new JButton("Roll");
        JButton buyp = new JButton("Buy Property");
        JButton buyh = new JButton("Buy Home");
        JButton exit = new JButton("Exit");

        roll.setBackground(Color.getHSBColor(50,200,50));
        buyp.setBackground(Color.getHSBColor(50,200,50));
        buyh.setBackground(Color.getHSBColor(50,200,50));
        exit.setBackground(Color.getHSBColor(50, 200, 50));

        roll.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
        buyp.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
        buyh.setFont(new Font("Helvetica Neue", Font.BOLD, 25));
        exit.setFont(new Font("Helvetica Neue", Font.BOLD, 25));

        exit.setPreferredSize(new Dimension(150, 150));

        buttons.add(roll);
        buttons.add(buyp);
        buttons.add(buyh);
        buttons.add(exit);
        stuff.add(buttons, BorderLayout.SOUTH);

        //hold text boxes
        JPanel text = new JPanel(new GridLayout(1, 2));


        //log
        JTextArea log = new JTextArea(19,17);
        log.setBackground(Color.lightGray);
        log.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        log.setText("LOG: " + '\n');
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        JScrollPane scroll = new JScrollPane(log, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        text.add(scroll);
        JScrollBar vertical = scroll.getVerticalScrollBar();

        //player info
        JTextArea playerinfo = new JTextArea(19, 17);

        //gameinfo and player info strings
        updateText(players, squares);

        playerinfo.setLineWrap(true);
        playerinfo.setWrapStyleWord(true);
        playerinfo.setEditable(false);
        text.add(playerinfo);
        stuff.add(text, BorderLayout.NORTH);


        //set info
        setPlayerInfoText(playerinfo);

        //Roll button action
        roll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                dice1 = rand.nextInt(6) + 1;
                dice2 = rand.nextInt(6) + 1;
                diceT = dice1 + dice2;

                //show whose turn it is
                currentPlayer = players[playerturn];
                log.append("+" + currentPlayer.getName() + "'s turn: " + '\n');


                //jail check
                //if jail boolean is true and current player is in jail
                //skip turn
                if(currentPlayer == jailedPlayer){
                        playerturn++;
                        log.append("In jail. Turn Skipped." + '\n');
                        jailedPlayer = null;
                    //move scroll bar
                    vertical.setValue(vertical.getMaximum());
                }
                //else continue as normal
                else {
                    playerturn++;

                    if (playerturn == 4)
                        playerturn = 0;


                    //display dice roll
                    String diceResult = "You rolled a " + dice1 + " and a " + dice2 + '\n';
                    log.append(diceResult);


                    //updates player position and restarts count if full cycle + adds 200
                    currentPlayer.setBoardPosition(currentPlayer.getBoardPosition() + diceT);
                    if (currentPlayer.getBoardPosition() >= 40) {
                        currentPlayer.setBoardPosition(currentPlayer.getBoardPosition() - 40);
                        currentPlayer.setMoney(currentPlayer.getMoney() + 200);
                        log.append("Full cycle! +$200" + '\n');
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }

                    //set current square
                    currentSquare = squares[currentPlayer.getBoardPosition()];
                    log.append("You landed on " + currentSquare.getName() + "!" + '\n');

                    //Last resort check of player current position and money
                    System.out.println(playerturn + "'s Number Position: " + currentPlayer.getBoardPosition());
                    System.out.println(playerturn + "'s Location on the board: " + currentSquare.getName());
                    System.out.println(playerturn + "'s Money: " + currentPlayer.getMoney());
                    System.out.println("Turn number: " + turn);


                    //if the square is a property square and is owned by someone
                    //subtract rent and add it to wallet of player who owns property
                    if (currentSquare instanceof PropertySquare) {
                        payUp(log, players, currentSquare.getRent());
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                    //pay tax
                    else if (currentSquare instanceof TaxSquare) {
                        currentPlayer.setMoney(currentPlayer.getMoney() - ((TaxSquare) currentSquare).getTaxPrice());
                        log.append(currentPlayer.getName() + " has paid $" + ((TaxSquare) currentSquare).getTaxPrice() + " worth of taxes" + '\n');
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                    //railroad check
                    else if (currentSquare instanceof RailroadSquare) {
                        payUpRR(log, players, squares, currentSquare.getRent());
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                    //utility
                    else if (currentSquare instanceof UtilitySquare) {
                        utilityRent = ((UtilitySquare) currentSquare).getMultiplierValue() * diceT;
                        payUp(log, players, utilityRent);
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                    //chance
                    else if (currentSquare.getName().equals("Chance")) {
                        drawChance(log, squares);
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                    //community chest
                    else if (currentSquare.getName().equals("Community Chest")) {
                        drawCommunity(log);
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                    //go to jail square
                    else if (currentSquare.getName().equals("Go to Jail")) {
                        currentPlayer.setBoardPosition(10);
                        log.append(currentPlayer.getName() + " is going to jail" + '\n');
                        jailedPlayer = currentPlayer;
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    } else if (currentSquare.getName().equals("Free Parking")) {
                        log.append("Free Parking! $20 added to the wallet of " + currentPlayer.getName() + '\n');
                        currentPlayer.setMoney(currentPlayer.getMoney() + 20.0);
                        //move scroll bar
                        vertical.setValue(vertical.getMaximum());
                    }
                }

                //update image position
                updateImage(pic2, pic3, pic4, pic5, squares);

                //move scroll bar
                vertical.setValue(vertical.getMaximum());

                //update player info
                updateText(players, squares);
                setPlayerInfoText(playerinfo);

                //up turn count
                turn++;

                if(turn == 40){
                    displayWinner(log, players);
                    roll.setEnabled(false);
                    buyp.setEnabled(false);
                    buyh.setEnabled(false);
                    log.append("All buttons except for Exit disabled. Click Exit to close program" + '\n');
                }
            }
        });

        //Buy Property button action
        buyp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks if the square is an instance of Property square
                //checks if square has been bought
                //checks if the player has enough money to buy the property
                //subtract price of property from player wallet
                //price changes to a variable to identify player who bought property
                //update info
                if(currentSquare instanceof PropertySquare){
                    buyProperty(log);
                    //move scroll bar
                    vertical.setValue(vertical.getMaximum());
                }
                //checks if the square is an instance of Utility Square
                else if(currentSquare instanceof UtilitySquare){
                    buyProperty(log);
                    //move scroll bar
                    vertical.setValue(vertical.getMaximum());
                }
                //checks if the square is an instance of Railroad Square
                else if(currentSquare instanceof RailroadSquare){
                    buyProperty(log);
                    //move scroll bar
                    vertical.setValue(vertical.getMaximum());
                }
                else
                    log.append("This is not a buyable square" + '\n');

                //move scroll bar
                vertical.setValue(vertical.getMaximum());

                //update player info
                updateText(players, squares);
                setPlayerInfoText(playerinfo);
            }
        });

        //Buy House button action
        buyh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks if square is a property square
                //checks if property square is own buy current player
                //checks if property already has 4 houses
                //checks if player has enough money to buy houses
                //buy house and up property rent
                if(currentSquare instanceof PropertySquare) {
                    if (currentSquare.getPrice() == (double) (playerturn)) {
                        if (currentSquare.getRent() != ((PropertySquare) currentSquare).getPrice4House()) {
                            if (currentPlayer.getMoney() > 50.0) {
                                currentPlayer.setMoney(currentPlayer.getMoney() - 50.0);
                                if (currentSquare.getRent() < ((PropertySquare) currentSquare).getPrice1House()) {
                                    currentSquare.setRent(((PropertySquare) currentSquare).getPrice1House());
                                    log.append("There is now 1 house on this property" + '\n');
                                    //move scroll bar
                                    vertical.setValue(vertical.getMaximum());
                                }
                                else if (currentSquare.getRent() == ((PropertySquare) currentSquare).getPrice1House()) {
                                    log.append("There are now 2 houses on this property" + '\n');
                                    currentSquare.setRent(((PropertySquare) currentSquare).getPrice2House());
                                    //move scroll bar
                                    vertical.setValue(vertical.getMaximum());
                                }
                                else if (currentSquare.getRent() == ((PropertySquare) currentSquare).getPrice2House()) {
                                    log.append("There are now 3 houses on this property" + '\n');
                                    currentSquare.setRent(((PropertySquare) currentSquare).getPrice3House());
                                    //move scroll bar
                                    vertical.setValue(vertical.getMaximum());
                                }
                                else if (currentSquare.getRent() == ((PropertySquare) currentSquare).getPrice3House()) {
                                    log.append("There are now 4 houses (MAX) on this property" + '\n');
                                    currentSquare.setRent(((PropertySquare) currentSquare).getPrice4House());
                                    //move scroll bar
                                    vertical.setValue(vertical.getMaximum());
                                }
                            } else
                                log.append("You do not have enough money to buy a house" + '\n');
                        } else
                            log.append("There are already 4 houses on this property" + '\n');
                    } else
                        log.append("You do not own this property" + '\n');
                }
                else
                    log.append("This is not a property square" + '\n');

                //move scroll bar
                vertical.setValue(vertical.getMaximum());

                //update player info
                updateText(players, squares);
                setPlayerInfoText(playerinfo);
            }
        });

        //Exit button action
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        panel1.add(stuff, BorderLayout.EAST);
        frame.add(panel1);
        frame.pack();
        frame.setVisible(true);
    }


    //Chance cards method
    public void drawChance(JTextArea a, Square[] s) {
        Random rand = new Random();
        boolean check = true;
        int i = 0;
        int card = rand.nextInt(5) + 1;
        switch (card) {
            case 1:
                a.append("You drew the Take A Walk On The Boardwalk card" + '\n');
                a.append(currentPlayer.getName() + " moves to the Boardwalk" + '\n');
                currentPlayer.setBoardPosition(39);
                break;
            case 2:
                a.append("You drew the Advance to Illinois Ave card" + '\n');
                a.append(currentPlayer.getName() + " moves to Illinois Ave" + '\n');
                currentPlayer.setBoardPosition(24);
                break;
            case 3:
                a.append("You drew the Advance to Nearest Utility card" + '\n');
                while(check){
                    if(s[currentPlayer.getBoardPosition() + i] instanceof UtilitySquare ) {
                        currentPlayer.setBoardPosition(currentPlayer.getBoardPosition() + i);
                        check = false;
                    }
                    else if(currentPlayer.getBoardPosition() + i == 39)
                        currentPlayer.setBoardPosition(0);
                    else
                        i++;
                }
                break;
            case 4:
                a.append("You drew the Advance to Nearest Railroad card" + '\n');
                while(check){
                    if(s[currentPlayer.getBoardPosition() + i] instanceof RailroadSquare ) {
                        currentPlayer.setBoardPosition(currentPlayer.getBoardPosition() + i);
                        check = false;
                    }
                    else if(currentPlayer.getBoardPosition() + i == 39)
                        currentPlayer.setBoardPosition(0);
                    else
                        i++;
                }
                break;
            case 5:
                a.append("You drew the You Won The Lottery card" + '\n');
                a.append("$1000 added to the wallet of " + currentPlayer.getName() + '\n');
                currentPlayer.setMoney(currentPlayer.getMoney() + 1000.0);
                break;
        }
    }

    //Community cards method
    public void drawCommunity(JTextArea a) {
        Random rand = new Random();
        int card = rand.nextInt(5) + 1;

        switch (card) {
            case 1:
                a.append("You drew the Advance to Go card" + '\n');
                a.append(currentPlayer.getName() + " moves to Go" + '\n');
                currentPlayer.setBoardPosition(0);
                break;
            case 2:
                a.append("You drew the Doctor Fees card" + '\n');
                a.append(currentPlayer.getName() + " loses $100" + '\n');
                currentPlayer.setMoney(currentPlayer.getMoney() - 100.0);
                break;
            case 3:
                a.append("You drew the Go to Jail card" + '\n');
                a.append(currentPlayer.getName() + " is going to jail" + '\n');
                currentPlayer.setBoardPosition(10);
                jailedPlayer = currentPlayer;
                break;
            case 4:
                a.append("You drew the School Fees card" + '\n');
                a.append(currentPlayer.getName() + " loses $200" + '\n');
                currentPlayer.setMoney(currentPlayer.getMoney() - 200.0);
                break;
            case 5:
                a.append("You drew the PFD card" + '\n');
                a.append("$350 added to the wallet of " + currentPlayer.getName() + '\n');
                currentPlayer.setMoney(currentPlayer.getMoney() + 350.0);
                break;
        }
    }


    //buy property method
    private void buyProperty(JTextArea a){
        if(currentSquare.getPrice() < 5.0){
            a.append("Property has already been bought by Player " + currentSquare.getPrice() + '\n');
        }
        else if(currentPlayer.getMoney() >= currentSquare.getPrice()){
            currentPlayer.setMoney(currentPlayer.getMoney() - currentSquare.getPrice());
            currentSquare.setPrice((double)(playerturn));
            a.append(currentPlayer.getName() + " now owns " + currentSquare.getName() + '\n');
        }
        else
            a.append("You do not own enough money to buy this property" + '\n');
    }

    //paying other players method
    private void payUp(JTextArea a, Player[] p, double b){
        if(currentSquare.getPrice() < 5.0){
            if(currentSquare.getPrice() != (double)(playerturn)){
                currentPlayer.setMoney(currentPlayer.getMoney() - b);
                if(currentSquare.getPrice() == 1.0){
                    p[0].setMoney(p[0].getMoney() + b);
                    a.append(currentPlayer.getName() + " has paid $" + b + " to Player 1" + '\n');
                }
                else if(currentSquare.getPrice() == 2.0) {
                    p[1].setMoney(p[1].getMoney() + b);
                    a.append(currentPlayer.getName() + " has paid $" + b + " to Player 2" + '\n');
                }
                else if(currentSquare.getPrice() == 3.0) {
                    p[2].setMoney(p[2].getMoney() + b);
                    a.append(currentPlayer.getName() + " has paid $" + b + " to Player 3" + '\n');
                }
                else if(currentSquare.getPrice() == 0.0) {
                    p[3].setMoney(p[3].getMoney() + b);
                    a.append(currentPlayer.getName() + " has paid $" + b + " to Player 4" + '\n');
                }
            }
        }
    }

    //pay method for railroads
    //multiplier based on how many railroads each individual player owns
    private void payUpRR(JTextArea a, Player[] p, Square[] s, double b){
        int multiplier = 0;
        if(currentSquare.getPrice() < 5.0){
            if(currentSquare.getPrice() != (double)(playerturn)){
                if(currentSquare.getPrice() == 1.0){
                    for(int i = 0; i < 40; i++){
                        if(s[i] instanceof RailroadSquare)
                            if(s[i].getPrice() == 1.0)
                                multiplier++;
                    }
                    currentPlayer.setMoney(currentPlayer.getMoney() - (b * multiplier));
                    p[0].setMoney(p[0].getMoney() + (b * multiplier));
                    a.append(currentPlayer.getName() + " has paid $" + b * multiplier + " to Player 1" + '\n');
                }
                else if(currentSquare.getPrice() == 2.0) {
                    for(int i = 0; i < 40; i++){
                        if(s[i] instanceof RailroadSquare)
                            if(s[i].getPrice() == 2.0)
                                multiplier++;
                    }
                    currentPlayer.setMoney(currentPlayer.getMoney() - (b * multiplier));
                    p[1].setMoney(p[1].getMoney() + (b * multiplier));
                    a.append(currentPlayer.getName() + " has paid $" + b * multiplier + " to Player 2" + '\n');
                }
                else if(currentSquare.getPrice() == 3.0) {
                    for(int i = 0; i < 40; i++){
                        if(s[i] instanceof RailroadSquare)
                            if(s[i].getPrice() == 3.0)
                                multiplier++;
                    }
                    currentPlayer.setMoney(currentPlayer.getMoney() - (b * multiplier));
                    p[2].setMoney(p[2].getMoney() + (b * multiplier));
                    a.append(currentPlayer.getName() + " has paid $" + b * multiplier + " to Player 3" + '\n');
                }
                else if(currentSquare.getPrice() == 0.0) {
                    for(int i = 0; i < 40; i++){
                        if(s[i] instanceof RailroadSquare)
                            if(s[i].getPrice() == 0.0)
                                multiplier++;
                    }
                    currentPlayer.setMoney(currentPlayer.getMoney() - (b * multiplier));
                    p[3].setMoney(p[3].getMoney() + (b * multiplier));
                    a.append(currentPlayer.getName() + " has paid $" + b * multiplier + " to Player 4" + '\n');
                }
            }
        }
    }

    //quick reassignment of variables for info update
    private void updateText(Player[] p, Square[] s){
        gameInfo1 = "+ Defaults to 4 Players." + '\n';
        gameInfo2 = "+ Game ends after 40 clicks of roll (10 turns)." + '\n';
        gameInfo4 = "+ Houses can only be bought while on that specific Property Square." + '\n' ;
        gameInfo5 = "+ There are some cases where Player 4 may be displayed as Player 0.0" + '\n';
        gameInfo6 = "+ When a player is moved by a chance or community card they cannot buy property" +
                " on that turn." + '\n';
        gameInfo7 = "+ If a player is sent to jail and another player is sent to jail before the first has done their time" +
                " the first player's jail sentence is dropped." + '\n' + '\n';
        playerInfo1 = p[0].getName() + " $: " + p[0].getMoney() + '\n';
        player1Position = "Current position: " + s[p[0].getBoardPosition()].getName() + '\n';
        playerInfo2 = p[1].getName() + " $: " + p[1].getMoney() + '\n';
        player2Position = "Current position: " + s[p[1].getBoardPosition()].getName() + '\n';
        playerInfo3 = p[2].getName() + " $: " + p[2].getMoney() + '\n';
        player3Position = "Current position: " + s[p[2].getBoardPosition()].getName() + '\n';
        playerInfo4 = p[3].getName() + " $: " + p[3].getMoney() + '\n';
        player4Position = "Current position: " + s[p[3].getBoardPosition()].getName() + '\n' + '\n';
        showTurn = "Current clicks of roll: " + turn + '\n';


    }

    //set the updates variables to the display
    private void setPlayerInfoText(JTextArea a){
        allInfo = gameInfo1 + gameInfo2 + gameInfo4 + gameInfo5 + gameInfo6 + gameInfo7 + playerInfo1 + player1Position + playerInfo2 + player2Position
                + playerInfo3 + player3Position + playerInfo4 + player4Position + showTurn;
        a.setText(allInfo);
    }

    //update image location
    private void updateImage(JLabel a, JLabel b, JLabel c, JLabel d, Square[] s){
        if(currentPlayer.getName().equals("Player 1")) {
            squareCoordinate(s, a);
        }
        else if(currentPlayer.getName().equals("Player 2")){
            squareCoordinate(s, b);
        }
        else if(currentPlayer.getName().equals("Player 3")){
            squareCoordinate(s, c);
        }
        else if(currentPlayer.getName().equals("Player 4")){
            squareCoordinate(s, d);
        }
    }

    //square to x y coordinates
    private void squareCoordinate(Square[] s, JLabel a){

        if(currentSquare == s[0]){
            a.setLocation(625, 625);
        }
        else if(currentSquare == s[1]){
            a.setLocation(545, 625);
        }
        else if(currentSquare == s[2]){
            a.setLocation(490, 625);
        }
        else if(currentSquare == s[3]){
            a.setLocation(435, 625);
        }
        else if(currentSquare == s[4]){
            a.setLocation(380, 625);
        }
        else if(currentSquare == s[5]){
            a.setLocation(325, 625);
        }
        else if(currentSquare == s[6]){
            a.setLocation(270, 625);
        }
        else if(currentSquare == s[7]){
            a.setLocation(215, 625);
        }
        else if(currentSquare == s[8]){
            a.setLocation(160, 625);
        }
        else if(currentSquare == s[9]){
            a.setLocation(105, 625);
        }
        else if(currentSquare == s[10]){
            a.setLocation(25, 625);
        }
        else if(currentSquare == s[11]){
            a.setLocation(25, 545);
        }
        else if(currentSquare == s[12]){
            a.setLocation(25, 490);
        }
        else if(currentSquare == s[13]){
            a.setLocation(25, 435);
        }
        else if(currentSquare == s[14]){
            a.setLocation(25, 380);
        }
        else if(currentSquare == s[15]){
            a.setLocation(25, 325);
        }
        else if(currentSquare == s[16]){
            a.setLocation(25, 270);
        }
        else if(currentSquare == s[17]){
            a.setLocation(25, 215);
        }
        else if(currentSquare == s[18]){
            a.setLocation(25, 160);
        }
        else if(currentSquare == s[19]){
            a.setLocation(25, 105);
        }
        else if(currentSquare == s[20]){
            a.setLocation(25, 25);
        }
        else if(currentSquare == s[21]){
            a.setLocation(105, 25);
        }
        else if(currentSquare == s[22]){
            a.setLocation(160, 25);
        }
        else if(currentSquare == s[23]){
            a.setLocation(215, 25);
        }
        else if(currentSquare == s[24]){
            a.setLocation(270, 25);
        }
        else if(currentSquare == s[25]){
            a.setLocation(325, 25);
        }
        else if(currentSquare == s[26]){
            a.setLocation(380, 25);
        }
        else if(currentSquare == s[27]){
            a.setLocation(435, 25);
        }
        else if(currentSquare == s[28]){
            a.setLocation(490, 25);
        }
        else if(currentSquare == s[29]){
            a.setLocation(545, 25);
        }
        else if(currentSquare == s[30]){
            a.setLocation(625, 25);
        }
        else if(currentSquare == s[31]){
            a.setLocation(625, 105);
        }
        else if(currentSquare == s[32]){
            a.setLocation(625, 160);
        }
        else if(currentSquare == s[33]){
            a.setLocation(625, 215);
        }
        else if(currentSquare == s[34]){
            a.setLocation(625, 270);
        }
        else if(currentSquare == s[35]){
            a.setLocation(625, 325);
        }
        else if(currentSquare == s[36]){
            a.setLocation(625, 380);
        }
        else if(currentSquare == s[37]){
            a.setLocation(625, 435);
        }
        else if(currentSquare == s[38]){
            a.setLocation(625, 490);
        }
        else if(currentSquare == s[39]){
            a.setLocation(625, 545);
        }
    }

    //display winner
    private void displayWinner(JTextArea a, Player[] p){
        if(p[0].getMoney() > p[1].getMoney() && p[0].getMoney() > p[2].getMoney() && p[0].getMoney() > p[3].getMoney())
            a.append('\n' + p[0].getName() + " wins the game with $" + p[0].getMoney() + '\n');
        if(p[1].getMoney() > p[0].getMoney() && p[1].getMoney() > p[2].getMoney() && p[1].getMoney() > p[3].getMoney())
            a.append('\n' + p[1].getName() + " wins the game with $" + p[1].getMoney() + '\n');
        if(p[2].getMoney() > p[0].getMoney() && p[2].getMoney() > p[1].getMoney() && p[2].getMoney() > p[3].getMoney())
            a.append('\n' + p[2].getName() + " wins the game with $" + p[2].getMoney() + '\n');
        if(p[3].getMoney() > p[0].getMoney() && p[3].getMoney() > p[1].getMoney() && p[3].getMoney() > p[2].getMoney())
            a.append('\n' + p[3].getName() + " wins the game with $" + p[3].getMoney() + '\n');
    }
}