package app;

import domain.WeatherInfo;
import util.TrioUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherNow extends App {
    private List<WeatherInfo> weatherData;
    private Random random;

    public WeatherNow(int no) {
        super(no, "날씨 앱");
        this.weatherData = new ArrayList<>();
        this.random = new Random();

        // 도시 데이터 생성
        weatherData.add(new WeatherInfo("서울", "대한민국", generateRandomTemperature(), generateRandomHumidity(), "매우 나쁨"));
        weatherData.add(new WeatherInfo("부산", "대한민국", generateRandomTemperature(), generateRandomHumidity(), generateRandomMicroDust()));
        weatherData.add(new WeatherInfo("뉴욕", "미국", generateRandomTemperature(), generateRandomHumidity(), generateRandomMicroDust()));
    }

    public void run() {
        while (true) {
            System.out.println("< 날씨 앱 >");
            System.out.println("1. 날씨 조회\n2. 종료");

            String choice = TrioUtils.nextLine("선택: ");
            if (choice.equals("2")) {
                System.out.println("앱을 종료합니다.");
                return;
            } else if (choice.equals("1")) {
                showWeather();
            } else {
                System.out.println("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }

    private void showWeather() {
        System.out.println("< 날씨 정보 조회 >");
        System.out.println("날씨를 조회할 도시를 선택하세요:");

        // 도시 목록 출력
        int index = 1;
        for (WeatherInfo weather : weatherData) {
            System.out.println(index++ + ". " + weather.getName() + ", " + weather.getCountry());
        }

        int cityChoice = TrioUtils.nextInt("도시 번호 선택: ");
        
        if (cityChoice > 0 && cityChoice <= weatherData.size()) {
            WeatherInfo weather = weatherData.get(cityChoice - 1); // 입력 번호 - 1
            System.out.println("선택한 도시: " + weather.getName() + ", " + weather.getCountry());
            System.out.println("기온: " + weather.getTemperature() + "°C");
            System.out.println("습도: " + weather.getHumidity() + "%");
            System.out.println("미세먼지: " + weather.getMicroDust());
        } else {
            System.out.println("잘못된 번호입니다. 다시 선택해주세요.");
        }

        // 날씨 정보 조회 후 다시 돌아가기 여부 확인
        boolean restart = TrioUtils.nextConfirm("다시 조회하시겠습니까? : ");
        if (!restart) {
            System.out.println("날씨 조회를 종료합니다.");
        }
    }

    // 랜덤 온도 생성
    private double generateRandomTemperature() {
        return 10 + (25 * random.nextDouble()); // 10°C ~ 35°C 사이 온도
    }

    // 랜덤 습도 생성
    private int generateRandomHumidity() {
        return 40 + random.nextInt(51); // 40% ~ 90% 사이 습도
    }

    // 랜덤 미세먼지 생성
    private String generateRandomMicroDust() {
        String[] dustLevels = {"좋음", "보통", "나쁨", "매우 나쁨"};
        return dustLevels[random.nextInt(dustLevels.length)]; // 랜덤 골르기
    }
}
