package domain;

import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import utils.TrioUtils;
import service.MemoService;

public class MemoApp extends MemoService {

	public static void main(String[] args) {
		MemoApp app = new MemoApp();
		app.run();
	}

	private static final Path MEMO_PATH = Path.of("storage", "Memo", "memo.txt");
	//여기 밑에 싱글턴 만들기
	private static final MemoApp memoApp = new MemoApp();
	
	private List<Memo> memos = new ArrayList<>();

	public MemoApp() {

	}

	public void run() {
		System.out.println("메모 앱을 실행합니다");

		while (true) {
			String str = utils.TrioUtils.nextLine("1. 작성한 메모 전체보기  2. 메모 추가  3. 수정  4. 삭제  6. 종료");
			int click = TrioUtils.nextInt("작업할 내용을 선택하세요");

			switch (click) {
			case 1:
				history();
				break;
			case 2:
				add();
				break;
			case 3:
				modify();
				break;
			case 4:
				delete();
				break;
			case 5:
				save();
				break;
			case 6:
				System.out.println("메모장 앱이 종료되었습니다");
				break;
			default:
				System.out.println("잘못된 경로입니다");
			}
		}
//	
	}

	private static MemoService memoService = new MemoService();

//		private MemoService() {
//		
//		}
	public static MemoService getInstance() {
		return memoService;
	}

//		//추가	
	private void add() {
		String title = TrioUtils.nextLine("제목: ");
		String content = TrioUtils.nextLine("내용: ");
		memos.add(new Memo(title, content));
		save();
		System.out.println("메모 추가 및 저장 완료");
	}

	// 수정
	private void modify() {
		if (memos.isEmpty()) {
			System.out.println("수정할 메모기록이 없습니다");
		}
		showMemo();
		int index = TrioUtils.nextInt("수정할 메모장을 입력하세요");
		
		if(index < 0 || index >= memos.size()) {
			System.out.println("잘못된 입력입니다");
			return;
		}
		
		Memo mo = memos.get(index);
		String title;
	}

	// 삭제
	private void delete() {
		if(memos.isEmpty()) {
			System.out.println("삭제할 메모가 없습니다");
			return;
		}
		showMemo();
		int index = TrioUtils.nextInt("삭제할 메모장 번호를 입력하세요: ");
	}

	// 작성 기록- 메모 리스트
	private void history() {
		System.out.println("작성한 메모");
		try {
			if (!Files.exists(MEMO_PATH)) {
				System.out.println("작성한 메모가 없습니다");
				return;
			}
			try (BufferedReader reader = Files.newBufferedReader(MEMO_PATH)) {
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
			}
		} catch (IOException e) {
			System.out.println("작성한 메모를 불러오지 못했습니다:" + e.getMessage());
		}
	}
    //메모 보여주기
	private void showMemo() {
		if (memos.isEmpty()) {
			System.out.println("메모가 비어있습니다");
			return;
		}
		for (int i = 0; i < memos.size(); i++) {
			Memo m = memos.get(i);
			System.out.printf("%d: %s\n", i, m.title, m.content);
		}
	}

	// 저장
	private void save() {
		List<String> save = new ArrayList<>();
		for (Memo m : memos) {
			save.add(m.toString());
		}
		try {
			Files.createDirectories(MEMO_PATH.getParent());
			Files.write(MEMO_PATH, save);
			System.out.println("메모가 저장되었습니다: " + MEMO_PATH.toAbsolutePath());
		} catch (IOException e) {
			System.out.println("메모장을 불러올 수 없습니다:" + e.getMessage());
		}
	}
		private String updateField(String field, String current) {
			String input = TrioUtils.nextLine("새로운"+field + "(" +current + "): ");
			return input.isEmpty() ? current : input;
		}

	private static class Memo {
		String title;
		String content;

		Memo(String title, String content) {
			super();
			this.title = title;
			this.content = content;
		}

	}
}
