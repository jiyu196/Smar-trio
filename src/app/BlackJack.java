package app;

public class BlackJack {

//	private Hand playerHand; // 플레이어의 카드 패
//	private Hand dealerHand; // 딜러의 카드 패
//	private List<Card> deck; // 카드 덱
//
//	public BlackJack(int no) {
//		super(no, "블랙잭");
//		deck = createDeck(); // 덱 생성
//		Collections.shuffle(deck); // 덱 섞기
//	}
//
//	public void run() {
//		while (true) {
//			System.out.println("< 블랙잭 >");
//			System.out.println(" (1) 게임 시작");
//			System.out.println(" (2) 돌아가기");
//			int choice = TrioUtils.nextInt("선택: ");
//			switch (choice) {
//				case 1:
//					playerHand = new Hand(); // 플레이어 패 초기화
//					dealerHand = new Hand(); // 딜러 패 초기화
//					// 카드 두 장씩 배분
//					playerHand.addCard(drawCard());
//					dealerHand.addCard(drawCard());
//					playerHand.addCard(drawCard());
//					dealerHand.addCard(drawCard());
//
//					System.out.println("\n딜러의 패: " + dealerHand.getCards().get(0) + " [?]"); // 딜러 카드 한 장만 공개
//					System.out.println("당신의 패: " + playerHand);
//
//					if (!playerTurn()) { // 플레이어 턴 실행
//						System.out.println("\n버스트! 당신이 졌습니다.");
//						if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? : ")) {
//							System.out.println("게임이 종료되었습니다.");
//							return;
//						}
//						continue;
//					}
//					dealerTurn(); // 딜러 턴 실행
//					determineWinner(); // 승자 판별
//
//					if (!TrioUtils.nextConfirm("게임을 다시 하시겠습니까? : ")) {
//						System.out.println("게임이 종료되었습니다.");
//						return;
//					}
//					break;
//				case 2:
//					System.out.println("돌아가기");
//					return;
//				default:
//					System.out.println("잘못된 입력입니다.");
//					break;
//			}
//		}
//	}
//
//	private boolean playerTurn() {
//		while (true) {
//			String move = TrioUtils.nextLine("히트(1) 또는 스탠드(2): ").toLowerCase(); // 입력 받기
//			if (move.equals("hit") || move.equals("1")) {
//				hit(playerHand); // 카드 한 장 받기
//				System.out.println("당신의 패: " + playerHand);
//				if (isBust(playerHand)) { // 버스트 여부 확인
//					return false;
//				}
//				if (playerHand.getValue() == 21) {
//					System.out.println("블랙잭! 당신이 이겼습니다!");
//					return false; // 21점이면 즉시 승리
//				}
//			} else if (move.equals("stand") || move.equals("2")) {
//				return true; // 스탠드 선택 시 턴 종료
//			} else {
//				System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
//			}
//		}
//	}
//
//	private void dealerTurn() {
//		System.out.println("\n딜러의 전체 패: " + dealerHand);
//		while (shouldDealerHit(dealerHand)) { // 딜러가 히트할지 판단
//			System.out.println("딜러가 히트합니다.");
//			hit(dealerHand);
//			System.out.println("딜러의 패: " + dealerHand);
//			if (isBust(dealerHand)) {
//				System.out.println("딜러 버스트!");
//				return;
//			}
//		}
//		System.out.println("딜러가 스탠드합니다.");
//	}
//
//	private void determineWinner() {
//		System.out.println("\n--- 최종 결과 ---");
//		System.out.println("당신의 최종 패: " + playerHand);
//		System.out.println("딜러의 최종 패: " + dealerHand);
//
//		String winner = determineWinner(playerHand, dealerHand); // 승자 결정
//		if (winner.equals("player")) {
//			System.out.println("당신이 이겼습니다!");
//		} else if (winner.equals("dealer")) {
//			System.out.println("딜러가 이겼습니다.");
//		} else {
//			System.out.println("무승부입니다.");
//		}
//	}
//
//	private List<Card> createDeck() {
//		List<Card> newDeck = new ArrayList<>();
//		for (int suit = 0; suit < 4; suit++) {
//			for (int value = 1; value <= 13; value++) {
//				newDeck.add(new Card(suit, value)); // 총 52장 카드 생성
//			}
//		}
//		return newDeck;
//	}
//
//	public Card drawCard() {
//		if (deck.isEmpty()) { // 덱이 비었으면 다시 생성
//			deck = createDeck();
//			Collections.shuffle(deck);
//		}
//		return deck.remove(0); // 덱에서 카드 한 장 추출
//	}
//
//	public void hit(Hand hand) {
//		hand.addCard(drawCard()); // 핸드에 카드 추가
//	}
//
//	public boolean isBust(Hand hand) {
//		return hand.getValue() > 21; // 21 초과면 버스트
//	}
//
//	public boolean shouldDealerHit(Hand dealerHand) {
//		return dealerHand.getValue() < 17; // 딜러는 17 미만이면 히트
//	}
//
//	public String determineWinner(Hand playerHand, Hand dealerHand) {
//		int playerTotal = playerHand.getValue();
//		int dealerTotal = dealerHand.getValue();
//
//		if (dealerTotal > 21 || playerTotal > dealerTotal && playerTotal <= 21) {
//			return "player";
//		} else if (playerTotal > 21 || dealerTotal > playerTotal && dealerTotal <= 21) {
//			return "dealer";
//		} else if (playerTotal == dealerTotal) {
//			return "push"; // 무승부
//		} else {
//			return "dealer";
//		}
//	}
//
//	public class Hand {
//		private List<Card> cards = new ArrayList<>();
//
//		public void addCard(Card card) {
//			this.cards.add(card);
//		}
//
//		public List<Card> getCards() {
//			return cards;
//		}
//
//		public int getValue() {
//			int total = 0;
//			int aceCount = 0;
//
//			for (Card card : cards) {
//				int value = card.getValue();
//				if (value >= 10) { // J, Q, K는 10으로 간주
//					total += 10;
//				} else if (value == 1) { // A는 기본 11로 계산
//					total += 11;
//					aceCount++;
//				} else {
//					total += value;
//				}
//			}
//
//			while (total > 21 && aceCount > 0) { // A를 1로 변환해서 버스트 방지
//				total -= 10;
//				aceCount--;
//			}
//			return total;
//		}
//
//		@Override
//		public String toString() {
//			return cards.toString() + " (총합: " + getValue() + ")";
//		}
//	}
}
