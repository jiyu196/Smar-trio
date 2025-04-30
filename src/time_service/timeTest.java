package time_service;

//시간 호출 방법 예제
public class timeTest {
	public static void main(String[] args) {
        String currentTime = timeService.getCurrentTime();
        System.out.println(currentTime);
    }
}