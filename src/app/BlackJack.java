package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Card;
import old.domain.App;
import util.TrioUtils;

public class BlackJack extends App {

	private Hand playerHand;
	private Hand dealerHand;
	private List<Card> deck;

	public BlackJack(int no) {
		super(no, "블랙잭");
		deck = createDeck();
		Collections.shuffle(deck);
	}

	public void run() {
		while (true) {
			System.out.println("< 블랙잭 >");
			System.out.println("1. 게임 시작");
			System.out.println("2. 돌아가기");
			String str = TrioUtils.nextLine("선택: ");
			if (str.equals("2")) {
				System.out.println("돌아가기");
				return;
			} else if (str.equals("1")) {
				playerHand = new Hand();
				dealerHand = new Hand();

				// 시작
				playerHand.addCard(drawCard());
				dealerHand.addCard(drawCard());
				playerHand.addCard(drawCard());
				dealerHand.addCard(drawCard());

				System.out.println("\n딜러의 패: " + dealerHand.getCards().get(0) + " [?]");
				System.out.println("당신의 패: " + playerHand);

				if (!playerTurn()) {
					System.out.println("\n버스트! 당신이 졌습니다.");
					if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? (y/n): ")) {
						System.out.println("게임이 종료되었습니다.");
						return;
					}
					continue;
				}
				dealerTurn();
				determineWinner();
				if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? (y/n): ")) {
					System.out.println("게임이 종료되었습니다.");
					return;
				}
			} else {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}

	private boolean playerTurn() {
		while (true) {
			String move = TrioUtils.nextLine("히트(1) 또는 스탠드(2): ").toLowerCase();
			if (move.equals("hit") || move.equals("1")) {
				hit(playerHand);
				System.out.println("당신의 패: " + playerHand);
				if (isBust(playerHand)) {
					return false;
				}
				if (playerHand.getValue() == 21) {
					System.out.println("블랙잭! 당신이 이겼습니다!");
					return false; // 합이 21이면 자동 승리
				}
			} else if (move.equals("stand") || move.equals("2")) {
				return true;
			} else {
				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
			}
		}
	}

	private void dealerTurn() {
		System.out.println("\n딜러의 전체 패: " + dealerHand);
		while (shouldDealerHit(dealerHand)) {
			System.out.println("딜러가 히트합니다.");
			hit(dealerHand);
			System.out.println("딜러의 패: " + dealerHand);
			if (isBust(dealerHand)) {
				System.out.println("딜러 버스트!");
				return;
			}
		}
		System.out.println("딜러가 스탠드합니다.");
	}

	private void determineWinner() {
		System.out.println("\n--- 최종 결과 ---");
		System.out.println("당신의 최종 패: " + playerHand);
		System.out.println("딜러의 최종 패: " + dealerHand);

		String winner = determineWinner(playerHand, dealerHand);
		if (winner.equals("player")) {
			System.out.println("당신이 이겼습니다!");
		} else if (winner.equals("dealer")) {
			System.out.println("딜러가 이겼습니다.");
		} else {
			System.out.println("무승부입니다.");
		}
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
