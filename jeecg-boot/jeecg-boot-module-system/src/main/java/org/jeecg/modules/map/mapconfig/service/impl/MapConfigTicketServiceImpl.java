package org.jeecg.modules.map.mapconfig.service.impl;

import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import org.jeecg.modules.map.mapconfig.mapper.MapConfigTicketMapper;
import org.jeecg.modules.map.mapconfig.service.IMapConfigTicketService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 产品明细
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
@Service
public class MapConfigTicketServiceImpl extends ServiceImpl<MapConfigTicketMapper, MapConfigTicket> implements IMapConfigTicketService {
	
	@Autowired
	private MapConfigTicketMapper mapConfigTicketMapper;
	
	@Override
	public List<MapConfigTicket> selectByMainId(String mainId) {
		return mapConfigTicketMapper.selectByMainId(mainId);
	}
}
