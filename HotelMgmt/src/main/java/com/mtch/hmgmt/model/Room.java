package com.mtch.hmgmt.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Karthick
 *
 */
@Entity
@Table(name="room")
public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int roomNo;
	private Character isAvailable = 'N' ;
	private String type;
	private String userEmail;
	private String roomTemp;
	private String roomColor;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	 
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getRoomTemp() {
		return roomTemp;
	}
	public void setRoomTemp(String roomTemp) {
		this.roomTemp = roomTemp;
	}
	public String getRoomColor() {
		return roomColor;
	}
	public void setRoomColor(String roomColor) {
		this.roomColor = roomColor;
	}
	
	public Character getIsAvailable() {
		return isAvailable;
	}
	public void setIsAvailable(Character isAvailable) {
		this.isAvailable = isAvailable;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room [id=");
		builder.append(id);
		builder.append(", roomNo=");
		builder.append(roomNo);
		builder.append(", isAvailable=");
		builder.append(isAvailable);
		builder.append(", ");
		if (type != null) {
			builder.append("type=");
			builder.append(type);
			builder.append(", ");
		}
		if (userEmail != null) {
			builder.append("userEmail=");
			builder.append(userEmail);
			builder.append(", ");
		}
		if (roomTemp != null) {
			builder.append("roomTemp=");
			builder.append(roomTemp);
			builder.append(", ");
		}
		if (roomColor != null) {
			builder.append("roomColor=");
			builder.append(roomColor);
		}
		builder.append("]");
		return builder.toString();
	}
	
}
