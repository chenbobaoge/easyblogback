package com.bobochen.easyblogback.allinone.controller;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@RestController
public class ExcelController {
    @RequestMapping(value = "/readexcel",method = RequestMethod.POST)
    public boolean ReadExcel(){
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File moban = new File(path.getAbsolutePath(), "myreadexcel/资料模板.xlsx");


            if(moban.exists()) {
                System.out.println("存在");

                //1.读取Excel的对象
                FileInputStream  poifsFileSystem = new FileInputStream (moban);
                //2.Excel工作薄对象
                Workbook hssfWorkbook = new XSSFWorkbook(poifsFileSystem);
                //3.Excel工作表对象
                Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
                for (int i = 0; i < hssfSheet.getLastRowNum()+1; i++) {
                    //获取Excel工作表的行
                    Row hssfRow1 = hssfSheet.getRow(i);
                    for (int j = 0; j < hssfRow1.getLastCellNum(); j++) {
                        //获取指定单元格
                        Cell hssfCell1 = hssfRow1.getCell(j);

                        //Excel数据Cell有不同的类型，当我们试图从一个数字类型的Cell读取出一个字符串时就有可能报异常：
                        //Cannot get a STRING value from a NUMERIC cell
                        //将所有的需要读的Cell表格设置为String格式
                        if (hssfCell1 != null) {
                            hssfCell1.setCellType(CellType.STRING);
                        }

                        //获取每一列中的值
                        System.out.print(hssfCell1.getStringCellValue() + "\t");
                    }
                    System.out.println();
                }


                return true;
            }else{
                System.out.println("不存在");
                return false;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }




    @RequestMapping(value = "/writeexcel",method = RequestMethod.POST)
    public boolean WriteExcel(){
        try {
            File path = new File(ResourceUtils.getURL("classpath:").getPath());


            if(path.exists()) {
                Workbook workbook = new XSSFWorkbook();
                Sheet sheet = workbook.createSheet("0");

                CellStyle cellStyle = workbook.createCellStyle();
                // 设置这些样式
                cellStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
                cellStyle.setFillBackgroundColor(IndexedColors.SKY_BLUE.getIndex());
                cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cellStyle.setBorderBottom(BorderStyle.THIN);
                cellStyle.setBorderLeft(BorderStyle.THIN);
                cellStyle.setBorderRight(BorderStyle.THIN);
                cellStyle.setBorderTop(BorderStyle.THIN);
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                Font cellfont = workbook.createFont();
                cellfont.setColor(IndexedColors.RED.getIndex());
                cellStyle.setFont(cellfont);



                for(int rowi=0;rowi<100;rowi++){
                    Row row = sheet.createRow(rowi);
                    for(int cellj=0;cellj<10;cellj++) {
                       Cell cell =  row.createCell(cellj);
                       cell.setCellStyle(cellStyle);
                       cell.setCellValue("姓名" + rowi + cellj);

                    }
                }

                workbook.setSheetName(0, "信息");
                File file = new File(path.getAbsolutePath(), "myreadexcel//writeexcel.xlsx");
                FileOutputStream fileoutputStream = new FileOutputStream(file);
                workbook.write(fileoutputStream);
                fileoutputStream.close();
                return true;
            }else{
                System.out.println("不存在");
                return false;

            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
