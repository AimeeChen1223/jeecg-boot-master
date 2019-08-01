package org.jeecg.modules.map.mapconfig.vo;

import java.util.List;
import org.jeecg.modules.map.mapconfig.entity.MapConfigMain;
import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

/**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
@Data
public class MapConfigMainPage {
	
	/**主键*/
	private java.lang.String id;
	/**订单号*/
  	@Excel(name = "订单号", width = 15)
	private java.lang.String orderCode;
	/**订单类型*/
  	@Excel(name = "订单类型", width = 15)
	private java.lang.String ctype;
	/**订单日期*/
  	@Excel(name = "订单日期", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date orderDate;
	/**订单金额*/
  	@Excel(name = "订单金额", width = 15)
	private java.lang.Double orderMoney;
	/**订单备注*/
  	@Excel(name = "订单备注", width = 15)
	private java.lang.String content;
	/**创建人*/
  	@Excel(name = "创建人", width = 15)
	private java.lang.String createBy;
	/**创建时间*/
  	@Excel(name = "创建时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date createTime;
	/**修改人*/
  	@Excel(name = "修改人", width = 15)
	private java.lang.String updateBy;
	/**修改时间*/
  	@Excel(name = "修改时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
  	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateTime;
	
	@ExcelCollection(name="客户明细")
	private List<MapConfigCustom> mapConfigCustomList;
	@ExcelCollection(name="产品明细")
	private List<MapConfigTicket> mapConfigTicketList;
	
}
