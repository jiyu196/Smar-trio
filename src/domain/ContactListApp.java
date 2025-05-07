package domain;

public class ContactListApp extends App{

	ContactList contactList = ContactList.getInstance();
	
	public ContactListApp(int appNo) {
		super(appNo, "주소록");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		contactList.run();
		
	}

}
