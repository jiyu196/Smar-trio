package domain;

public class MemoApp {
	
	private static class Memo {
		private final int no;
		private String title;
		private String content;
		

		public Memo(int no, String title, String content) {
			super();
			this.no = no;
			this.title = title;
			this.content = content;
		}

		public int getno() {
			return no;
		}

		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}



		@Override
		public String toString() {
			return "메모 [no=" + no + ", title=" + title + ", content=" + content + "]";
		}
		
		public static Memo fromString(String line) {
            String[] parts = line.split(";");
            if (parts.length == 5) {
                int no = Integer.parseInt(parts[0]);
                return new Memo(no, parts[1], parts[2]);
            }
            return null;
        }

	}

	public int getNo() {
		// TODO Auto-generated method stub
		return 0;
	}


//	public static void main(String[] args) {
//	MemoApp app = new MemoApp();
//	app.run();
//}
//
//private static final Path MEMO_PATH = Path.of("storage", "Memo", "memo.txt");
//private static final MemoApp memoApp = new MemoApp();
//
//
//
//private MemoApp() {
//
//}
//
//public void run() {
//	System.out.println("메모 앱을 실행합니다");
//
//	while (true) {
////		String str = TrioUtils.nextLine("1. 작성한 메모 전체보기  \n2. 메모 추가  \n3. 수정  \n4. 삭제  \n6. 종료");
//		int chack = TrioUtils.nextInt("1. 작성한 메모 전체보기  \n2. 메모 추가  \n3. 수정  \n4. 삭제  \n6. 종료");
//		switch (chack) {
//		case 1:
//			history();
//			break;
//		case 2:
//			add();
//			break;
//		case 3:
//			modify();
//			break;
//		case 4:
//			delete();
//			break;
//		case 5:
//			save();
//			break;
//		case 6:
//			System.out.println("메모장 앱이 종료되었습니다");
//			break;
//		default:
//			System.out.println("잘못된 경로입니다");
//		}
//	}
////
//}
////다시 실행했을 때 이전에 작성했던 메모까지 보일 수 있게하기.지금은 실행 할때마다 작성한게 없다고함
////수정하고 나서 전체보기 했을 때 수정된걸로 보일 수 있게하기
//
//
//
//private static MemoService memoService = new MemoService();
//
////	private MemoService() {
////	
////	}
//public static MemoService getInstance() {
//	return memoService;
//}
//
////	//추가	
//private void add() {
//	String title = TrioUtils.nextLine("제목: ");
//	String content = TrioUtils.nextLine("내용: ");
//	memos.add(new Memo(title, content));
//	save();
//	System.out.println("메모 추가 및 저장 완료");
//}
//
//// 수정- 실행 다시할 때 수정 누르면 수정할 메모가 없다고 함 이전꺼 불러와야?
//private void modify() {
//	if (memos.isEmpty()) {
//		System.out.println("수정할 메모기록이 없습니다");
//	}
//	showMemo();
//	int index = TrioUtils.nextInt("수정할 메모장을 입력하세요");
//	
//	if(index < 0 || index >= memos.size()) {
//		System.out.println("잘못된 입력입니다");
//		return;
//	}
//	
//	Memo oldmemo = memos.get(index); //메모 적었던 index에서 oldmemo를 가져옴
//	String title = updateField("제목", oldmemo.title);
//	String content = updateField("내용", oldmemo.content);
//	save();
//	
//}
//
//// 삭제
//private void delete() {
//	if(memos.isEmpty()) {
//		System.out.println("삭제할 메모가 없습니다");
//		return;
//	}
//	showMemo();
//	int index = TrioUtils.nextInt("삭제할 메모장 번호를 입력하세요: ");
//}
//
//// 작성 기록- 메모 리스트
//private void history() {
//	System.out.println("작성한 메모");
//	try {
//		if (!Files.exists(MEMO_PATH)) {
//			System.out.println("작성한 메모가 없습니다");
//			return;
//		}
//		try (BufferedReader reader = Files.newBufferedReader(MEMO_PATH)) {
//			String line;
//			while ((line = reader.readLine()) != null) {
//				System.out.println(line);
//			}
//		}
//	} catch (IOException e) {
//		System.out.println("작성한 메모를 불러오지 못했습니다:" + e.getMessage());
//	}
//}
////메모 보여주기
//private void showMemo() {
//	if (memos.isEmpty()) {
//		System.out.println("메모가 비어있습니다");
//		return;
//	}
//	for (int i = 1; i < memos.size(); i++) {
//		Memo m = memos.get(i);
//		System.out.printf("메모 %d > 제목: %s\n", i, m.title, m.content);
//	}
//}
//
//// 저장
//private void save() {
//	List<String> save = new ArrayList<>();
//	for (Memo m : memos) {
//		save.add(m.toString());
//	}
//	try {
//		Files.createDirectories(MEMO_PATH.getParent());
//		Files.write(MEMO_PATH, save);
//		System.out.println("메모가 저장되었습니다: " + MEMO_PATH.toAbsolutePath());
//	} catch (IOException e) {
//		System.out.println("메모장을 불러올 수 없습니다:" + e.getMessage());
//	}
//}
//	private String updateField(String field, String current) {
//		String input = TrioUtils.nextLine("새로운"+field + "(" +current + "): ");
//		return input.isEmpty() ? current : input;
//	}
//




}
