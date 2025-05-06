package domain;


import java.io.BufferedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import utils.TrioUtils;
import service.MemoService;

public class MemoApp extends MemoService{
	
	public static void main(String[] args) {
//		MemoApp app = new MemoApp();
//		app.run();
//	}
//	
//	private static final Path MEMO_PATH = Path.of("storage", "Memo", "memo.txt");
//	private List<MemoService> memos = new ArrayList<>();
//	
//	public MemoApp() {
//		
//	}
//	
//	public void run() {
//		System.out.println("메모 앱을 실행합니다");
//		
//		while(true) {
//			String str = utils.TrioUtils.nextLine("1. 작성한 메모 전체보기  2. 메모 추가  3. 수정  4. 삭제  6. 종료");
//			int click = TrioUtils.nextInt("작업할 내용을 선택하세요"); 
//
//	switch(click) {
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
//	}
//
//	//작성 기록- 메모 리스트
//	public void history() {
//		System.out.println("작성한 메모");
//		try {
//			if(!Files.exists(FILE_PATH)) {
//				System.out.println("작성한 메모가 없습니다");
//				return;
//			}
//			try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
//				String line;
//				while((line = reader.readLine()) !=null) {
//					System.out.println(line);
//				}
//			}
//		}catch (IOException e) {
//			System.out.println("작성한 메모를 불러오지 못했습니다:" + e.getMessage());
//		}
//		
//		private static MemoService memoService = new MemoService();
//		
////		private MemoService() {
////		
////		}
//		public static MemoService getInstance() {
//			return memoService;
//		}
//		
////		//추가	
//		public void add() {
//			String title ;
//		}
//		
//	
//		
//		//메모 제목
//		public void title() {
//			
//		}
//		
//		//메모 내용
//		public void content() {
//			
//		}
//		
//		//수정
//		public void modify() {
//			
//		}
//		
//		//삭제
//		public void delete() {
//			
//		}
//		//저장
//		public void save() {
//			List<String> save = new ArrayList<>();
//			for(MemoService m : memos) {
//				save.add(m.toString());
//			}
//		}
//
//
//		private String title;
//		private String content;
//		public String getTitle() {
//			return title;
//		}
//		
//		
//		public void setTitle(String title) {
//			this.title = title;
//		}
//		public String getContent() {
//			return content;
//		}
//		public void setContent(String content) {
//			this.content = content;
//		}
//		
//		@Override
//		public String toString() {
//			return "MemoApp [title=" + title + ", content=" + content + "]";
//		
//
//	
//

	
	}
}
