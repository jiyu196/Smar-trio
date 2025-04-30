package service;

import java.util.ArrayList;
import java.util.List;

import domain.App;

public class mainService {
	private List<App> installedApps = new ArrayList<>();

	public void installApp(App app) {
		installedApps.add(app);
		System.out.println(app.getName() + " 설치 완료!");
	}
}
