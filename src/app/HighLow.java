package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import domain.Card;
import domain.PhoneUI;

import static util.TrioUtils.*;

public class HighLow extends App {
	private List<Card> deck;

	public HighLow(int no) {
		super(no, "하이로우");
		deck = createDeck();
		Collections.shuffle(deck);
	}

	public void run() {
		while (true) {
			PhoneUI.printTimeLine();
			PhoneUI.printWallpaper();
			System.out.println(" < 하이로우 > :");
			boolean input = nextConfirm("게임을 시작하시겠습니까? ");
			if (!input) {
				System.out.println("게임을 종료합니다.");
				break;
			}
			if (deck.size() < 2) {
				deck = createDeck();
				Collections.shuffle(deck);
				System.out.println("새로운 덱으로 셔플합니다.");
			}

			Card player = drawCard();
			Card dealer = drawCard();
			System.out.println("당신의 카드: " + player);
			System.out.println("딜러의 카드: " + dealer);

			int result = Integer.compare(player.getValue(), dealer.getValue());
			if (result > 0) {
				System.out.println("당신이 이겼습니다!");
			} 
			else if (result < 0) {
				System.out.println("딜러가 이겼습니다.");
			} 
			else {
				System.out.println("무승부!");
			}
			
			if (!nextConfirm("다시 하시겠습니까?")) {
				System.out.println("게임을 종료합니다.");
				break;
			}
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

	private Card drawCard() {
		return deck.remove(0);
	}
}
