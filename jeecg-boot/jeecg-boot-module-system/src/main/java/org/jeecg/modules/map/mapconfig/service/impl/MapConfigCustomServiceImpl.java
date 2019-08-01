package org.jeecg.modules.map.mapconfig.service.impl;

import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import org.jeecg.modules.map.mapconfig.mapper.MapConfigCustomMapper;
import org.jeecg.modules.map.mapconfig.service.IMapConfigCustomService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 客户明细
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
@Service
public class MapConfigCustomServiceImpl extends ServiceImpl<MapConfigCustomMapper, MapConfigCustom> implements IMapConfigCustomService {
	@Autowired
	private MapConfigCustomMapper mapConfigCustomMapper;
	
	@Override
	public List<MapConfigCustom> selectByMainId(String mainId) {
		return mapConfigCustomMapper.selectByMainId(mainId);
	}
}
