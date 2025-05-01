package service;

import java.util.ArrayList;
import java.util.List;

import domain.Apps;

public class AppService {
	private List<Apps> installedApps = new ArrayList<>();

	public void installApp(Apps app) {
		installedApps.add(app);
		System.out.println(app.getName() + " 설치 완료");
	}
}
