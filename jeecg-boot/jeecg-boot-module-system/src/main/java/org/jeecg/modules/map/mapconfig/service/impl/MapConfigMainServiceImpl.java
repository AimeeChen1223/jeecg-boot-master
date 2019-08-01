package org.jeecg.modules.map.mapconfig.service.impl;

import org.jeecg.modules.map.mapconfig.entity.MapConfigMain;
import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import org.jeecg.modules.map.mapconfig.mapper.MapConfigCustomMapper;
import org.jeecg.modules.map.mapconfig.mapper.MapConfigTicketMapper;
import org.jeecg.modules.map.mapconfig.mapper.MapConfigMainMapper;
import org.jeecg.modules.map.mapconfig.service.IMapConfigMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
@Service
public class MapConfigMainServiceImpl extends ServiceImpl<MapConfigMainMapper, MapConfigMain> implements IMapConfigMainService {

	@Autowired
	private MapConfigMainMapper mapConfigMainMapper;
	@Autowired
	private MapConfigCustomMapper mapConfigCustomMapper;
	@Autowired
	private MapConfigTicketMapper mapConfigTicketMapper;
	
	@Override
	@Transactional
	public void saveMain(MapConfigMain mapConfigMain, List<MapConfigCustom> mapConfigCustomList,List<MapConfigTicket> mapConfigTicketList) {
		mapConfigMainMapper.insert(mapConfigMain);
		for(MapConfigCustom entity:mapConfigCustomList) {
			//外键设置
			entity.setOrderId(mapConfigMain.getId());
			mapConfigCustomMapper.insert(entity);
		}
		for(MapConfigTicket entity:mapConfigTicketList) {
			//外键设置
			entity.setOrderId(mapConfigMain.getId());
			mapConfigTicketMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void updateMain(MapConfigMain mapConfigMain,List<MapConfigCustom> mapConfigCustomList,List<MapConfigTicket> mapConfigTicketList) {
		mapConfigMainMapper.updateById(mapConfigMain);
		
		//1.先删除子表数据
		mapConfigCustomMapper.deleteByMainId(mapConfigMain.getId());
		mapConfigTicketMapper.deleteByMainId(mapConfigMain.getId());
		
		//2.子表数据重新插入
		for(MapConfigCustom entity:mapConfigCustomList) {
			//外键设置
			entity.setOrderId(mapConfigMain.getId());
			mapConfigCustomMapper.insert(entity);
		}
		for(MapConfigTicket entity:mapConfigTicketList) {
			//外键设置
			entity.setOrderId(mapConfigMain.getId());
			mapConfigTicketMapper.insert(entity);
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		mapConfigCustomMapper.deleteByMainId(id);
		mapConfigTicketMapper.deleteByMainId(id);
		mapConfigMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			mapConfigCustomMapper.deleteByMainId(id.toString());
			mapConfigTicketMapper.deleteByMainId(id.toString());
			mapConfigMainMapper.deleteById(id);
		}
	}
	
}
