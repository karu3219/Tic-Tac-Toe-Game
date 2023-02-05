import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
class GamePlay extends JFrame implements ItemListener, ActionListener {
    private static final long serialVersionUID = 1L;
    //Two frames are created, one for the game and the other for the help page. Each having its own title and buttons
    JFrame fr = new JFrame();
    JFrame helperFrame = new JFrame();
    JPanel title2 = new JPanel();
    JPanel button2 = new JPanel();
    JLabel text2 = new JLabel();
    Boolean firstime = true;
    JPanel title = new JPanel();
    JPanel button = new JPanel();
    JLabel text = new JLabel();
    JButton[] arr = new JButton[9];//array of Jbuttons for the squares of the tic tac toe game
    JButton help, back;//two buttons for navigation between frames
    boolean type;//holds whether who starts first,true if you are playing and false if computer
    boolean player1;//which player is playing, true if you are playing and false if computer
    int count;//counts how many tiles are filled
    Checkbox c1, c2;//checkboxes for who starts first
    GamePlay() {
        super("tic tac toe");
        //creating two checkboxes and adding them to frame
        CheckboxGroup cbg = new CheckboxGroup();
        c1 = new Checkbox("First move by computer", cbg, false);
        c2 = new Checkbox("First move by you", cbg, false);
        c1.setBounds(70, 80, 200, 40);
        c2.setBounds(70, 150, 200, 40);
        add(c1);
        add(c2);
        c1.addItemListener(this);
        c2.addItemListener(this);
        setLayout(null);
        //setting the frame size and making it visible
        setSize(330, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    /**
     * Whenever an item state is changed(eg: state of checkbox), this method is called
     * @param ItemEvent e - indicates that an item was selected or deselected.
     */
    public void itemStateChanged(ItemEvent e) {
        //if computer is selected, type will be set to false else type will be set to true
        if (c1.getState()) {
            type = false;
        } else if (c2.getState()) {
            type = true;
        }
        //removing the checkboxes
        remove(c1);
        remove(c2);
        showButton();//call to set up new frame
    } 
    /**
     *To set up the game frame
     */
    private void showButton() {
        //setting up the frame and colors
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(700, 700);
        fr.getContentPane().setBackground(new Color(25, 25, 25));
        fr.setLayout(new BorderLayout());
        fr.setVisible(true);
        text.setBackground(new Color(25, 25, 25));
        text.setForeground(new Color(0, 0, 255));
        text.setFont(new Font("serif", Font.BOLD, 75));
        text.setHorizontalAlignment(JLabel.CENTER);
        //setting the title as Tic-Tac-Toe
        text.setText("Tic-Tac-Toe");
        text.setOpaque(true);
        title.setLayout(new BorderLayout());
        title.setBounds(0, 0, 800, 200);
        title.add(text);
        fr.add(title, BorderLayout.NORTH);
        //creating a grid of 3*3 to store 9 buttons and adding storing them in an array
        button.setLayout(new GridLayout(3, 3));
        button.setBackground(new Color(150, 150, 150));
        for (int i = 0; i < 9; i++) {
            arr[i] = new JButton();
            button.add(arr[i]);
            arr[i].setFont(new Font("serif", Font.BOLD, 120));
            arr[i].setFocusable(false);
            arr[i].addActionListener(this);
        }
        //adding a help button to navigate to helper frame
        help = new JButton("HELP");
        help.addActionListener(this);
        fr.add(help, BorderLayout.SOUTH);
        help.setFont(new Font("serif", Font.PLAIN, 30));
        fr.add(button);
        count = 0;
        //if computer makes move first, we call firstX() else firstO()
        if (type) {
            firstX();
        } else {
            firstO();
        }

    }
    /**
     *To setup the helperFrame
     */
    private void showHelp() {
        //setting up the helperFrame and colors
        helperFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        helperFrame.setSize(700, 700);
        helperFrame.getContentPane().setBackground(new Color(25, 25, 25));
        helperFrame.setLayout(new BorderLayout());
        helperFrame.setVisible(true);
        text2.setBackground(new Color(25, 25, 25));
        text2.setForeground(new Color(0, 0, 255));
        text2.setFont(new Font("serif", Font.BOLD, 75));
        text2.setHorizontalAlignment(JLabel.CENTER);
        //title will be set as HELP
        text2.setText("HELP");
        text2.setOpaque(true);
        title2.setLayout(new BorderLayout());
        title2.setBounds(0, 0, 800, 200);
        //creating a text area which contains rules and date of implementation and adding it to frame
        JTextArea textArea = new JTextArea("RULES FOR TIC-TAC-TOE\r\n" +
            "\r\n" +
            "1. The game is played on a grid that's 3 squares by 3 squares.\r\n" +
            "\r\n" +
            "2. You are X, the Computer is O. Players take turns putting their marks in empty squares.\r\n" +
            "\r\n" +
            "3. The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\r\n" +
            "\r\n" +
            "4. When all 9 squares are full, the game is over. If no player has 3 marks in a row, the game ends in a tie.");
        textArea.setFont(new Font("Serif", Font.PLAIN, 22));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        helperFrame.add(textArea);
        //adding the title and a button at bottom named back to navigate back to the game
        title2.add(text2);
        helperFrame.add(title2, BorderLayout.NORTH);
        back = new JButton("BACK TO THE GAME");
        back.setFont(new Font("serif", Font.PLAIN, 30));
        back.addActionListener(this);
        helperFrame.add(back, BorderLayout.SOUTH);
    }
    /**
     * Whenever an action is performed(eg: button clicks), this method is called
     * @param ActionEvent e - object of the component that caused the event.
     */
    public void actionPerformed(ActionEvent e) {
        //if help button is clicked the game frame will be set to invisible and helperframe will be made visible 
        if (e.getSource() == help) {
            fr.setVisible(false);
            //if its the frist time to be called, frame will be setup
            if (firstime) {
                showHelp();
                firstime = !firstime;
                return;
            }
            helperFrame.setVisible(true);
        }
        //if back button is clicked the game frame will be set to visible and helperframe will be made invisible 
        else if (e.getSource() == back) {
            helperFrame.setVisible(false);
            fr.setVisible(true);
        }
        //if any buttons are clicked in the game
        else{
            //loop through all the buttons and know which button is clicked
            for (int i = 0; i < 9; i++) {
                //executed if you are playing
                if (player1) {
                    if (e.getSource() == arr[i]) {
                        //If a button is clicked by you, it will be set to X
                        if (arr[i].getText() == "") {
                            arr[i].setForeground(new Color(54, 156, 60));
                            arr[i].setText("X");
                            player1 = false;
                            
                            count++;
                            check();
                        }
                    }
                }
                //When played by computer
                if (!player1 && !check()) {
                    //AI chooses where to place O.
                    makeMoveAI();
                }
            }
        }
    }
    /**
     * AI will compute and make the best move
     */
    public void makeMoveAI() {
        count++;//increment the counter
        //Executed when Ai is making for the first time.
        if (!type) {
            /*
            Place first O in a corner. 
            placing first "O in a corner will have more chances of winning. 
            This gives the opponent the most opportunities to make a mistake.
            */

            int pos[] = {
                0,
                2,
                6,
                8
            };//pos contains the positions of the four corners
            //The Ai chooses any of the four corners randomly as they have the best chance of winning.
            Random random = new Random();
            int x = random.nextInt(4);
            arr[pos[x]].setForeground(new Color(44, 73, 156));
            arr[pos[x]].setText("O");
            //player is changed to YOU
            player1 = true;
            //type is set to true as it should not enter this block again as this should be executed only once.
            type = true;
            return;

        }
        boolean enter = false;
        int n = one("X");
        if (n != -1) {
            //if first X is placed at position 3
            if (n == 3) {
                //set square at position 4 as O if it is empty
                if (arr[4].getText() == "") {
                    arr[4].setForeground(new Color(44, 73, 156));
                    arr[4].setText("O");
                    player1 = true;
                    check();
                    return;
                }
                //set square at position 0 as O if it is empty
                else if (arr[0].getText() == "") {
                    arr[0].setForeground(new Color(44, 73, 156));
                    arr[0].setText("O");
                    player1 = true;
                    check();
                    return;
                }
                //set square at position 6 as O if it is empty 
                else if (arr[6].getText() == "") {
                    arr[6].setForeground(new Color(44, 73, 156));
                    arr[6].setText("O");
                    player1 = true;
                    check();
                    return;
                }
            } 
            //if first X is placed at position 6
            else if (n == 6) {
                //set square at position 4 as O if it is empty
                if (arr[4].getText() == "") {
                    arr[4].setForeground(new Color(44, 73, 156));
                    arr[4].setText("O");
                    player1 = true;
                    check();
                    return;
                }
                //set square at position 7 as O if it is empty
                if (arr[7].getText() == "") {
                    arr[7].setForeground(new Color(44, 73, 156));
                    arr[7].setText("O");
                    player1 = true;
                    check();
                    return;
                } 
                //set square at position 3 as O if it is empty
                else if (arr[3].getText() == "") {
                    arr[3].setForeground(new Color(44, 73, 156));
                    arr[3].setText("O");
                    player1 = true;
                    check();
                    return;
                }
            } 
            //set square at position 4 as O if it is empty
            else if (arr[4].getText() == "") {
                arr[4].setForeground(new Color(44, 73, 156));
                arr[4].setText("O");
                player1 = true;
                check();
                return;
            } 
            //if X is in squares 1-7 set their adjacent squares to left as O
            else if (n - 1 >= 0 && n - 1 <= 8 && arr[n - 1].getText() == "") {
                arr[n - 1].setForeground(new Color(44, 73, 156));
                arr[n - 1].setText("O");
                player1 = true;
                check();
                return;

            } 
            //if X is in squares 3-8 set their above squares as O
            else if (n - 3 >= 0 && n - 3 <= 8 && arr[n - 3].getText() == "") {
                arr[n - 3].setForeground(new Color(44, 73, 156));
                arr[n - 3].setText("O");
                player1 = true;
                check();
                return;
            } 
            //if X is in squares 0-7 set their adjacent squares to right as O
            else if (n + 1 >= 0 && n + 1 <= 8 && arr[n + 1].getText() == "") {
                arr[n + 1].setForeground(new Color(44, 73, 156));
                arr[n + 1].setText("O");
                player1 = true;
                
                check();
                return;
            } 
            //if X is in squares 0-5 set their below squares as O
            else if (n + 3 >= 0 && n + 3 <= 8 && arr[n + 3].getText() == "") {
                arr[n + 3].setForeground(new Color(44, 73, 156));
                arr[n + 3].setText("O");
                player1 = true;
                
                check();
                return;
            }
        } 
        //If there are more than one X's in the game
        else {
            //We will check if there is any possibility of O winning and place O at a place which has a chance of winning
            for (int i = 0; i < 9; i++) {
                if (arr[i].getText() == "" && is_Win(i, "O")) {
                    arr[i].setForeground(new Color(44, 73, 156));
                    arr[i].setText("O");
                    player1 = true;
                    check();
                    enter = true;
                    break;
                }
            }
            //if there is no possibility of O winning, place O to stop X from winning(if there is chance of X winning)
            if (!enter) {
                for (int i = 0; i < 9; i++) {
                    if (arr[i].getText() == "" && is_Win(i, "X")) {
                        arr[i].setForeground(new Color(44, 73, 156));
                        arr[i].setText("O");
                        player1 = true;
                        check();
                        enter = true;
                        break;
                    }
                }
            }
            //if X and O have no chance of winning with one move, place O adjacent or diagonally to another O, so that it may increase its chance of winning in the future.
            if (!enter) {
                for (int i = 0; i < 9; i++) {
                    if (arr[i].getText() == "" && is_Win(i, "")) {
                        arr[i].setForeground(new Color(44, 73, 156));
                        arr[i].setText("O");
                        player1 = true;                        
                        check();
                        enter = true;
                        break;
                    }
                }
            }
            //if the adjacents or diagonals of O are filled, if there is no chance, then randomly put an O at empty box
            if (!enter) {
                for (int i = 0; i < 9; i++) {
                    if (arr[i].getText() == "") {
                        arr[i].setForeground(new Color(44, 73, 156));
                        arr[i].setText("O");
                        player1 = true;                        
                        check();
                        break;
                    }
                }
            }
        }
    }
    /**
     * Whenever an item state is changed(eg: state of checkbox), this method is called
     * @param String s - string for which index is to be known.
     * @return int - -1 if the count of s i greater than 1, index if the count of s id 1
     */
    public int one(String s) {
        int cnt = 0;
        int ind = -1;
        //count of s
        for (int i = 0; i < 9; i++) {
            if (arr[i].getText() == s) {
                cnt++;
                ind = i;
            }
        }
        return cnt == 1 ? ind : -1;
    }
    public boolean is_Win(int i, String s) {
        /*
        checking the chance of winning for s, if we put an "s" at 0. i.e., 
        we check the horizontal case 0,1,2 and diagonal case 0,4,8 and vertical case 0,3,6 
        */
        if (i == 0) {
            if ((arr[1].getText() == s && arr[2].getText() == s) ||
                (arr[4].getText() == s && arr[8].getText() == s) || (arr[3].getText() == s && arr[6].getText() == s)) {
                return true;
            }
        }
        /*
        checking the chance of winning for s, if we put an "s" at 1. i.e., 
        we check the horizontal case 0,1,2, and vertical case 1,4,7 
        */ 
        else if (i == 1) {
            if ((arr[4].getText() == s && arr[7].getText() == s) ||
                (arr[0].getText() == s && arr[2].getText() == s)) {
                return true;
            }
        }
        /*
        checking the chance of winning for s, if we put an "s" at 2. i.e., 
        we check the horizontal case 0,1,2, and diagonal case 2,4,6 and vertical case 2,5,8 
        */         
        else if (i == 2) {
            if ((arr[1].getText() == s && arr[0].getText() == s) ||
                (arr[4].getText() == s && arr[6].getText() == s) || (arr[5].getText() == s && arr[8].getText() == s)) {
                return true;
            }
        } 
        /*
        checking the chance of winning for s, if we put an "s" at 3. i.e., 
        we check the horizontal case 3,4,5, and vertical case 0,3,6 
        */ 
        else if (i == 3) {
            if ((arr[0].getText() == s && arr[6].getText() == s) ||
                (arr[4].getText() == s && arr[5].getText() == s)) {
                return true;
            }
        }/*
        checking the chance of winning for s, if we put an "s" at 4. i.e., 
        we check the horizontal case 3,4,5, and diagonal case 0,4,8 and vertical case 1,4,7 
        */       
        else if (i == 4) {
            if ((arr[3].getText() == s && arr[5].getText() == s) ||
                (arr[1].getText() == s && arr[7].getText() == s) || (arr[0].getText() == s && arr[8].getText() == s) ||
                (arr[2].getText() == s && arr[6].getText() == s)) {
                return true;
            }
        } 
        /*
        checking the chance of winning for s, if we put an "s" at 5. i.e., 
        we check the horizontal case 3,4,5, and vertical case 2,5,8 
        */ 
        else if (i == 5) {
            if ((arr[2].getText() == s && arr[8].getText() == s) ||
                (arr[3].getText() == s && arr[4].getText() == s)) {
                return true;
            }
        } 
        /*
        checking the chance of winning for s, if we put an "s" at 6. i.e., 
        we check the horizontal case 6,7,8, and diagonal case 2,4,6 and vertical case 0,3,6 
        */      
        else if (i == 6) {
            if ((arr[0].getText() == s && arr[3].getText() == s) ||
                (arr[4].getText() == s && arr[2].getText() == s) || (arr[7].getText() == s && arr[8].getText() == s)) {
                return true;
            }
        } 
        /*
        checking the chance of winning for s, if we put an "s" at 7. i.e., 
        we check the horizontal case 6,7,8, and vertical case 1,4,7 
        */ 
        else if (i == 7) {
            if ((arr[6].getText() == s && arr[8].getText() == s) ||
                (arr[1].getText() == s && arr[4].getText() == s)) {
                return true;
            }
        } 
        /*
        checking the chance of winning for s, if we put an "s" at 8. i.e., 
        we check the horizontal case 6,7,8, and diagonal case 0,4,8 and vertical case 2,5,8 
        */      
        else if (i == 8) {
            if ((arr[0].getText() == s && arr[4].getText() == s) ||
                (arr[2].getText() == s && arr[5].getText() == s) || (arr[7].getText() == s && arr[6].getText() == s)) {
                return true;
            }
        }
        return false;
    }
    /**
     * You will make the first move
     */
    public void firstX() {
        player1 = true;
    }
    /**
     * Ai will make the first move
     */
    public void firstO() {
        player1 = false;
        makeMoveAI();
    }
    /**
     * checks the result of the game
     *@return boolean - false if game is yet to complete, true if the game is completed
     */
    public boolean check() {
        //checking diagonally, horizontally, vertically whether X or O is winning and their respective method is called if they are winning
        if ((arr[0].getText() == "X") && (arr[1].getText() == "X") && (arr[2].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[3].getText() == "X") && (arr[4].getText() == "X") && (arr[5].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[6].getText() == "X") && (arr[7].getText() == "X") && (arr[8].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[0].getText() == "X") && (arr[3].getText() == "X") && (arr[6].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[1].getText() == "X") && (arr[4].getText() == "X") && (arr[7].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[2].getText() == "X") && (arr[5].getText() == "X") && (arr[8].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[0].getText() == "X") && (arr[4].getText() == "X") && (arr[8].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[2].getText() == "X") && (arr[4].getText() == "X") && (arr[6].getText() == "X")) {
            xWins();
            return true;
        }
        if ((arr[0].getText() == "O") && (arr[1].getText() == "O") && (arr[2].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[3].getText() == "O") && (arr[4].getText() == "O") && (arr[5].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[6].getText() == "O") && (arr[7].getText() == "O") && (arr[8].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[0].getText() == "O") && (arr[3].getText() == "O") && (arr[6].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[1].getText() == "O") && (arr[4].getText() == "O") && (arr[7].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[2].getText() == "O") && (arr[5].getText() == "O") && (arr[8].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[0].getText() == "O") && (arr[4].getText() == "O") && (arr[8].getText() == "O")) {
            oWins();
            return true;
        }
        if ((arr[2].getText() == "O") && (arr[4].getText() == "O") && (arr[6].getText() == "O")) {
            oWins();
            return true;
        }
        //If it is a tie, all the buttons are disabled and the game is stopped.
        if (count == 9) {
            for (int i = 0; i < 9; i++) {
                arr[i].setEnabled(false);
            }
            text.setText("Tie");
            return true;
        }
        //return false if game is yet to complete
        return false;
    }
    /**
     * Sets the text as X won
     */
    public void xWins() {
        //all the buttons are disabled and title is set as X wins
        for (int i = 0; i < 9; i++) {
            arr[i].setEnabled(false);
        }
        text.setText("X wins");
    }
    /**
     * Sets the text as O won
     */
    public void oWins() {
        //all the buttons are disabled and the title is set as O wins
        for (int i = 0; i < 9; i++) {
            arr[i].setEnabled(false);
        }
        text.setText("O wins");
    }
}
