package com.hralievskyi.conferences.entity;

public class Report extends Entity {

	private static final long serialVersionUID = 2206611413040282994L;

	private String topicEn;
	private String topicUk;
	private String localTopic;
	private User author;
	private boolean accepted;
	private boolean suggested;
	private Speaker speaker;

	public void createLocalTopic(String language) {
		if ("uk".equals(language)) {
			this.localTopic = topicUk;
		} else {
			this.localTopic = topicEn;
		}
	}

	public String getLocalTopic(String language) {
		createLocalTopic(language);
		return localTopic;
	}

	public String getTopicEn() {
		return topicEn;
	}

	public void setTopicEn(String topicEn) {
		this.topicEn = topicEn;
	}

	public String getTopicUk() {
		return topicUk;
	}

	public void setTopicUk(String topicUk) {
		this.topicUk = topicUk;
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

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public String getLocalTopic() {
		return localTopic;
	}

	public void setLocalTopic(String localTopic) {
		this.localTopic = localTopic;
	}

}
