package domain;

import java.io.Serializable;

public abstract class App implements Serializable{
	String name;
	
	public App(String name) {
		this.name= name;
	}
	
	public void run() {}
	
	public String getName() {
        return name;
    }

}
