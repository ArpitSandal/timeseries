package com.infy.timeseries.dto;

import java.time.LocalDateTime;

public class TimeSeriesDTO {
	LocalDateTime timestamp;
	Integer eventId;
	Integer personId;

	Object personPayload;

	String eventType;

	LocalDateTime createdOn;

	LocalDateTime raisedOn;

	LocalDateTime subscribedOn;

	LocalDateTime handledOn;

	LocalDateTime processedOn;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public Object getPersonPayload() {
		return personPayload;
	}

	public void setPersonPayload(Object personPayload) {
		this.personPayload = personPayload;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getRaisedOn() {
		return raisedOn;
	}

	public void setRaisedOn(LocalDateTime raisedOn) {
		this.raisedOn = raisedOn;
	}

	public LocalDateTime getSubscribedOn() {
		return subscribedOn;
	}

	public void setSubscribedOn(LocalDateTime subscribedOn) {
		this.subscribedOn = subscribedOn;
	}

	public LocalDateTime getHandledOn() {
		return handledOn;
	}

	public void setHandledOn(LocalDateTime handledOn) {
		this.handledOn = handledOn;
	}

	public LocalDateTime getProcessedOn() {
		return processedOn;
	}

	public void setProcessedOn(LocalDateTime processedOn) {
		this.processedOn = processedOn;
	}

	@Override
	public String toString() {
		return "TimeSeriesEntity [timestamp=" + timestamp + ", eventId=" + eventId + ", personId=" + personId
				+ ", personPayload=" + personPayload + ", eventType=" + eventType + ", createdOn=" + createdOn
				+ ", raisedOn=" + raisedOn + ", subscribedOn=" + subscribedOn + ", handledOn=" + handledOn
				+ ", processedOn=" + processedOn + "]";
	}

}
