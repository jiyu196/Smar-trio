package app;

import domain.Deck;
import domain.Hand;
import domain.PhoneUI;
import util.TrioUtils;

public class BlackJack extends App {

	public BlackJack(int no) {
		super(no, "블랙잭");
	}
	
	private final Deck deck = new Deck();
	private Hand playerHand;
	private Hand dealerHand;

	public void run() {
		while (true) {
			
			PhoneUI.printTimeLine();
			PhoneUI.printWallpaper();
            System.out.println(" < 블랙잭 > :");
            System.out.println(" (1) 게임 시작");
            System.out.println(" (0) 돌아가기");
            PhoneUI.printBorder();
            
            int choice = TrioUtils.nextInt("(옵션을 선택하세요) :");
            switch (choice) {
            case 1:
            	play();
            	if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? : ")) {
					System.out.println("게임이 종료되었습니다.");
					return;
				}
            	break;
            case 0:
            	System.out.println("(홈 화면으로 되돌아갑니다)");
            	return;
            default:
				System.out.println("잘못된 입력입니다.");
				break;
            }
        }
	}

	private void play() {
		setup();

		if (playerBlackJack()) {
			return;
		} else if (dealerBlackJack()) {
			return;
		}

		playerTurn();
		if (playerHand.isBust()) {
			System.out.println("버스트! 당신 패배");
			return;
		}

		dealerTurn();
		if (dealerHand.isBust())
			return;

		determineWinner();
	}

	private void playerTurn() {
		while (!playerHand.isBust()) {
			boolean hit = TrioUtils.nextConfirm("히트하시겠습니까?");
			if (hit) {
				playerHand.addCard(deck.draw());
				dealerHand.addCard(deck.draw());
				System.out.println("딜러의 패: " + dealerHand);
				PhoneUI.printBorder();
				System.out.println("현재 패: " + playerHand);
			} else
				break;
		}
	}

	private void dealerTurn() {
		System.out.println("딜러의 전체 패: " + dealerHand);
		while (dealerHand.getValue() < 17) {
			dealerHand.addCard(deck.draw());
			System.out.println("딜러가 히트: " + dealerHand);
		}

		if (dealerHand.isBust()) {
			System.out.println("딜러 버스트! 당신 승리");
		}
	}

	private void setup() {
		playerHand = new Hand();
		dealerHand = new Hand();

		playerHand.addCard(deck.draw());
		dealerHand.addCard(deck.draw());
		playerHand.addCard(deck.draw());
		dealerHand.addCard(deck.draw());

		System.out.println("딜러의 패: [?] [?] ");
//+ dealerHand.getCards().get(0));
		System.out.println("당신의 패: " + playerHand);
	}

	private boolean playerBlackJack() {
		if (playerHand.isBlackJack()) {
			System.out.println("블랙잭! 당신 승리!");
			return true;
		}
		return false;
	}

	private boolean dealerBlackJack() {
		if (dealerHand.isBlackJack()) {
			System.out.println("블랙잭... 딜러 승리...");
			return true;
		}
		return false;
	}

	private void determineWinner() {
		int player = playerHand.getValue();
		int dealer = dealerHand.getValue();

		System.out.println("당신: " + playerHand);
		System.out.println("딜러: " + dealerHand);

		if (player > dealer)
			System.out.println("당신 승리!");
		else if (player < dealer)
			System.out.println("딜러 승리!");
		else
			System.out.println("무승부!");
	}
}
