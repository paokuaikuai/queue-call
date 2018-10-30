package com.smart.server.service;


import java.util.List;

import com.smart.mvc.model.Pagination;
import com.smart.mvc.service.mybatis.Service;
import com.smart.server.model.Room;

/**
 * 诊室服务接口
 * 
 * @author WS
 */
public interface RoomService extends Service<Room, Integer> {
	
	/**
	 * 根据查询分页列表
	 * @param pageNo 分页起始
	 * @param pageSize 分页记录数
	 * @return
	 */
	public Pagination<Room> findPaginationByName(String name,  Pagination<Room> p);
	
	/**
	 * 根据departId查询诊室
	 * @return
	 */
	public List<Room> findRoomByDepartId(Integer departId);
	
}
