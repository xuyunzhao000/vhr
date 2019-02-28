package org.sang.service;

import org.sang.bean.Cust;
import org.sang.mapper.CustMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sang on 2018/1/12.
 */
@Service
@Transactional
public class CustService {
  @Autowired
  CustMapper custMapper;
  SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
  SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
  SimpleDateFormat birthdayFormat = new SimpleDateFormat("yyyy-MM-dd");
  DecimalFormat decimalFormat = new DecimalFormat("##.00");

  /**
   * @description 添加客户信息
   * @author xyzhao
   * @date 2019年2月27日上午10:57:07
   * @version v2.0
   */
  public int addCust(Cust cust) {
    cust.setId("");
    cust.setDtCreated(new Date());
    cust.setVersion(0);
    return custMapper.addCust(cust);
  }

  /**
   * @description 获取客户信息列表
   * @author xyzhao
   * @date 2019年2月27日上午11:18:32
   * @version v2.0
   * @param isExport 
   */
  public List<Cust> getCusts(List<String> ids, String provience, String company, String nm,
      String mobile, String tele, String position, String isExport) {
    Cust cust = new Cust();
    cust.setIds(ids);
    cust.setProvience(provience);
    cust.setCompany(company);
    cust.setNm(nm);
    cust.setMobile(mobile);
    cust.setTele(tele);
    cust.setPosition(position);
    cust.setIsExport(isExport);
    return custMapper.getCusts(cust);
  }

  /**
   * @description 批量添加客户信息
   * @author xyzhao
   * @date 2019年2月27日下午12:17:26
   * @version v2.0
   */
  public int addCusts(List<Cust> custs) {
    return custMapper.insertList(custs);
  }

  /**
   * @description 标记已导出
   * @author xyzhao
   * @date 2019年2月27日下午3:31:08
   * @version v2.0
   */
  public void updateExportMark(List<String> exportIds) {
    custMapper.updateExportMark(exportIds);
  }
}
