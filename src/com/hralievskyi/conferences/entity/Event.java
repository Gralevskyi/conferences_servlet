package com.hralievskyi.conferences.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Event extends Entity {

	private static final long serialVersionUID = -8199167009604653369L;

	private String nameEn;
	private String nameUk;
	private String localName;
	private String placeEn;
	private String placeUk;
	private String localPlace;
	private LocalDate date;
	private LocalTime time;
	private Set<Report> reports = new HashSet<>();
	private Set<User> subscribers = new HashSet<>();

	public Event(long id) {
		super(id);
	}

	public Event() {
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameUk() {
		return nameUk;
	}

	public void setNameUk(String nameUk) {
		this.nameUk = nameUk;
	}

	public void createLocalName(String language) {
		if ("uk".equals(language)) {
			this.localName = nameUk;
		} else {
			this.localName = nameEn;
		}
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getLocalName() {
		return localName;
	}

	public String getLocalName(String language) {
		createLocalName(language);
		return localName;
	}

	public void setLocalPlace(String language) {
		if ("uk".equals(language)) {
			this.localPlace = placeUk;
		} else {
			this.localPlace = placeEn;
		}

	}

	public String getLocalPlace() {
		return localPlace;
	}

	public String getLocalPlace(String language) {
		setLocalPlace(language);
		return localPlace;
	}

	public String getPlaceEn() {
		return placeEn;
	}

	public void setPlaceEn(String placeEn) {
		this.placeEn = placeEn;
	}

	public String getPlaceUk() {
		return placeUk;
	}

	public void setPlaceUk(String placeUk) {
		this.placeUk = placeUk;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Set<Report> getReports() {
		return reports;
	}

	public void setReports(Set<Report> reports) {
		this.reports = reports;
	}

	public Set<User> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Set<User> subscribers) {
		this.subscribers = subscribers;
	}

}
