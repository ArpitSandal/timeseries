package com.infy.timeseries.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timeseries")
public class TimeSeriesEntity {
	@Id
	@Column(name = "time_stamp")
	LocalDateTime timestamp;
	Integer eventId;

	@Column(name="person_id")
	Integer personId;

	String personPayload;

	String eventType;

	LocalDateTime createdOn;

	LocalDateTime raisedOn;

	LocalDateTime subscribedOn;

	LocalDateTime handledOn;

	LocalDateTime processedOn;

	public TimeSeriesEntity() {
		
	}
	public TimeSeriesEntity(Integer eventId, Integer personId, String personPayload,
			String eventType, LocalDateTime createdOn, LocalDateTime raisedOn, LocalDateTime subscribedOn,
			LocalDateTime handledOn, LocalDateTime processedOn) {
		super();
		this.timestamp = LocalDateTime.now();
		this.eventId = eventId;
		this.personId = personId;
		this.personPayload = personPayload;
		this.eventType = eventType;
		this.createdOn = createdOn;
		this.raisedOn = raisedOn;
		this.subscribedOn = subscribedOn;
		this.handledOn = handledOn;
		this.processedOn = processedOn;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonPayload() {
		return personPayload;
	}

	public void setPersonPayload(String personPayload) {
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
