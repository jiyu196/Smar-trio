package domain;

import java.io.Serializable;
import java.util.Objects;

public abstract class App implements Serializable {
	private int appNo;
	private String appName;

	public App(int appNo, String appName) {
		this.appNo = appNo;
		this.appName = appName;
	}

	public int getAppNo() {
		return appNo;
	}

	public String getAppName() {
		return appName;
	}

	// 앱 실행 메서드는 모든 앱이 구현해야 함
	public abstract void run();

	@Override
	public String toString() {
		return "App [appNo=" + appNo + ", appName=" + appName + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appNo);
	}

	@Override
	public boolean equals(Object obj) { // 같은 앱 체크 방식을 어플 no로 변경
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		return appNo == other.appNo; // 번호가 같으면 같은 앱으로 간주
	}

}
