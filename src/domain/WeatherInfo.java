package domain;

import java.io.Serializable;

public class WeatherInfo implements Serializable{

	private String city, country, microDust;
	private double temperature;
	private int humidity;

	public WeatherInfo(String city, String country, double temperature, int humidity, String microDust) {
		this.city = city;
		this.country = country;
		this.temperature = temperature;
		this.humidity = humidity;
		this.microDust = microDust;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMicroDust() {
		return microDust;
	}

	public void setMicroDust(String microDust) {
		this.microDust = microDust;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getHumidity() {
		return humidity;
	}

	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}

	@Override
	public String toString() {
		return city + ", " + country + ", " + temperature + "°C, " + humidity + "%, 미세먼지: " + microDust;
	}
}
