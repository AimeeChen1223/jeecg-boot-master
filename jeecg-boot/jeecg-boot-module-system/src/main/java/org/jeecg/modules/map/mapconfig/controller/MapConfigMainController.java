package org.jeecg.modules.map.mapconfig.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.map.mapconfig.entity.MapConfigCustom;
import org.jeecg.modules.map.mapconfig.entity.MapConfigTicket;
import org.jeecg.modules.map.mapconfig.entity.MapConfigMain;
import org.jeecg.modules.map.mapconfig.vo.MapConfigMainPage;
import org.jeecg.modules.map.mapconfig.service.IMapConfigMainService;
import org.jeecg.modules.map.mapconfig.service.IMapConfigCustomService;
import org.jeecg.modules.map.mapconfig.service.IMapConfigTicketService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;

 /**
 * @Description: 订单
 * @Author: jeecg-boot
 * @Date:   2019-07-28
 * @Version: V1.0
 */
@RestController
@RequestMapping("/mapconfig/MapConfigMain")
@Slf4j
public class MapConfigMainController {
	@Autowired
	private IMapConfigMainService mapConfigMainService;
	@Autowired
	private IMapConfigCustomService mapConfigCustomService;
	@Autowired
	private IMapConfigTicketService mapConfigTicketService;
	
	/**
	  * 分页列表查询
	 * @param MapConfigMain
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@GetMapping(value = "/list")
	// public Result<IPage<MapConfigMain>> queryPageList(MapConfigMain mapConfigMain,
	// 								  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
	// 								  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
	// 								  HttpServletRequest req) {
	// 	Result<IPage<MapConfigMain>> result = new Result<IPage<MapConfigMain>>();
	// 	QueryWrapper<MapConfigMain> queryWrapper = QueryGenerator.initQueryWrapper(mapConfigMain, req.getParameterMap());
	// 	Page<MapConfigMain> page = new Page<MapConfigMain>(pageNo, pageSize);
	// 	IPage<MapConfigMain> pageList = mapConfigMainService.page(page, queryWrapper);
	// 	result.setSuccess(true);
	// 	result.setResult(pageList);
	// 	return result;
	// }
	public Result<List<MapConfigMain>> list(MapConfigMain mapConfigMain,HttpServletRequest req) {
		Result<List<MapConfigMain>> result = new Result<List<MapConfigMain>>();
		QueryWrapper<MapConfigMain> queryWrapper = QueryGenerator.initQueryWrapper(mapConfigMain, req.getParameterMap());
		List<MapConfigMain> list = mapConfigMainService.list(queryWrapper);
		result.setSuccess(true);
		result.setResult(list);
		return result;
	}
	
	/**
	  *   添加
	 * @param MapConfigMainPage
	 * @return
	 */
	@PostMapping(value = "/add")
	public Result<MapConfigMain> add(@RequestBody MapConfigMainPage mapConfigMainPage) {
		Result<MapConfigMain> result = new Result<MapConfigMain>();
		try {
			MapConfigMain mapConfigMain = new MapConfigMain();
			BeanUtils.copyProperties(mapConfigMainPage, mapConfigMain);
			
			mapConfigMainService.saveMain(mapConfigMain, mapConfigMainPage.getMapConfigCustomList(),mapConfigMainPage.getMapConfigTicketList());
			result.success("添加成功！");
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			result.error500("操作失败");
		}
		return result;
	}
	
	/**
	  *  编辑
	 * @param MapConfigMainPage
	 * @return
	 */
	@PutMapping(value = "/edit")
	public Result<MapConfigMain> edit(@RequestBody MapConfigMainPage mapConfigMainPage) {
		Result<MapConfigMain> result = new Result<MapConfigMain>();
		MapConfigMain mapConfigMain = new MapConfigMain();
		BeanUtils.copyProperties(mapConfigMainPage, mapConfigMain);
		MapConfigMain mapConfigMainEntity = mapConfigMainService.getById(mapConfigMain.getId());
		if(mapConfigMainEntity==null) {
			result.error500("未找到对应实体");
		}else {
			boolean ok = mapConfigMainService.updateById(mapConfigMain);
			mapConfigMainService.updateMain(mapConfigMain, mapConfigMainPage.getMapConfigCustomList(),mapConfigMainPage.getMapConfigTicketList());
			result.success("修改成功!");
		}
		
		return result;
	}
	
