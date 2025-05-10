package app;

import java.util.ArrayList;
import java.util.List;
import static util.TrioUtils.*;

public class store extends App{

	public store(int no) {
		super(no, "스토어", true);
		}

	List<App> store = new ArrayList<App>();
	
	@Override
	public void run() {
		
		install();
		while(true) {
			int no = nextInt(getCurrentDateTime());
		}
		
		
	}
	
	private void install() {
		
	}
	
	private void appList() {
		
	}
	
	
}
