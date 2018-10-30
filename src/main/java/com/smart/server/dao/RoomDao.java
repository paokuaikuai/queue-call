package com.smart.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.smart.mvc.dao.mybatis.Dao;
import com.smart.mvc.model.Pagination;
import com.smart.server.model.Role;
import com.smart.server.model.Room;

/**
 * 诊室接口
 * 
 * @author WS
 */
public interface RoomDao extends Dao<Room, Integer> {

	public List<Room> findPaginationByName(@Param("roomName") String roomName, 
			 Pagination<Room> p);
	public List<Room> findRoomByDepartId(@Param("departId") Integer departId);
}
