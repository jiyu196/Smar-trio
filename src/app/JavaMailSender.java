package app;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import domain.Contacts;

import static util.TrioUtils.*;

public class JavaMailSender extends App {

	public JavaMailSender(int appNo) {
		super(appNo, "메일 발송", true);
	}

	@SuppressWarnings("finally")
	@Override
	public void run() {
		while (true) {

			int no = nextInt("사용할 기능을 선택해주세요.\n" + " (1) 메일 입력\n" + " (2) 주소록에서 선택\n" + " (0) 되돌아가기");
			switch (no) {
			case 1:
				try {
					insertEmail();
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} finally {
					return;
				}
			case 2:
				try {
					viewList();
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} finally {
					return;
				}
			case 0:
				System.out.println("이전 메뉴로 되돌아갑니다");
				return;
			default:
				throw new IllegalArgumentException("Unexpected value: " + no);
			}

		}
	}

	public void viewList() throws AddressException, MessagingException {
		ContactList contactList = ContactList.getInstance();
		contactList.contacts = loadData("storage/contacts/contacts_log.ser");
		contactList.showContacts();

		int no = nextInt("전송할 이메일 선택");
		Contacts contact = contactList.findByNo(no);
		if (nextConfirm(contact.getEmail() + "을 선택했습니다. 옳바른 이메일입니까?")) {
			String title = nextLine("메일 제목을 입력해주세요");
			String content = nextLine("메일 본문을 입력해주세요");
			if(nextConfirm("메일을 정말 전송하십니까?")) {
				sendMail(contact.getEmail(), title, content);
			}			
		}

	}

	public void insertEmail() throws AddressException, MessagingException {
		String recipient = nextLine("수신자의 이메일을 정확히 입력해주세요");

		if (!recipient.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			System.out.println("이메일 형식이 올바르지 않습니다.");
			return;
		}
		String title = nextLine("메일 제목을 입력해주세요");
		String content = nextLine("메일 본문을 입력해주세요");
		if(nextConfirm("메일을 정말 전송하십니까?")) {
			sendMail(recipient, title, content);
		}
	}

	public void sendMail(String recipient, String title, String content) throws AddressException, MessagingException {
		final String id = "tiger2ys98@gmail.com";
		final String pw = "httyqnfhvnpepmvi";

		System.out.println("잠시 기다려주세요");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(id, pw);
			}
		});
		System.out.println(session);

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(id));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient)); // 수신자 이메일

		message.setSubject(title); // 메일 제목
		message.setText(content); // 제목 본문
		Transport.send(message);

		System.out.println("전송되었습니다");
	}

}
