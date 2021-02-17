package com.hralievskyi.conferences.entity;

public class Report extends Entity {

	private static final long serialVersionUID = 2206611413040282994L;

	private String topic_en;
	private String topic_uk;
	private User author;
	private boolean accepted;
	private boolean suggested;

	public String getTopic_en() {
		return topic_en;
	}

	public void setTopic_en(String topic_en) {
		this.topic_en = topic_en;
	}

	public String getTopic_uk() {
		return topic_uk;
	}

	public void setTopic_uk(String topic_uk) {
		this.topic_uk = topic_uk;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isSuggested() {
		return suggested;
	}

	public void setSuggested(boolean suggested) {
		this.suggested = suggested;
	}

}
