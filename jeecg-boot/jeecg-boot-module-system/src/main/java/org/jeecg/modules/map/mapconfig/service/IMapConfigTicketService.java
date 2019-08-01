package org.jeecg.modules.map.mapconfig.service;

import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 产品明细
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
public interface IMapConfigTicketService extends IService<MapConfigTicket> {

	public List<MapConfigTicket> selectByMainId(String mainId);
}
