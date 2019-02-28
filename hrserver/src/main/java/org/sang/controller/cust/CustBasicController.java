package org.sang.controller.cust;

import org.apache.commons.collections4.CollectionUtils;
import org.sang.bean.Cust;
import org.sang.bean.RespBean;
import org.sang.common.poi.PoiUtils;
import org.sang.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by sang on 2018/1/12.
 */
@RestController
@RequestMapping("/cust/basic")
public class CustBasicController {
  @Autowired
  CustService custService;

  /**
   * @description 列表
   * @author xyzhao
   * @date 2019年2月28日上午10:56:40
   * @version v2.0
   */
  @RequestMapping(value = "/cust", method = RequestMethod.GET)
  public Map<String, Object> getCusts(
      @RequestParam(defaultValue = "1") Integer pageNum,
      @RequestParam(defaultValue = "10") Integer pageSize,
      @RequestParam(required = false) List<String> ids,
      @RequestParam(required = false) String provience,
      @RequestParam(required = false) String company, 
      @RequestParam(required = false) String nm,
      @RequestParam(required = false) String mobile, 
      @RequestParam(required = false) String tele,
      @RequestParam(required = false) String position,
      @RequestParam(required = false) String isExport) {
    Map<String, Object> result = new HashMap<>();
    PageHelper.startPage(pageNum, pageSize);
    List<Cust> custByPage =
        custService.getCusts(ids, provience, company, nm, mobile, tele, position, isExport);
    PageInfo<Cust> pageInfo = new PageInfo<>(custByPage);
    long total = pageInfo.getTotal();
    result.put("emps", custByPage);
    result.put("count", total);
    return result;
  }

  /**
   * @description 导出
   * @author xyzhao
   * @date 2019年2月28日上午10:08:10
   * @version v2.0
   */
  @RequestMapping(value = "/exportCust", method = RequestMethod.GET)
  public ResponseEntity<byte[]> exportCust(
      @RequestParam(required = false) List<String> ids,
      @RequestParam(required = false) String provience,
      @RequestParam(required = false) String company, 
      @RequestParam(required = false) String nm,
      @RequestParam(required = false) String mobile, 
      @RequestParam(required = false) String tele,
      @RequestParam(required = false) String position,
      @RequestParam(required = false) String isExport) {
    List<Cust> custs = custService.getCusts(ids, provience, company, nm, mobile, tele, position, isExport);
    // 标记已导出
    List<String> exportIds = custs.stream().map(r -> r.getId()).collect(Collectors.toList());
    custService.updateExportMark(exportIds);
    return PoiUtils.exportCust2Excel(custs);
  }

  /**
   * @description 导入
   * @author xyzhao
   * @date 2019年2月27日下午1:50:44
   * @version v2.0
   */
  @RequestMapping(value = "/importCust", method = RequestMethod.POST)
  public RespBean importEmp(MultipartFile file) {
    List<Cust> custs = PoiUtils.importCust2List(file);
    if(CollectionUtils.isEmpty(custs)) {
      return RespBean.ok("导入成功!",0);
    }
    int addCusts = custService.addCusts(custs);
    if (addCusts == custs.size()) {
      return RespBean.ok("导入成功!",addCusts);
    }
    return RespBean.error("导入失败!");
  }
}
