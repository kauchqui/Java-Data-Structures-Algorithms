package cs171;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;
import java.util.Random;

public class Card {

    // fill in code here
    // define data members

    String suits;
    String ranks;
    public static int playerScore;
    public static int dealerScore;

    public Card (String suits, String ranks) {
        // fill in code here
        // initialization
        this.suits = suits;
        this.ranks = ranks;
        //ArrayList<String> deck = new ArrayList<String>();

    }
    public String getSuit() {
        return this.suits;
    }

    public String getRank() {
        return this.ranks;
    }


    public static void buildDeck(ArrayList<Card> deck) {
        // fill in code here
        // Given an empty deck, construct a standard deck of playing cards
        deck.clear();
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(int i = 0; i<ranks.length;i++) {
            for(int j = 0; j< suits.length; j++) {
                deck.add(new Card(suits[j],ranks[i]));
            }
        }

    }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        // fill in code here
        // Deal two cards from the deck into each of the player's hand and dealer's hand
        playerHand.clear();
        dealerHand.clear();
        Random rand = new Random();
        int value1 = rand.nextInt(deck.size());
        playerHand.add(deck.get(value1));
        deck.remove(value1);

        rand = new Random();
        int value2 = rand.nextInt(deck.size());
        playerHand.add(deck.get(value2));
        deck.remove(value2);

        rand = new Random();
        int value3 = rand.nextInt(deck.size());
        dealerHand.add(deck.get(value3));
        deck.remove(value3);

