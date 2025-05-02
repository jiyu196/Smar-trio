package domain;

import java.io.Serializable;


public abstract class App implements Serializable{
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
}
