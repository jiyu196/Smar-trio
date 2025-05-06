package service.apps;

import java.util.*;
import service.ConsoleUIService;
import service.ConsoleUIService.UIStyle;
import utils.TrioUtils;

public class BlackJackApp {
    public static void main(String[] args) {
        BlackJackApp app = new BlackJackApp();
        app.run();
    }

    private List<Card> deck;
    private List<Card> playerHand;
    private List<Card> dealerHand;

    public void run() {
        while (true) {
            deck = createDeck();
            Collections.shuffle(deck);
            playerHand = new ArrayList<>();
            dealerHand = new ArrayList<>();

            playerHand.add(drawCard());
            dealerHand.add(drawCard());
            playerHand.add(drawCard());
            dealerHand.add(drawCard());

            List<String> gameText = new ArrayList<>();
//            System.out.println("딜러의 패: " + dealerHand.get(0) + " [?]");
            gameText.add("딜러의 패: " + dealerHand.get(0) + " [?]");
//            System.out.println("당신의 패: " + playerHand + " (총합: " + handValue(playerHand) + ")");
            gameText.add("당신의 패: " + playerHand + "     (총합: " + handValue(playerHand) + ")");
            ConsoleUIService.printFrame("블랙잭" , gameText, UIStyle.Full);

            if (!playerTurn()) {
                ConsoleUIService.printFrame("게임 결과", List.of("당신이 버스트하여 졌습니다."), UIStyle.Full);
                if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? (y/n)")) {
                    ConsoleUIService.printFrame("종료", List.of("게임이 종료되었습니다."), UIStyle.Full);
                    return;
                }
                continue;
            }

            dealerTurn();
            determineWinner();

            if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? (y/n)")) {
                ConsoleUIService.printFrame("종료", List.of("게임이 종료되었습니다."), UIStyle.Full);
                return;
            }
        }
    }

    private boolean playerTurn() {
        while (true) {
            String move = TrioUtils.nextLine("'히트(hit)' 또는 '스탠드(stand)'를 선택하세요: ").toLowerCase();
            if (move.equals("hit") || move.equals("히트")) {
                playerHand.add(drawCard());
                System.out.println("당신의 패: " + playerHand + " (총합: " + handValue(playerHand) + ")");
                if (handValue(playerHand) > 21) {
                	ConsoleUIService.printFrame("버스트!", List.of("당신이 졌습니다."), UIStyle.Full);
//                    System.out.println("버스트! 당신이 졌습니다.");
                    return false;
                }
            } else if (move.equals("stand") || move.equals("스탠드")) {
                return true;
            } else {
            	ConsoleUIService.printFrame("입력 오류", List.of("잘못된 입력입니다. 다시 시도해주세요."), UIStyle.Full);
//                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    private void dealerTurn() {
    	List<String> text = new ArrayList<>();
    	text.add("딜러의 전체 패: " + dealerHand + " (총합: " + handValue(dealerHand) + ")");
    	ConsoleUIService.printFrame("딜러 턴 시작", text, UIStyle.Full);
//        System.out.println("딜러의 전체 패: " + dealerHand + " (총합: " + handValue(dealerHand) + ")");
        while (handValue(dealerHand) < 17) {
            Card newCard = drawCard();
            dealerHand.add(newCard);
            text = new ArrayList<>();
            text.add("딜러가 카드를 뽑았습니다: " + newCard);
            text.add("딜러의 패: " + dealerHand + " (총합: " + handValue(dealerHand) + ")");
            ConsoleUIService.printFrame("딜러 HIT", text, UIStyle.Full);
//            System.out.println("딜러가 카드를 뽑았습니다: " + newCard);
//            System.out.println("딜러의 패: " + dealerHand + " (총합: " + handValue(dealerHand) + ")");
        }
    }

	private void determineWinner() {
		int playerTotal = handValue(playerHand);
		int dealerTotal = handValue(dealerHand);

		List<String> resultLines = List.of("당신의 최종 패: " + playerHand + " (총합: " + playerTotal + ")",
				"딜러의 최종 패: " + dealerHand + " (총합: " + dealerTotal + ")");
//        System.out.println("당신의 최종 패: " + playerHand + " (총합: " + playerTotal + ")");
//        System.out.println("딜러의 최종 패: " + dealerHand + " (총합: " + dealerTotal + ")");
		ConsoleUIService.printFrame("최종 결과", resultLines, UIStyle.Full);

		if (dealerTotal > 21 || playerTotal > dealerTotal) {
			ConsoleUIService.printFrame("승리!", List.of("당신이 이겼습니다!"), UIStyle.Full);
//			System.out.println("당신이 이겼습니다!");
		} else if (dealerTotal == playerTotal) {
			ConsoleUIService.printFrame("무승부", List.of("무승부입니다."), UIStyle.Full);
//			System.out.println("무승부입니다.");
		} else {
			ConsoleUIService.printFrame("패배", List.of("딜러가 이겼습니다."), UIStyle.Full);
//			System.out.println("딜러가 이겼습니다.");
		}
	}

    private List<Card> createDeck() {
        List<Card> deck = new ArrayList<>();
        for (int suit = 0; suit < 4; suit++) {
            for (int value = 1; value <= 13; value++) {
                deck.add(new Card(suit, value));
            }
        }
        return deck;
    }

    private Card drawCard() {
        return deck.remove(0);
    }

    private int handValue(List<Card> hand) {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand) {
            int value = card.value;
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

    private class Card {
        int suit;
        int value;

        private final String[] Suits = {"♣", "♥", "♦", "♠"};
        private final String[] Values = {"", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        Card(int suit, int value) {
            this.suit = suit;
            this.value = value;
        }

        @Override
        public String toString() {
            return "[" + Suits[suit] + Values[value] + "]";
        }
    }

    public static Object getInstance() {
        return new BlackJackApp();
    }
}