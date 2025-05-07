package service.apps;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import domain.ContactList;
import domain.Apps.MemoApp;
import utils.TrioUtils;



public class MemoService {
	
	private static final Path MEMO_PATH = Path.of("storage", "contacts", "contacts.txt");

	private static final MemoService memoService = new MemoService();
	
	public MemoService() {
	}
	
	public static MemoService getInstance() {
		return memoService;
	}
	
	private List<MemoApp> memos = new ArrayList<>();
//	private static MemoApp memo = new MemoApp();
	private int nextNo = 0;
	
	public MemoApp findBy(int no) {
		for(MemoApp m : memos) {
			if(m.getNo() == no) {
				return m;
			}
		}
		return null;
	}
	//추가, 수정, 삭제, 저장, 기록
	public void run() {
		System.out.println("메모 앱을 실행합니다");
		
		while(true) {
			int chack = TrioUtils.nextInt("1. 메모 추가  \n2. 메모 수정  \n3. 메모 삭제  \n4. 작성한 메모보기 ");
			
			switch(chack) {
			case 1:
				add();
				break;
			case 2:
				modify();
				break;
			case 3:
				delete();
				break;
			case 4:
				history();
				break;
			}
		}
	}
	
	public void add() {
		String title = TrioUtils.nextLine("제목: ");
		String content = TrioUtils.nextLine("내용: ");
		memos.add(new Memo(title, content));
		save();
		System.out.println("메모 추가 및 저장 완료");
		
	}
	public void modify() {
		
	}
	public void delete() {
		
	}
	public void save() {
		
	}
	public void history() {
		
	}
	public void savd() {
		
	}
	
	
		
}
