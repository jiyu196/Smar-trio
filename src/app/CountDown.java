package app;

import java.util.Comparator;
import java.util.stream.IntStream;

import util.TrioUtils;
public class CountDown extends App{

//	public static void main(String[] args) throws Exception{
//		CountDown app = new CountDown(1);
//		app.run();
//	}
	
	public CountDown(int no) {
		super(no, "카운트다운");
	}
	
	public void run() {
		System.out.println("카운트다운 앱을 실행합니다");
		while(true) {
			int no = TrioUtils.nextInt("시간(초)을 입력해주세요: ");
		
		IntStream.rangeClosed(1, no).boxed().sorted(Comparator.reverseOrder()).forEach(i->{
			System.out.println(i);
			try {
				Thread.sleep(1000);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		System.out.println("시간이 다 되었습니다!");
		System.out.println("-------------------------");
		if (!TrioUtils.nextConfirm("다시 시작하시겠습니까? ")) {
			System.out.println("카운트다운 앱이 종료되었습니다.");
			return;
		}
		continue;
	}
  }
}
