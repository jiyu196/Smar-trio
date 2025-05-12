package app;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

import domain.Calculation;
import domain.Contacts;
import static util.TrioUtils.*;

@SuppressWarnings("unchecked")
public class ContactList extends App {
//	private final List<Contacts> contacts = new ArrayList<>(); // 연락처를 저장할 리스트
	private List<Contacts> contacts; // 연락처를 저장할 리스트
	private int nextNo = 0; // 다음 연락처 번호

	// 생성자, 주소록의 번호를 받아서 App 클래스의 생성자를 호출
	private static ContactList contactList;

	public ContactList(int no) {
		super(no, "주소록");
	}

	public static ContactList getInstance() {
		if (contactList == null) {
			contactList = new ContactList(generateAppNo());
		}
		return contactList;
	}

	// 연락처 앱을 실행하는 메소드
	public void run() {
		System.out.println("연락처 앱을 실행합니다");

		// 저장된 연락처 목록을 로드
		loadContacts();

		while (true) {
			System.out.println("--- 연락처 메뉴 ---");
			System.out.println("1. 연락처 추가\n2. 연락처 수정\n3. 연락처 삭제\n4. 연락처 보기\n5. 돌아가기");
			int choice = nextInt("옵션을 선택하세요:"); // 메뉴 선택
			switch (choice) {
			case 1:
				addContact(); // 연락처 추가
				break;
			case 2:
				modifyContact(); // 연락처 수정
				break;
			case 3:
				deleteContact(); // 연락처 삭제
				break;
			case 4:
				showContacts(); // 연락처 보기
				break;
			case 5:
				saveContacts(); // 연락처 저장 후 종료
				return;
			default:
				System.out.println("잘못된 선택입니다.");
				break;
			}
		}
	}

//	// 연락처 목록 로드
//	private void loadContacts() {
//	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("storage/contacts/contacts_log.ser"))) {
//	        List<Contacts> loadedContacts = (List<Contacts>) ois.readObject();// 파일에서 연락처 목록을 읽어옴
//	        contacts.clear();  // 기존 연락처 목록을 초기화한 후 불러온 목록을 추가
//	        contacts.addAll(loadedContacts);
//	        if (!contacts.isEmpty()) { // 연락처가 하나 이상 있을 경우 다음 연락처 번호 설정
//	            nextNo = contacts.get(contacts.size() - 1).getNo() + 1;
//	        }
//	        System.out.println("연락처를 성공적으로 불러왔습니다.");
//	    } catch (Exception e) { // 예외 발생 시 오류 메시지 출력
//	        System.out.println("연락처를 불러오는 데 실패했습니다.");
//	        e.printStackTrace();
//	    }
//	}
//
//	// 연락처 저장
//	private void saveContacts() {
//		try {
////			save("storage", "contacts", "contacts_log.ser", contacts);
//			System.out.println("연락처가 성공적으로 저장되었습니다.");
//		} catch (Exception e) {
//			System.out.println("연락처 저장 실패: " + e.getMessage());
//		}
//	}

	private void saveContacts() {
		saveData(contacts, "storage/contacts/contacts_log.ser");
	}

	private void loadContacts() {
		List<Contacts> loadcontacts = loadData("storage/contacts/contacts_log.ser");

		if (loadcontacts != null) {
			this.contacts = loadcontacts;
			if (!contacts.isEmpty()) { // 연락처가 하나 이상 있을 경우 다음 연락처 번호 설정
				nextNo = contacts.get(contacts.size() - 1).getNo() + 1;
			}
			System.out.println("연락처를 성공적으로 불러왔습니다.");

		} else {
			this.contacts = new ArrayList<>(); // 파일이 없거나 실패한 경우에도 빈 리스트로 초기화
			System.out.println("기록을 불러오는 데 실패했습니다.");
		}
	}

	// 새 연락처 추가
	private void addContact() {
		String name = nextLine("이름: ");
		String phone = nextLine("전화번호 (010-1234-5678): ");

		if (!phone.matches("^01[0-9]-\\d{3,4}-\\d{4}$")) {
			System.out.println("전화번호 형식이 올바르지 않습니다.");
			return;
		}

		String email = nextLine("이메일: ");
		if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			System.out.println("이메일 형식이 올바르지 않습니다.");
			return;
		}

		String nickname = nextLine("별명: ");
		contacts.add(new Contacts(nextNo++, name, phone, email, nickname));
		System.out.println("연락처가 추가되었습니다.");
		saveContacts(); // 추가 후 저장
	}

	// 연락처 수정
	private void modifyContact() {
		if (contacts.isEmpty()) {
			System.out.println("수정할 연락처가 없습니다.");
			return;
		}

		showContacts(); // 연락처 목록 표시
		int no = nextInt("수정할 연락처 번호: ");
		Contacts contact = findByNo(no);

		if (contact == null) {
			System.out.println("해당 번호의 연락처가 없습니다.");
			return;
		}

		updateContact(contact); // 연락처 수정
		saveContacts(); // 수정 후 저장
	}

	// 연락처 수정
	private void updateContact(Contacts contact) {
		String name = nextLine("이름 (" + contact.getName() + "): ");
		String phone = nextLine("전화번호 (" + contact.getPhone() + "): ");
		String email = nextLine("이메일 (" + contact.getEmail() + "): ");
		String nickname = nextLine("별명 (" + contact.getNickname() + "): ");

		if (!name.isEmpty())
			contact.setName(name);
		if (!phone.isEmpty())
			contact.setPhone(phone);
		if (!email.isEmpty())
			contact.setEmail(email);
		if (!nickname.isEmpty())
			contact.setNickname(nickname);

		System.out.println("연락처가 수정되었습니다.");
	}

	// 연락처 목록 출력
	private void showContacts() {
		if (contacts.isEmpty()) {
			System.out.println("등록된 연락처가 없습니다.");
			return;
		}

		for (Contacts c : contacts) {
			System.out.printf("번호: %d | 이름: %s | 전화: %s | 이메일: %s | 별명: %s\n", c.getNo(), c.getName(), c.getPhone(),
					c.getEmail(), c.getNickname());
		}
	}

	// 번호로 연락처 찾기
	private Contacts findByNo(int no) {
		for (Contacts contact : contacts) {
			if (contact.getNo() == no) {
				return contact;
			}
		}
		return null;
	}

	// 연락처 삭제
	private void deleteContact() {
		if (contacts.isEmpty()) {
			System.out.println("삭제할 연락처가 없습니다.");
			return;
		}

		showContacts(); // 연락처 목록 표시
		int no = nextInt("삭제할 연락처 번호: ");
		Contacts contact = findByNo(no);

		if (contact == null) {
			System.out.println("해당 번호의 연락처가 없습니다.");
			return;
		}

		if (nextConfirm("정말 삭제하시겠습니까? :")) {
			contacts.remove(contact); // 삭제
			saveContacts(); // 삭제 후 저장
			System.out.println("연락처가 삭제되었습니다.");
		} else {
			System.out.println("삭제가 취소되었습니다.");
		}
	}
}
