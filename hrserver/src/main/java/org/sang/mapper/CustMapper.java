package org.sang.mapper;

import org.apache.ibatis.annotations.Param;
import org.sang.bean.Cust;

import java.util.Date;
import java.util.List;

/**
 * Created by sang on 2018/1/12.
 */
public interface CustMapper{

    /**
     * @description 
     * @author xyzhao
     * @date 2019年2月27日上午10:58:07
     * @version v2.0
     */
    int addCust(Cust cust);

    /**
     * @description 
     * @author xyzhao
     * @date 2019年2月27日下午12:05:06
     * @version v2.0
     */
    List<Cust> getCusts(Cust cust);

    /**
     * @description 
     * @author xyzhao
     * @date 2019年2月27日下午12:17:55
     * @version v2.0
     */
    int insertList(List<Cust> custs);

    /**
     * @description 
     * @author xyzhao
     * @date 2019年2月27日下午3:31:28
     * @version v2.0
     */
    void updateExportMark(List<String> list);
}
