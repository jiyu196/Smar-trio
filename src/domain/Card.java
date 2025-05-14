package domain;

import java.io.Serializable;

public class Card implements Serializable{	
	
    private int suit;
    private int value;

    private static final String[] Suits = {"♣", "♥", "♦", "♠"};
    private static final String[] Values = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public Card(int suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    public void setValue(int value) {
		this.value = value;
	}

    public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	@Override
    public String toString() {
        return "[" + Suits[suit] + Values[value] + "]";
    }

}