package org.jeecg.modules.map.mapconfig.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @Description: 地图配置数据
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
@Data
@TableName("map_config_layer")
public class MapConfigMain implements Serializable {
    private static final long serialVersionUID = 1L;
    
	/**主键*/
	@TableId(type = IdType.UUID)
	private java.lang.String id;
	/**父级Id*/
	private java.lang.String parent_id;
	/**节点名称*/
	private java.lang.String nodename;
	/**模型类别*/
	private java.lang.String type;
	/**插件地址/路径*/
	private java.lang.String url;
	/**坐标参数*/
	private java.lang.String center_id;
	/**矢量数据参数*/
	private java.lang.String symbol_id;
	/**弹窗说明*/
	private java.lang.String popup;
	/**显示*/
	private java.lang.String visible;
	/**是否折叠*/
	private java.lang.String open;
	/**创建人*/
	private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
	private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
}