	/**
	  *   通过id删除
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public Result<MapConfigMain> delete(@RequestParam(name="id",required=true) String id) {
		Result<MapConfigMain> result = new Result<MapConfigMain>();
		MapConfigMain mapConfigMain = mapConfigMainService.getById(id);
		if(mapConfigMain==null) {
			result.error500("未找到对应实体");
		}else {
			mapConfigMainService.delMain(id);
			result.success("删除成功!");
		}
		
		return result;
	}
	
	/**
	  *  批量删除
	 * @param ids
	 * @return
	 */
	@DeleteMapping(value = "/deleteBatch")
	public Result<MapConfigMain> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		Result<MapConfigMain> result = new Result<MapConfigMain>();
		if(ids==null || "".equals(ids.trim())) {
			result.error500("参数不识别！");
		}else {
			this.mapConfigMainService.delBatchMain(Arrays.asList(ids.split(",")));
			result.success("删除成功!");
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryById")
	public Result<MapConfigMain> queryById(@RequestParam(name="id",required=true) String id) {
		Result<MapConfigMain> result = new Result<MapConfigMain>();
		MapConfigMain mapConfigMain = mapConfigMainService.getById(id);
		if(mapConfigMain==null) {
			result.error500("未找到对应实体");
		}else {
			result.setResult(mapConfigMain);
			result.setSuccess(true);
		}
		return result;
	}
	
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryMapConfigCustomByMainId")
	public Result<List<MapConfigCustom>> queryMapConfigCustomListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<MapConfigCustom>> result = new Result<List<MapConfigCustom>>();
		List<MapConfigCustom> mapConfigCustomList = mapConfigCustomService.selectByMainId(id);
		result.setResult(mapConfigCustomList);
		result.setSuccess(true);
		return result;
	}
	/**
	  * 通过id查询
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/queryMapConfigTicketByMainId")
	public Result<List<MapConfigTicket>> queryMapConfigTicketListByMainId(@RequestParam(name="id",required=true) String id) {
		Result<List<MapConfigTicket>> result = new Result<List<MapConfigTicket>>();
		List<MapConfigTicket> mapConfigTicketList = mapConfigTicketService.selectByMainId(id);
		result.setResult(mapConfigTicketList);
		result.setSuccess(true);
		return result;
	}

  /**
      * 导出excel
   *
   * @param request
   * @param response
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, HttpServletResponse response) {
      // Step.1 组装查询条件
      QueryWrapper<MapConfigMain> queryWrapper = null;
      try {
          String paramsStr = request.getParameter("paramsStr");
          if (oConvertUtils.isNotEmpty(paramsStr)) {
              String deString = URLDecoder.decode(paramsStr, "UTF-8");
              MapConfigMain mapConfigMain = JSON.parseObject(deString, MapConfigMain.class);
              queryWrapper = QueryGenerator.initQueryWrapper(mapConfigMain, request.getParameterMap());
          }
      } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
      }

      //Step.2 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      List<MapConfigMainPage> pageList = new ArrayList<MapConfigMainPage>();
      List<MapConfigMain> MapConfigMainList = mapConfigMainService.list(queryWrapper);
      for (MapConfigMain MapConfigMain : MapConfigMainList) {
          MapConfigMainPage vo = new MapConfigMainPage();
          BeanUtils.copyProperties(MapConfigMain, vo);
          List<MapConfigCustom> mapConfigCustomList = mapConfigCustomService.selectByMainId(MapConfigMain.getId());
          vo.setMapConfigCustomList(mapConfigCustomList);
          List<MapConfigTicket> mapConfigTicketList = mapConfigTicketService.selectByMainId(MapConfigMain.getId());
          vo.setMapConfigTicketList(mapConfigTicketList);
          pageList.add(vo);
      }
      //导出文件名称
      mv.addObject(NormalExcelConstants.FILE_NAME, "订单列表");
      mv.addObject(NormalExcelConstants.CLASS, MapConfigMainPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("订单列表数据", "导出人:Jeecg", "导出信息"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
  }

  /**
      * 通过excel导入数据
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
  public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          MultipartFile file = entity.getValue();// 获取上传文件对象
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<MapConfigMainPage> list = ExcelImportUtil.importExcel(file.getInputStream(), MapConfigMainPage.class, params);
              for (MapConfigMainPage page : list) {
                  MapConfigMain po = new MapConfigMain();
                  BeanUtils.copyProperties(page, po);
                  mapConfigMainService.saveMain(po, page.getMapConfigCustomList(),page.getMapConfigTicketList());
              }
              return Result.ok("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.ok("文件导入失败！");
  }
}
