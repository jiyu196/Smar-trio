package app;

import domain.Card;
import domain.Deck;
import domain.Hand;
import domain.PhoneUI;

import static util.TrioUtils.*;

public class HighLow extends App {
	private Deck deck;

	public HighLow(int no) {
		super(no, "하이로우");
	}

	public void run() {
		while (true) {
			deck = new Deck();

			PhoneUI.printTimeLine();
			PhoneUI.printWallpaper();
			System.out.println(" < 하이로우 > :");
			
			boolean input = nextConfirm("게임을 시작하시겠습니까? ");
			if (!input) {
				System.out.println("게임을 종료합니다.");
				break;
			}

			Card current = deck.draw();
			System.out.println("현재 카드: " + current);

			Hand player = new Hand();
			Hand dealer = new Hand();

			player.addCard(deck.draw());
			dealer.addCard(deck.draw());

			System.out.println("당신의 카드: " + player);
			System.out.println("딜러의 카드: " + dealer);

			int result = Integer.compare(player.getValue(), dealer.getValue());
			if (result > 0) {
				PhoneUI.printBorder();
				System.out.println("당신이 이겼습니다!");
				PhoneUI.printBorder();
			} 
			else if (result < 0) {
				PhoneUI.printBorder();
				System.out.println("딜러가 이겼습니다.");
				PhoneUI.printBorder();
			} 
			else {
				System.out.println("무승부!");
			}
			
			if (!nextConfirm("다시 하시겠습니까?")) {
				System.out.println("(홈 화면으로 되돌아갑니다)");
				break;
			}
		}
	}

}
