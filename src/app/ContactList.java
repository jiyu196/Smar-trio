package app;

import java.io.*;
import java.util.*;
import java.nio.file.*;

import domain.Contacts;
import util.TrioUtils;

@SuppressWarnings("unchecked")
public class ContactList extends App {

	private final List<Contacts> contacts = new ArrayList<>();

	private int nextNo = 0;

	public ContactList(int no) {
		super(no, "주소록");
	}

//	public static void main(String[] args) {
//		ContactList app = new ContactList(1);
//		app.run();
//	}

	public void run() {
		System.out.println("연락처 앱을 실행합니다");
		loadContacts();
		while (true) {
			System.out.println("--- 연락처 메뉴 ---");
			System.out.println("" + "1. 연락처 추가\n" + "2. 연락처 수정\n" + "3. 연락처 삭제\n" + "4. 연락처 보기\n" + "5. 돌아가기");
			int choice = TrioUtils.nextInt("옵션을 선택하세요:");
			switch (choice) {
			case 1:
				addContact();
				break;
			case 2:
				modifyContact();
				break;
			case 3:
				deleteContact();
				break;
			case 4:
				showContacts();
				break;
			case 5:
				saveContacts();
				return;
			default:
				System.out.println("잘못된 선택입니다.");
				break;
			}
		}
	}

	private void addContact() { // 주소록 추가
		String name = TrioUtils.nextLine("이름: ");
		String phone = TrioUtils.nextLine("전화번호 (010-1234-5678): ");
		if (!phone.matches("^01[0-9]-\\d{3,4}-\\d{4}$")) {
			System.out.println("전화번호 형식이 올바르지 않습니다.");
			return;
		}
		String email = TrioUtils.nextLine("이메일: ");
		if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			System.out.println("이메일 형식이 올바르지 않습니다.");
			return;
		}
		String nickname = TrioUtils.nextLine("별명: ");

		contacts.add(new Contacts(nextNo++, name, phone, email, nickname));
		System.out.println("연락처가 추가되었습니다.");
		saveContacts();
	}

	private void modifyContact() { // 주소록 수정
		if (contacts.isEmpty()) {
			System.out.println("수정할 연락처가 없습니다.");
			return;
		}

		showContacts();
		int no = TrioUtils.nextInt("수정할 연락처 번호: ");
		Contacts contact = findByNo(no);
		if (contact == null) {
			System.out.println("해당 번호의 연락처가 없습니다.");
			return;
		}

		String name = TrioUtils.nextLine("이름 (" + contact.getName() + "): ");
		String phone = TrioUtils.nextLine("전화번호 (" + contact.getPhone() + "): ");
		String email = TrioUtils.nextLine("이메일 (" + contact.getEmail() + "): ");
		String nickname = TrioUtils.nextLine("별명 (" + contact.getNickname() + "): ");

		if (!name.isEmpty())
			contact.setName(name);
		if (!phone.isEmpty())
			contact.setPhone(phone);
		if (!email.isEmpty())
			contact.setEmail(email);
		if (!nickname.isEmpty())
			contact.setNickname(nickname);

		System.out.println("연락처가 수정되었습니다.");
		saveContacts();
	}

	private void showContacts() { // 주소록 보기
		if (contacts.isEmpty()) {
			System.out.println("등록된 연락처가 없습니다.");
			return;
		}

		for (Contacts c : contacts) {
			System.out.printf("번호: %d | 이름: %s | 전화: %s | 이메일: %s | 별명: %s\n", c.getNo(), c.getName(), c.getPhone(),
					c.getEmail(), c.getNickname());
		}
	}

	private void saveContacts() { // 주소록 저장
		TrioUtils.writeLog("contacts", "contacts_log.ser", new ArrayList<>(contacts)); //Array List로 저장
	}

	private void loadContacts() { // 주소록 조회
		contacts.clear(); //
		Path logPath = Path.of("storage", "contacts", "contacts_log.ser"); // 저장위치 불러오기
		if (!Files.exists(logPath)) {
			System.out.println("저장된 연락처가 없습니다.");
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(logPath.toFile()))) {
			Object obj = ois.readObject();
			if (obj instanceof List<?>) {
				List<Contacts> loaded = (List<Contacts>) obj;
				contacts.addAll(loaded);
				for (Contacts c : loaded) {
					if (c.getNo() >= nextNo) {
						nextNo = c.getNo() + 1;
					}
				}
				System.out.println("연락처 로드 완료 (" + contacts.size() + "개)");
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("연락처 로드 실패: " + e.getMessage());
		}
	}

	private void deleteContact() { // 주소록 삭제
		try {
			if (contacts.isEmpty()) {
				System.out.println("삭제할 연락처가 없습니다.");
				return;
			}
			showContacts();
			int no = TrioUtils.nextInt("삭제할 연락처 번호: ");
			Contacts contact = findByNo(no);
			try {
				if (contact == null) {
					System.out.println("해당 번호의 연락처가 없습니다.");
					return;
				}
				if (TrioUtils.nextConfirm("정말 삭제하시겠습니까? :")) {
					contacts.remove(contact);
					saveContacts();
					System.out.println("연락처가 삭제되었습니다.");
				} else {
					System.out.println("삭제가 취소되었습니다.");
				}
			} catch (NumberFormatException e) {
			}
		} catch (Exception e) {
		}
	}

	private Contacts findByNo(int no) {
		for (Contacts c : contacts) {
			if (c.getNo() == no)
				return c;
		}
		return null;
	}
}