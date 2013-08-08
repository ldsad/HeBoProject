package com.hebo.heboproject.entity;

public class WeatherEntity {
	private String city;
	private String date_y;
	private String week;
	private String temp1;
	private String temp2;
	private String temp3;
	private String temp4;
	private String temp5;
	private String temp6;
	private String weather1;
	private String weather2;
	private String weather3;
	private String weather4;
	private String weather5;
	private String weather6;
	private String wind1;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDate_y() {
		return date_y;
	}

	public void setDate_y(String date_y) {
		this.date_y = date_y;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getTemp1() {
		return temp1;
	}

	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}

	public String getTemp2() {
		return temp2;
	}

	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}

	public String getTemp3() {
		return temp3;
	}

	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}

	public String getTemp4() {
		return temp4;
	}

	public void setTemp4(String temp4) {
		this.temp4 = temp4;
	}

	public String getTemp5() {
		return temp5;
	}

	public void setTemp5(String temp5) {
		this.temp5 = temp5;
	}

	public String getTemp6() {
		return temp6;
	}

	public void setTemp6(String temp6) {
		this.temp6 = temp6;
	}

	public String getWeather1() {
		return weather1;
	}

	public void setWeather1(String weather1) {
		this.weather1 = weather1;
	}

	public String getWeather2() {
		return weather2;
	}

	public void setWeather2(String weather2) {
		this.weather2 = weather2;
	}

	public String getWeather3() {
		return weather3;
	}

	public void setWeather3(String weather3) {
		this.weather3 = weather3;
	}

	public String getWeather4() {
		return weather4;
	}

	public void setWeather4(String weather4) {
		this.weather4 = weather4;
	}

	public String getWeather5() {
		return weather5;
	}

	public void setWeather5(String weather5) {
		this.weather5 = weather5;
	}

	public String getWeather6() {
		return weather6;
	}

	public void setWeather6(String weather6) {
		this.weather6 = weather6;
	}

	public String getWind1() {
		return wind1;
	}

	public void setWind1(String wind1) {
		this.wind1 = wind1;
	}


	public WeatherEntity(String city, String date_y, String week, String temp1,
			String temp2, String temp3, String temp4, String temp5,
			String temp6, String weather1, String weather2, String weather3,
			String weather4, String weather5, String weather6, String wind1,
			String fx1, String fx2, String fx3, String fx4,
			String fx5, String fx6) {
		super();
		this.city = city;
		this.date_y = date_y;
		this.week = week;
		this.temp1 = temp1;
		this.temp2 = temp2;
		this.temp3 = temp3;
		this.temp4 = temp4;
		this.temp5 = temp5;
		this.temp6 = temp6;
		this.weather1 = weather1;
		this.weather2 = weather2;
		this.weather3 = weather3;
		this.weather4 = weather4;
		this.weather5 = weather5;
		this.weather6 = weather6;
		this.wind1 = wind1;
	}

	public WeatherEntity() {
		super();
	}
}

