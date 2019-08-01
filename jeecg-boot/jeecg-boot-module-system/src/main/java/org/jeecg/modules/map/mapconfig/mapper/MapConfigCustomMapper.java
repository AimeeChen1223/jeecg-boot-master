package org.jeecg.modules.map.mapconfig.mapper;

import java.util.List;
import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 客户明细
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
public interface MapConfigCustomMapper extends BaseMapper<MapConfigCustom> {

	public boolean deleteByMainId(String mainId);
    
	public List<MapConfigCustom> selectByMainId(String mainId);
}
