package service.apps;

import java.util.*;

public class BlackJackApp {
    public static void main(String[] args) {
        BlackJackApp app = new BlackJackApp();
        app.run();
    }

    public void run() {

    }

    public static Object getInstance() {
        return null;
    }
    
    private List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (int kind = 0; kind < 4; kind++) {
            for (int num = 0; num < 13; num++) {
                deck.add(new Card(kind, num));
            }
        }
        return deck;
    }
    
    private class Card {
    	int number;
    	int type;
    	
    	Card(int kind, int number) {
    		this.type = kind;
    		this.number = number;
    	}
    	
    	public String toString() {
    		String numbers = "23456789XJQKA";
    		String kinds = "♣♥♦♠";
    		return "[" + kinds.charAt(type) + "]" + numbers.charAt(number);
    	}
    }
}
