package service.apps;

import java.util.*;

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

            System.out.println("딜러의 패: " + dealerHand.get(0) + " [?]");
            System.out.println("당신의 패: " + playerHand + " (총합: " + handValue(playerHand) + ")");

            if (!playerTurn()) {
                System.out.println("결과: 당신이 버스트하여 졌습니다.");
                if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? (y/n)")) {
                    System.out.println("게임이 종료되었습니다.");
                    return;
                }
                continue;
            }

            dealerTurn();
            determineWinner();

            if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? (y/n)")) {
                System.out.println("게임이 종료되었습니다.");
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
                    System.out.println("버스트! 당신이 졌습니다.");
                    return false;
                }
            } else if (move.equals("stand") || move.equals("스탠드")) {
                return true;
            } else {
                System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
            }
        }
    }

    private void dealerTurn() {
        System.out.println("딜러의 전체 패: " + dealerHand + " (총합: " + handValue(dealerHand) + ")");
        while (handValue(dealerHand) < 17) {
            Card newCard = drawCard();
            dealerHand.add(newCard);
            System.out.println("딜러가 카드를 뽑았습니다: " + newCard);
            System.out.println("딜러의 패: " + dealerHand + " (총합: " + handValue(dealerHand) + ")");
        }
    }

    private void determineWinner() {
        int playerTotal = handValue(playerHand);
        int dealerTotal = handValue(dealerHand);

        System.out.println("당신의 최종 패: " + playerHand + " (총합: " + playerTotal + ")");
        System.out.println("딜러의 최종 패: " + dealerHand + " (총합: " + dealerTotal + ")");

        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("당신이 이겼습니다!");
        } else if (dealerTotal == playerTotal) {
            System.out.println("무승부입니다.");
        } else {
            System.out.println("딜러가 이겼습니다.");
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