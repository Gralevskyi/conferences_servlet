package com.hralievskyi.conferences.entity;

public class Speaker extends User {

	private static final long serialVersionUID = -3942811697054574062L;

	private String name_en;
	private String name_uk;

	public Speaker(String name_en, String name_uk) {
		super();
		this.name_en = name_en;
		this.name_uk = name_uk;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public String getName_uk() {
		return name_uk;
	}

	public void setName_uk(String name_uk) {
		this.name_uk = name_uk;
	}

}
