package com.mtch.hmgmt.dao;

import java.util.List;
import java.util.Set;

import com.mtch.hmgmt.model.Room;

/**
 * 
 * @author Karthick
 *
 */
public interface RoomDao {

	public void book(Room room);

	public void changeColor(Room room);

	public void changeTemperature(Room room);

	public Room isAvailable(Room room);

	public List<Room> getAllRooms();

	public List<Room> getAllAvailableRooms();

	public Room getRoomByRoomNo(long roomNo);

	public void checkOut(Room room);

}
