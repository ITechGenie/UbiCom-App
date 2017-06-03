package com.mtch.hmgmt.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mtch.hmgmt.dao.RoomDao;
import com.mtch.hmgmt.model.Room;

@Service
public class RoomDaoImpl extends HibernateDaoSupport implements RoomDao {

	public static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

	@Autowired
	public void setMySessionFactory(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@Override
	@Transactional(readOnly = false)
	public void book(Room room) {
		Room roomDb = getRoomByRoomNo(room.getRoomNo());
		logger.info("Booking rooms: " + roomDb);
		room.setId(roomDb.getId());
		roomDb.setUserEmail(room.getUserEmail());

		SessionFactory sessionF = getHibernateTemplate().getSessionFactory();
		Session session = sessionF.openSession() ;
		Transaction tx = session.getTransaction() ;
		tx.begin();
		session.saveOrUpdate(roomDb); 
		tx.commit();
		session.close();
		
		logger.info("Object Updated successfully.....!!");
		
	}

	@Override
	public void checkOut(Room room) {
		Room roomDb = getRoomByRoomNo(room.getRoomNo());
		logger.info("Checkout rooms: " + roomDb);
		roomDb.setUserEmail(null);
		getHibernateTemplate().saveOrUpdate(roomDb);
	}

	@Override
	public Room isAvailable(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> getAllRooms() {
		return (List<Room>) getHibernateTemplate().find("from Room");
	}

	@Override
	public List<Room> getAllAvailableRooms() {
		return (List<Room>) getHibernateTemplate().find("from Room where userEmail is null");
	}

	public Room getRoomByRoomNo(long roomNo) {
		List<Room> rooms = (List<Room>) getHibernateTemplate().find("from Room where roomNo = '" + roomNo + "'");
		logger.info("Obtained rooms: " + rooms.size() + " - " + rooms);
		if (rooms != null && !rooms.isEmpty()) {
			if (rooms.size() > 1)
				throw new UnsupportedOperationException("Multiple Rooms with same room number found !");
			return rooms.get(0);
		}
		return new Room();
	}

	@Override
	@Transactional
	public void changeColor(Room room) {
		Room roomDb = getRoomByRoomNo(room.getRoomNo());
		logger.info("Changing Color rooms: " + roomDb);
		roomDb.setRoomColor(room.getRoomColor());
		getHibernateTemplate().saveOrUpdate(roomDb);
	}

	@Override
	public void changeTemperature(Room room) {
		Room roomDb = getRoomByRoomNo(room.getRoomNo());
		logger.info("Changing Temp rooms: " + roomDb);
		roomDb.setRoomTemp(room.getRoomTemp());
		getHibernateTemplate().saveOrUpdate(roomDb);
	}

}
