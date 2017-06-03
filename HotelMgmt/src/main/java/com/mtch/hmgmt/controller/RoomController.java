package com.mtch.hmgmt.controller;

import java.util.List;

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
	@RequestMapping(value = "/all/", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getAllRooms() {
		List<Room> rooms = roomService.getAllRooms();
		if (rooms ==null ||rooms.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/available/", method = RequestMethod.GET)
	public ResponseEntity<List<Room>> getAllAvailableRooms() {
		List<Room> rooms = roomService.getAllAvailableRooms();
		if (rooms ==null ||rooms.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vacate/{roomNo}", method = RequestMethod.GET)
	public ResponseEntity<?> vacate(@PathVariable("roomNo") long roomNo) {
		Room room = roomService.getRoomByRoomNo(roomNo);
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomNo);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomNo 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		roomService.book(room);
		return new ResponseEntity<Room>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	public ResponseEntity<?> book(@RequestBody Room roomInput) {
		logger.info("Obtained info for booking: " + roomInput );
		Room room = roomService.getRoomByRoomNo(roomInput.getRoomNo());
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomInput);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomInput 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		roomService.book(roomInput);
		return new ResponseEntity<Room>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/color", method = RequestMethod.POST)
	public ResponseEntity<?> changeColor(@RequestBody Room roomInput) {
		logger.info("Obtained info for changeColor: " + roomInput );
		Room room = roomService.getRoomByRoomNo(roomInput.getRoomNo());
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomInput);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomInput 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		room.setRoomColor(roomInput.getRoomColor());
		roomService.changeColor(room);
		return new ResponseEntity<Room>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/temperature", method = RequestMethod.POST)
	public ResponseEntity<?> changeTemperature(@RequestBody Room roomInput) {
		logger.info("Obtained info for changeTemperature: " + roomInput );
		Room room = roomService.getRoomByRoomNo(roomInput.getRoomNo());
		if (room == null) {
			logger.error("Room with roomNo {} not found.", roomInput);
			return new ResponseEntity(new CustomErrorType("Room with RoomNO " + roomInput 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		room.setRoomTemp(roomInput.getRoomTemp());
		roomService.changeTemperature(room);
		return new ResponseEntity<Room>(HttpStatus.OK);
	}
	
}
