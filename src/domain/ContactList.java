package domain;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import utils.TrioUtils;

public class ContactList {
//	public static void main(String[] args) {
//		ContactList app = new ContactList();
//		app.run();
//	}

	private static final Path CONTACT_PATH = Path.of("storage", "contacts", "contacts.txt");

	private static final ContactList contactList = new ContactList();
	
	public ContactList() {
		loadContacts();
	}
	
	public static ContactList getInstance() {
		return contactList;
	}

    private List<Contact> contacts = new ArrayList<>();
    private int nextNo = 0;

    public Contact findBy(int no) {
        for (Contact c : contacts) {
            if (c.getNo() == no) {
                return c;
            }
        }
        return null;
    }
    
    public void run() {
        while (true) {
            System.out.println("--- 연락처 메뉴 ---");
            System.out.println(""
            		+ "1. 연락처 추가\n"
            		+ "2. 연락처 수정\n"
            		+ "3. 연락처 삭제\n"
            		+ "4. 연락처 보기\n"
            		+ "5. 돌아가기");
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
    
    public void showContacts() {
        if (contacts.isEmpty()) {
            System.out.println("연락처가 없습니다.");
            return;
        }

        for (int i = 0; i < contacts.size(); i++) {
            Contact c = contacts.get(i);
            System.out.printf("%d: 이름: %s, 전화번호: %s, 이매일: %s, 별명: %s\n", 
                i, c.getName(), c.getPhone(), c.getEmail(), c.getNickname());
        }
    }

    private void addContact() {
        String name = TrioUtils.nextLine("이름: ");
        
        String phone = TrioUtils.nextLine("전화번호: ");
        if (!phone.matches("^01[0-9]{1}-[0-9]{3,4}-[0-9]{4}$")) {
			System.err.println("\n형식이 틀렸습니다 > ex. ex.(010-0000-0000)");
			return;
        }
        String email = TrioUtils.nextLine("이메일: ");
        if (!email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			System.err.println("\n형식이 틀렸습니다 > ex. (JohnSmith@Hotmail.com)");
			return;
        }
        String nickname = TrioUtils.nextLine("별명: ");

        int newNo = nextNo++;
        contacts.add(new Contact(newNo, name, phone, email, nickname));
        saveContacts();
        System.out.println("연락처가 추가되고 저장되었습니다.");
    }

    private void modifyContact() {
        if (contacts.isEmpty()) {
            System.out.println("수정할 연락처가 없습니다.");
            return;
        }

        int no = TrioUtils.nextInt("수정할 연락처의 번호를 입력하세요:");

        Contact contactToModify = findBy(no);
        if (contactToModify == null) {
            System.out.println("해당 번호의 연락처를 찾을 수 없습니다.");
            return;
        }

        String name = updateField("이름", contactToModify.getName());
        String phone = updateField("전화번호", contactToModify.getPhone());
        String email = updateField("이메일", contactToModify.getEmail());
        String nickname = updateField("별명", contactToModify.getNickname());

        contactToModify.setName(name);
        contactToModify.setPhone(phone);
        contactToModify.setEmail(email);
        contactToModify.setNickname(nickname);

        saveContacts();
        System.out.println("연락처가 수정되고 저장되었습니다.");
    }

    private void deleteContact() {
        if (contacts.isEmpty()) {
            System.out.println("삭제할 연락처가 없습니다.");
            return;
        }

        int no = TrioUtils.nextInt("삭제할 연락처의 번호를 입력하세요:");

        Contact contactToDelete = findBy(no);
        if (contactToDelete == null) {
            System.out.println("해당 번호의 연락처를 찾을 수 없습니다.");
            return;
        }

        boolean confirm = TrioUtils.nextConfirm("이 연락처를 삭제하시겠습니까? (y/n)");
        if (confirm) {
            contacts.remove(contactToDelete);
            saveContacts();
            System.out.println("연락처가 삭제되고 저장되었습니다.");
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
    }

    private void saveContacts() {
        List<String> saveContact = new ArrayList<>();
        for (Contact c : contacts) {
        	saveContact.add(c.toString());
        }

        try {
        	Files.createDirectories(CONTACT_PATH.getParent());
        	Files.write(CONTACT_PATH, saveContact);
            System.out.println("연락처가 저장되었습니다: " + CONTACT_PATH.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("연락처 저장에 실패했습니다: " + e.getMessage());
        }
    }

    private void loadContacts() {
        if (!Files.exists(CONTACT_PATH)) return;

        try {
            List<String> saveContact = Files.readAllLines(CONTACT_PATH);
            for (String saveContacts : saveContact) {
                Contact c = Contact.fromString(saveContacts);
                if (c != null) {
                    contacts.add(c);
                    nextNo = Math.max(nextNo, c.getNo() + 1);
                }
            }
        } catch (IOException e) {
            System.err.println("연락처를 불러올 수 없습니다: " + e.getMessage());
        }
    }

    //to domain you go
    private String updateField(String field, String current) {
        String input = TrioUtils.nextLine("새로운 " + field + " (" + current + "): ");
        return input.isEmpty() ? current : input;
    }

    private static class Contact {
        private final int no;
        private String name;
        private String phone;
        private String email;
        private String nickname;

        public Contact(int no, String name, String phone, String email, String nickname) {
            this.no = no;
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.nickname = nickname;
        }

        public int getNo() {
            return no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        @Override
        public String toString() {
            return no + ";" + name + ";" + phone + ";" + email + ";" + nickname;
        }

        public static Contact fromString(String line) {
            String[] parts = line.split(";");
            if (parts.length == 5) {
                int no = Integer.parseInt(parts[0]);
                return new Contact(no, parts[1], parts[2], parts[3], parts[4]);
            }
            return null;
        }
    }
}
