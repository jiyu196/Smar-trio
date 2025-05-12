package app;

import java.io.Serializable;
import java.util.Objects;

public abstract class App implements Serializable {
	private final int appNo;
	private final String appName;
	private final boolean systemApp;
	
	public App(int appNo, String appName) {
		this(appNo, appName, false);
	}

	public App(int appNo, String appName, boolean systemApp) {
		super();
		this.appNo = appNo;
		this.appName = appName;
		this.systemApp = systemApp;
	}

	public int getAppNo() {
		return appNo;
	}

	public String getAppName() {
		return appName;
	}

	public boolean isSystemApp() {
		return systemApp;
	}
	
	public abstract void run();

	@Override
	public String toString() {
		return "App [appNo=" + appNo + ", appName=" + appName + ", systemApp=" + systemApp + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(appNo);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (!(obj instanceof App))
	        return false;
	    App other = (App) obj;
	    return this.appNo == other.appNo;
	}
	
	
}
