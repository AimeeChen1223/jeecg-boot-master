package org.jeecg.modules.map.mapconfig.service;

import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import org.jeecg.modules.map.mapconfig.entity.MapConfigMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
public interface IMapConfigMainService extends IService<MapConfigMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(MapConfigMain mapConfigMain,List<MapConfigCustom> mapConfigCustomList,List<MapConfigTicket> mapConfigTicketList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(MapConfigMain mapConfigMain,List<MapConfigCustom> mapConfigCustomList,List<MapConfigTicket> mapConfigTicketList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
