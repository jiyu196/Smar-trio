package service.apps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Apps.Card;

public class BlackjackService {
	private List<Card> deck;

	public BlackjackService() {
		deck = createDeck();
		Collections.shuffle(deck);
	}

	private List<Card> createDeck() {
		List<Card> newDeck = new ArrayList<>();
		for (int suit = 0; suit < 4; suit++) {
			for (int value = 1; value <= 13; value++) {
				newDeck.add(new Card(suit, value));
			}
		}
		return newDeck;
	}

	public Card drawCard() {
		if (deck.isEmpty()) {
			deck = createDeck();
			Collections.shuffle(deck);
		}
		return deck.remove(0);
	}

	public Hand dealInitialHand() {
		Hand hand = new Hand();
		hand.addCard(drawCard());
		hand.addCard(drawCard());
		return hand;
	}

	public void hit(Hand hand) {
		hand.addCard(drawCard());
	}

	public boolean isBust(Hand hand) {
		return hand.getValue() > 21;
	}

	public boolean shouldDealerHit(Hand dealerHand) {
		return dealerHand.getValue() < 17;
	}

	public String determineWinner(Hand playerHand, Hand dealerHand) {
		int playerTotal = playerHand.getValue();
		int dealerTotal = dealerHand.getValue();

		if (dealerTotal > 21 || playerTotal > dealerTotal && playerTotal <= 21) {
			return "player";
		} else if (playerTotal > 21 || dealerTotal > playerTotal && dealerTotal <= 21) {
			return "dealer";
		} else if (playerTotal == dealerTotal) {
			return "push";
		} else {
			return "dealer";
		}
	}

	public class Hand {
		private List<Card> cards = new ArrayList<>();

		public void addCard(Card card) {
			this.cards.add(card);
		}

		public List<Card> getCards() {
			return cards;
		}

		public int getValue() {
			int total = 0;
			int aceCount = 0;

			for (Card card : cards) {
				int value = card.getValue();
				if (value >= 10) {
					total += 10;
				} else if (value == 1) {
					total += 11;
					aceCount++;
				} else {
					total += value;
				}
			}

			while (total > 21 && aceCount > 0) {
				total -= 10;
				aceCount--;
			}
			return total;
		}

		@Override
		public String toString() {
			return cards.toString() + " (총합: " + getValue() + ")";
		}
	}
}