package org.jeecg.modules.map.mapconfig.service;

import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 客户明细
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
public interface IMapConfigCustomService extends IService<MapConfigCustom> {

	public List<MapConfigCustom> selectByMainId(String mainId);
}
