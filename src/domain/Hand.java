package domain;

import java.io.Serializable;
import java.util.*;

public class Hand implements Serializable{
	private final List<Card> cards = new ArrayList<>();

	public void addCard(Card card) {
		cards.add(card);
	}

	public List<Card> getCards() {
		return cards;
	}

	public int getValue() {
		int total = 0;
		int aceCount = 0;
		for (Card card : cards) {
			int cardValue = card.getValue();
			if (cardValue >= 10)
				total += 10;
			else if (cardValue == 1) {
				total += 11;
				aceCount++;
			} else
				total += cardValue;
		}

		while (total > 21 && aceCount-- > 0)
			total -= 10;
		return total;
	}

	public boolean isBust() {
		int totalValue = getValue();
		if (totalValue > 21) {
			return true;
		}
		return false;
	}
	
	public boolean isBlackJack() {
		return cards.size() == 2 && getValue() == 21;
	}
	
	public String toString() {
        return cards.toString() + " (합계: " + getValue() + ")";
    }
}
