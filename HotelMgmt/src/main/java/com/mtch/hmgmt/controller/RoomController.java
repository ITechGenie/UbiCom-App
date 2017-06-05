package com.mtch.hmgmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mtch.hmgmt.model.Room;
import com.mtch.hmgmt.service.RoomService;
import com.mtch.hmgmt.util.CustomErrorType;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/room")
public class RoomController {

	public static final Logger logger = LoggerFactory.getLogger(RoomController.class);

	@Autowired
	RoomService roomService; //Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All Rooms---------------------------------------------

	@ApiOperation(value = "getAllRooms", notes="get all rooms",nickname = "getAllRooms")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		if (rooms ==null ||rooms.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/available", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getAllAvailableRooms() {
		List<Room> rooms = roomService.getAllAvailableRooms();
		if (rooms ==null ||rooms.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/detail/{roomNo}", method = RequestMethod.GET)
	public Object getRoomByNo(@PathVariable("roomNo") Long roomNo) {
		Room rooms = roomService.getRoomByRoomNo(roomNo);
		if (rooms == null ) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return rooms ;
	}
	
	@RequestMapping(value = "/myroom/{emailId}", method = RequestMethod.GET)
	public Object getRoomByEmail(@PathVariable("emailId") String emailId) {
		List<Room> rooms = roomService. getAllAvailableRooms() ;
		if (rooms == null || rooms.size() == 0 ) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		for (Room room: rooms) {
			if ( emailId.equalsIgnoreCase(room.getUserEmail()) ) 
				return room ;
		}
		return new HashMap() ;
	}
	
	@RequestMapping(value = "/vacate/{roomNo}", method = RequestMethod.GET)
	public Object vacate(@PathVariable("roomNo") long roomNo) {
		Room room = roomService.getRoomByRoomNo(roomNo);
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomNo);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomNo 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		roomService.checkOut(room);
		Map<String, Object> resp = new HashMap<String, Object>() ;
		resp.put("sucsess", true) ;
		
		return resp ;
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public Object book(@RequestBody Room roomInput) {
		logger.info("Obtained info for booking: " + roomInput );
		Room room = roomService.getRoomByRoomNo(roomInput.getRoomNo());
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomInput);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomInput 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		roomService.book(roomInput);

		Map<String, Object> resp = new HashMap<String, Object>() ;
		resp.put("sucsess", true) ;
		
		return resp ;
	}
	
	@RequestMapping(value = "/color", method = RequestMethod.POST)
	public Object changeColor(@RequestBody Room roomInput) {
		logger.info("Obtained info for changeColor: " + roomInput );
		Room room = roomService.getRoomByRoomNo(roomInput.getRoomNo());
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomInput);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomInput 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		room.setRoomColor(roomInput.getRoomColor());
		roomService.changeColor(room);

		Map<String, Object> resp = new HashMap<String, Object>() ;
		resp.put("sucsess", true) ;
		
		return resp ;
	}
	
	@RequestMapping(value = "/temperature", method = RequestMethod.POST)
	public Object changeTemperature(@RequestBody Room roomInput, HttpServletResponse httpServletResponse) {
		logger.info("Obtained info for changeTemperature: " + roomInput );
		Room room = roomService.getRoomByRoomNo(roomInput.getRoomNo());
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomInput);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomInput 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		room.setRoomTemp(roomInput.getRoomTemp());
		roomService.changeTemperature(room);
		httpServletResponse.setStatus(HttpServletResponse.SC_CREATED);
		
		Map<String, Object> resp = new HashMap<String, Object>() ;
		resp.put("sucsess", true) ;
		
		return resp ;
	}
	
}
