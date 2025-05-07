package domain.Apps;

import service.apps.BlackjackService;
import utils.TrioUtils;

public class BlackJackApp {
	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();
		app.run();
	}

	private BlackjackService blackjackService = new BlackjackService();
	private BlackjackService.Hand playerHand;
	private BlackjackService.Hand dealerHand;

	public void run() {
		while (true) {
			System.out.println("\n< 블랙잭 >");
			System.out.println("1. 게임 시작");
			System.out.println("2. 돌아가기");
			String str = TrioUtils.nextLine("선택: ");
			if (str.equals("2")) {
				System.out.println("돌아가기");
				return;
			} else if (str.equals("1")) {
				playerHand = blackjackService.new Hand();
				dealerHand = blackjackService.new Hand();

				// 시작
				playerHand.addCard(blackjackService.drawCard());
				dealerHand.addCard(blackjackService.drawCard());
				playerHand.addCard(blackjackService.drawCard());
				dealerHand.addCard(blackjackService.drawCard());

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
				blackjackService.hit(playerHand);
				System.out.println("당신의 패: " + playerHand);
				if (blackjackService.isBust(playerHand)) {
					return false;
				}
				if (playerHand.getValue() == 21) {
					System.out.println("브랙잭! 당신이 이겼습니다!");
					return false; // 합 21 이면 승리
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
		while (blackjackService.shouldDealerHit(dealerHand)) {
			System.out.println("딜러가 히트합니다.");
			blackjackService.hit(dealerHand);
			System.out.println("딜러의 패: " + dealerHand);
			if (blackjackService.isBust(dealerHand)) {
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

		String winner = blackjackService.determineWinner(playerHand, dealerHand);
		if (winner.equals("player")) {
			System.out.println("당신이 이겼습니다!");
		} else if (winner.equals("dealer")) {
			System.out.println("딜러가 이겼습니다.");
		} else {
			System.out.println("무승부입니다.");
		}
	}
}