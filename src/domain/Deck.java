package domain;

import java.io.Serializable;
import java.util.*;

public class Deck implements Serializable{
	private List<Card> cards;

	public void shuffle() {
		for (int suit = 0; suit < 4; suit++) {
			for (int value = 1; value <= 13; value++) {
				cards.add(new Card(suit, value));
			}
		}
		Collections.shuffle(cards);
	}
	
	public Deck() {
		cards = new ArrayList<>();
		shuffle();
	}

	public Card draw() {
        if (cards.isEmpty()) {
            System.out.println("댁을 셔플합니다...");
            reset();
        }
        return cards.remove(0);
    }
	
	private void reset() {
        cards.clear();
        shuffle();
    }
}
