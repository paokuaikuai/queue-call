package com.smart.server.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.impl.ServiceImpl;
import com.smart.server.common.aop.SystemServiceLog;
import com.smart.server.dao.RoomDao;
import com.smart.server.model.Room;
import com.smart.server.service.RoomService;

@Service("roomService")
public class RoomServiceImpl extends ServiceImpl<RoomDao, Room, Integer> implements RoomService {


	@Autowired
	public void setDao(RoomDao dao) {
		this.dao = dao;
	}
	@SystemServiceLog(description="保存")
	public void save(Room t) {
		super.save(t);
	}

	public Pagination<Room> findPaginationByName(String name, Pagination<Room> p) {
		dao.findPaginationByName(name, p);
		return p;
	}
	public List<Room> findRoomByDepartId(Integer departId) {
		return dao.findRoomByDepartId(departId);
	}


}