        rand = new Random();
        int value4 = rand.nextInt(deck.size());
        dealerHand.add(deck.get(value4));
        deck.remove(value4);


    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
        // fill in code here
        // this should deal a single card from the deck to the hand
        Random rand = new Random();
        int value0 = rand.nextInt(deck.size());
        hand.add(deck.get(value0));
        deck.remove(value0);
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
        // fill in code here
        // This should conduct the dealer's turn and
        // Return true if the dealer busts; false otherwise
        if(dealerCheckBust1(hand)==true) {
            return true;
        }else {
            if(dealerCheckBust2(hand) == true) {
                return false;
            }else {
                while(dealerCheckBust2(hand)==false) {
                    dealOne(deck, hand);
                    if(dealerCheckBust1(hand)==true) {
                        return true;
                    }else {
                        if(dealerCheckBust2(hand)==true) {
                            return false;
                        }
                    }
                }
            }
        }
        return false; //sfsfwsfefefefef
    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        // fill in code here
        // This should return 1 if the player wins and 2 if the dealer wins
        if(checkBust(playerHand)==false) {
            if(checkBust(dealerHand)==true) {
                return 1;
            }
        }
        if(checkBust(dealerHand)==false) {
            if(checkBust(playerHand)==true) {
                return 2;
            }
        }
        if(checkBust(playerHand)==false) {
            if(checkBust(dealerHand)==false) {
                if(playerScore<=dealerScore) {
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        if(checkBust(playerHand)== true) {
            return 2;
        }
        return 0; //sdsdsfdsfvswfw
    }

    public static String displayCard(ArrayList<Card> hand){
        // fill in code here
        // Return a string describing the card which has index 1 in the hand
        String showCard = hand.get(1).getRank() + " of " + hand.get(1).getSuit();
        return showCard;
    }

    public static String displayHand(ArrayList<Card> hand){
        // fill in code here
        // Return a string listing the cards in the hand
        String showHand = "";
        for(int i = 0; i < hand.size(); i++){
            showHand += hand.get(i).getRank() + " of " + hand.get(i).getSuit() + '\n';
        }

        return showHand;
    }

    // fill in code here (Optional)
    // feel free to add methods as necessary






    public static void resetDeck(ArrayList<Card> deck){ //yao
        while(deck.size() >= 1){deck.remove(0);}
        buildDeck(deck);
    }

    public static void resetPlayerDealer(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){ //yao
        while (1 <= playerHand.size()){
            playerHand.remove(0);
        }
        while (1 <= dealerHand.size()){
            dealerHand.remove(0);
        }
    }

    public static boolean checkBust(ArrayList<Card> hand){
        // fill in code here
        // This should return whether a given hand's value exceeds 21
        String [] tempDeck = new String[52];
        int counter = 0;
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(int i = 0; i < ranks.length; i++) {
            for(int j = 0; j < suits.length; j++) {
                tempDeck[counter] = ranks[i] +" of "+ suits[j];
                counter++;
            }
        }


        String [] showHand = new String[hand.size()];
        for(int i = 0; i < hand.size(); i++){
            showHand[i] = hand.get(i).getRank() + " of " + hand.get(i).getSuit();
        }


        counter = 0;
        int total = 0;
        int score = 0;
        int numAceHand = 0;
        for(int k = 0; k < hand.size(); k++) {
            for(int i = 0; i < ranks.length;i++) {
                for(int j = 0; j < suits.length; j++) {
                    if(showHand[k].equals(tempDeck[counter])) {
                        if(i==0) {
                            numAceHand++;
                            total = total+ 11;
                        }else if(i < 10 && i>0) {
                            total = total + i+1;
                        }else {
                            total = total + 10;
                        }
                    }
                    counter++;
                }
            }
            counter=0;
            score = score + total;
            total = 0;
        }

        if(score> 21) {
            if(numAceHand>0) {
                score = score - (numAceHand-1)*10;
                System.out.println(score);
                System.out.println("aces in hand:" + numAceHand);
                playerScore = score;
                if(score>21) {
                    score = score-10;
                    playerScore = score;
                    if(score>21) {
                        return true;
                    }
                    return false;
                }
            }
            return true;
        }else {
            System.out.println(score+ "false");
            System.out.println("aces in hand:" + numAceHand);
            playerScore = score;

            return false;
        }

    }

    public static boolean dealerCheckBust1(ArrayList<Card> hand){
        // fill in code here
        // This should return whether a given hand's value exceeds 21
        String [] tempDeck = new String[52];
        int counter = 0;
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(int i = 0; i < ranks.length; i++) {
            for(int j = 0; j < suits.length; j++) {
                tempDeck[counter] = ranks[i] +" of "+ suits[j];
                counter++;
            }
        }


        String [] showHand = new String[hand.size()];
        for(int i = 0; i < hand.size(); i++){
            showHand[i] = hand.get(i).getRank() + " of " + hand.get(i).getSuit();
        }


        counter = 0;
        int total = 0;
        int score = 0;
        int numAceHand = 0;
        for(int k = 0; k < hand.size(); k++) {
            for(int i = 0; i < ranks.length;i++) {
                for(int j = 0; j < suits.length; j++) {
                    if(showHand[k].equals(tempDeck[counter])) {
                        if(i==0) {
                            numAceHand++;
                            total = total+ 11;
                        }else if(i < 10 && i>0) {
                            total = total + i+1;
                        }else {
                            total = total + 10;
                        }
                    }
                    counter++;
                }
            }
            counter=0;
            score = score + total;
            total = 0;
        }


        if(score> 21) {
            if(numAceHand>0) {
                score = score - (numAceHand-1)*10;
                System.out.println(score);
                System.out.println("aces in hand:" + numAceHand);
                dealerScore = score;
                if(score>21) {
                    score = score-10;
                    dealerScore = score;
                    if(score>21) {
                        return true;
                    }
                    return false;
                }
            }

            return true;
        }else {
            System.out.println(score+ "false");
            System.out.println("aces in hand:" + numAceHand);
            dealerScore = score;

            return false;
        }

    }

    public static boolean dealerCheckBust2(ArrayList<Card> hand){
        // fill in code here
        // This should return whether a given hand's value exceeds 21
        String [] tempDeck = new String[52];
        int counter = 0;
        String[] suits = {"spades", "hearts", "diamonds", "clubs"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
        for(int i = 0; i < 13; i++) {
            for(int j = 0; j < 4; j++) {
                tempDeck[counter] = ranks[i] +" of "+ suits[j];
                counter++;
            }
        }

        String [] showHand = new String[hand.size()];
        for(int i = 0; i < hand.size(); i++){
            showHand[i] = hand.get(i).getRank() + " of " + hand.get(i).getSuit();
        }

        counter = 0;
        int total = 0;
        int score = 0;
        int numAceHand = 0;
        for(int k = 0; k < hand.size(); k++) {
            for(int i = 0; i < ranks.length;i++) {
                for(int j = 0; j < suits.length; j++) {
                    if(showHand[k].equals(tempDeck[counter])) {
                        if(i==0) {
                            numAceHand++;
                            total = total+ 11;
    	    					/*if((total + 11) < 22) {
    		    					total = total+ 11;
    		    					numAceHand++;
    	    					}else {
    	    						total = total + 1;
    	    					}*/
                        }else if(i < 10 && i>0) {
                            total = total + (i+1);
                        }else {
                            total = total + 10;
                        }
                    }
                    counter++;
                }
            }
            counter = 0;
            score = score + total;
            total = 0;
        }

        if(score> 16) {
            System.out.println("Dealer's Turn stand");
            dealerScore = score;
            return true;
        }else {
            System.out.println("Dealer's Turn hit");
            return false;
        }

    }




    public static void main(String[] args) {

        int playerChoice, winner; // playerEndChoice; //yao
        ArrayList<Card> deck = new ArrayList<Card> ();

        buildDeck(deck);

        playerChoice = JOptionPane.showConfirmDialog(null, "Ready to Play Blackjack?", "Blackjack", JOptionPane.OK_CANCEL_OPTION);

        if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
            System.exit(0);


        Object[] options = {"Hit","Stand"};
        boolean isBusted, dealerBusted;
        boolean isPlayerTurn;
        ArrayList<Card> playerHand = new ArrayList<>();
        ArrayList<Card> dealerHand = new ArrayList<>();

        do{ // Game loop
			/*resetDeck(deck); //yao
            resetPlayerDealer(playerHand, dealerHand); //yao*/
            initialDeal(deck, playerHand, dealerHand); //yao
            isPlayerTurn=true;
            isBusted=false;
            dealerBusted=false;

            while(isPlayerTurn){

                // Shows the hand and prompts player to hit or stand
                playerChoice = JOptionPane.showOptionDialog(null,"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: " + displayHand(playerHand) + "\n What do you want to do?","Hit or Stand",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        options[0]);

                if(playerChoice == JOptionPane.CLOSED_OPTION)
                    System.exit(0);

                else if(playerChoice == JOptionPane.YES_OPTION){
                    dealOne(deck, playerHand);
                    isBusted = checkBust(playerHand);
                    if(isBusted){
                        // Case: Player Busts
                        playerChoice = JOptionPane.showConfirmDialog(null,"Player has busted!", "You lose", JOptionPane.OK_CANCEL_OPTION);

                        if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                            System.exit(0);

                        isPlayerTurn=false;
                    }
                }

                else{
                    isPlayerTurn=false;
                }
            }
            if(!isBusted){ // Continues if player hasn't busted
                dealerBusted = dealerTurn(deck, dealerHand);
                if(dealerBusted){ // Case: Dealer Busts
                    playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\nThe dealer busted.\n Congrautions!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);

                    if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                        System.exit(0);
                }


                else{ //The Dealer did not bust.  The winner must be determined
                    winner = whoWins(playerHand, dealerHand);

                    if(winner == 1){ //Player Wins
                        playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\n Congrautions!", "You Win!!!", JOptionPane.OK_CANCEL_OPTION);

                        if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                            System.exit(0);
                    }

                    else{ //Player Loses
                        playerChoice = JOptionPane.showConfirmDialog(null, "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " + displayHand(playerHand) + "\n Better luck next time!", "You lose!!!", JOptionPane.OK_CANCEL_OPTION);

                        if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                            System.exit(0);
                    }
                }
            }
		    /*playerEndChoice = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Blackjack", JOptionPane.OK_CANCEL_OPTION); //yao

            if((playerEndChoice == JOptionPane.CLOSED_OPTION) || (playerEndChoice == JOptionPane.CANCEL_OPTION))  //yao
                System.exit(0); //yao*/
        }while(true);
    }
}