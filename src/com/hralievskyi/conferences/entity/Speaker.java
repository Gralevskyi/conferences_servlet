package com.hralievskyi.conferences.entity;

public class Speaker extends User {
	private static final long serialVersionUID = -3942811697054574062L;

	private String nameEn;
	private String nameUk;
	private String localName;

	public Speaker() {
		super();
	}

	public Speaker(long id) {
		super(id);
	}

	public Speaker(String nameEn, String nameUk) {
		super();
		this.nameEn = nameEn;
		this.nameUk = nameUk;
	}

	public void createLocalName(String language) {
		if ("uk".equals(language)) {
			this.setLocalName(nameUk);
		} else {
			this.setLocalName(nameEn);
		}
	}

	public String getLocalName(String language) {
		createLocalName(language);
		return localName;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String name_en) {
		this.nameEn = name_en;
	}

	public String getNameUk() {
		return nameUk;
	}

	public void setNameUk(String name_uk) {
		this.nameUk = name_uk;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

}
