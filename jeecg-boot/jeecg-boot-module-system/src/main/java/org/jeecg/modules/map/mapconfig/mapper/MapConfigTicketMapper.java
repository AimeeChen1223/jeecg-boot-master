package org.jeecg.modules.map.mapconfig.mapper;

import java.util.List;
import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 产品明细
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
public interface MapConfigTicketMapper extends BaseMapper<MapConfigTicket> {

	public boolean deleteByMainId(String mainId);
    
	public List<MapConfigTicket> selectByMainId(String mainId);
}
