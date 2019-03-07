package org.sang.common.poi;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.sang.bean.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Created by sang on 2018/1/16.
 */
public class PoiUtils {


  /**
   * @description
   * @author xyzhao
   * @date 2019年2月27日上午11:26:26
   * @version v2.0
   */
  public static ResponseEntity<byte[]> exportCust2Excel(List<Cust> custs) {
     HttpHeaders headers = null;
     ByteArrayOutputStream baos = null;
     try {
     //1.创建Excel文档
     HSSFWorkbook workbook = new HSSFWorkbook();
     //2.创建文档摘要
     workbook.createInformationProperties();
     //3.获取文档信息，并配置
     DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
     //3.1文档类别
     dsi.setCategory("客户信息");
//     //3.2设置文档管理员
//     dsi.setManager("江南一点雨");
//     //3.3设置组织机构
//     dsi.setCompany("XXX集团");
     //4.获取摘要信息并配置
     SummaryInformation si = workbook.getSummaryInformation();
     //4.1设置文档主题
     si.setSubject("客户信息表");
     //4.2.设置文档标题
     si.setTitle("客户信息");
//     //4.3 设置文档作者
//     si.setAuthor("XXX集团");
//     //4.4设置文档备注
//     si.setComments("备注信息暂无");
     //创建Excel表单
     HSSFSheet sheet = workbook.createSheet("客户信息表");
     //创建日期显示格式
     HSSFCellStyle dateCellStyle = workbook.createCellStyle();
     dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
     //创建标题的显示样式
     HSSFCellStyle headerStyle = workbook.createCellStyle();
     headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
     headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
     //定义列的宽度
     sheet.setColumnWidth(0, 5 * 256);
     sheet.setColumnWidth(1, 12 * 256);
     sheet.setColumnWidth(2, 10 * 256);
     sheet.setColumnWidth(3, 5 * 256);
     sheet.setColumnWidth(4, 12 * 256);
     sheet.setColumnWidth(5, 20 * 256);
     sheet.setColumnWidth(6, 10 * 256);
     //5.设置表头
     HSSFRow headerRow = sheet.createRow(0);
     HSSFCell cell0 = headerRow.createCell(0);
     cell0.setCellValue("编号");
     cell0.setCellStyle(headerStyle);
     HSSFCell cell1 = headerRow.createCell(1);
     cell1.setCellValue("省份");
     cell1.setCellStyle(headerStyle);
     HSSFCell cell2 = headerRow.createCell(2);
     cell2.setCellValue("公司名称");
     cell2.setCellStyle(headerStyle);
     HSSFCell cell3 = headerRow.createCell(3);
     cell3.setCellValue("姓名");
     cell3.setCellStyle(headerStyle);
     HSSFCell cell4 = headerRow.createCell(4);
     cell4.setCellValue("手机号");
     cell4.setCellStyle(headerStyle);
     HSSFCell cell5 = headerRow.createCell(5);
     cell5.setCellValue("座机号");
     cell5.setCellStyle(headerStyle);
     HSSFCell cell6 = headerRow.createCell(6);
     cell6.setCellValue("职位");
     cell6.setCellStyle(headerStyle);
     //6.装数据
     for (int i = 0; i < custs.size(); i++) {
     HSSFRow row = sheet.createRow(i + 1);
     Cust cust = custs.get(i);
     row.createCell(0).setCellValue(i+1);
     row.createCell(1).setCellValue(cust.getProvience());
     row.createCell(2).setCellValue(cust.getCompany());
     row.createCell(3).setCellValue(cust.getNm());
     row.createCell(4).setCellValue(cust.getMobile());
     row.createCell(5).setCellValue(cust.getTele());
     row.createCell(6).setCellValue(cust.getPosition());
     }
     headers = new HttpHeaders();
     headers.setContentDispositionFormData("attachment",
     new String("客户表.xls".getBytes("UTF-8"), "iso-8859-1"));
     headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
     baos = new ByteArrayOutputStream();
     workbook.write(baos);
     } catch (IOException e) {
     e.printStackTrace();
     }
     return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
  }

  public static List<Cust> importCust2List(MultipartFile file) {
    List<Cust> custs = new ArrayList<>();
    try {
      HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
      int numberOfSheets = workbook.getNumberOfSheets();
      for (int i = 0; i < numberOfSheets; i++) {
        HSSFSheet sheet = workbook.getSheetAt(i);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
        Cust cust;
        for (int j = 0; j < physicalNumberOfRows; j++) {
          if (j == 0) {
            continue;// 标题行
          }
          HSSFRow row = sheet.getRow(j);
          if (row == null) {
            continue;// 没数据
          }
          short physicalNumberOfCells = row.getLastCellNum();
          cust = new Cust();
          for (int k = 0; k < physicalNumberOfCells; k++) {
            HSSFCell cell = row.getCell(k);
            if(null == cell) {
              continue;
            }
            CellType cellTypeEnum = cell.getCellTypeEnum();
            switch (cell.getCellTypeEnum()) {
              case STRING: {
                String cellValue = cell.getStringCellValue();
                if (cellValue == null) {
                  cellValue = "";
                }
                switch (k) {
                  case 1:
                    cust.setProvience(cellValue);
                    break;
                  case 2:
                    cust.setCompany(cellValue);
                    break;
                  case 3:
                    cust.setNm(cellValue.trim());
                    break;
                  case 4:
                    cust.setMobile(cellValue.trim());
                    break;
                  case 5:
                    cust.setTele(cellValue);
                    break;
                  case 6:
                    cust.setPosition(cellValue);
                    break;
                  default:
                    break;
                }
              }
                break;
              case NUMERIC:{
                HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                String cellValue = dataFormatter.formatCellValue(cell);
                switch (k) {
                  case 4:
                    cust.setMobile(cellValue);
                    break;
                  case 5:
                    cust.setTele(cellValue);
                    break;
                  default:
                    break;
                }
              }
              default:
                break;
            }
          }
          String mobile = cust.getMobile();
          String reg = "^[1]\\d{10}$";
          if(StringUtils.isEmpty(cust.getNm()) || StringUtils.isEmpty(cust.getMobile())) {
            continue;
          } else if(!Pattern.matches(reg, mobile)) {
            continue;
          }
          cust.setId(UUID.randomUUID().toString().replaceAll("-", ""));
          cust.setDtCreated(new Date());
          custs.add(cust);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return custs;
  }
}
