package com.wl.springboot_pe_2211.demo;


import org.apache.commons.lang3.builder.ToStringExclude;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoiTest {


    //如果单元测试报错   在pom.xml修改单元测试依赖的范围为compile
    @Test
    public void readExcel() throws IOException {
        //定义文件的路径
        String path = "D:\\software\\ideaworkspace\\springboot_pe_2211\\src\\main\\resources\\read.xlsx";
        //通过io流获取到文件
        FileInputStream fileInputStream = new FileInputStream(path);
        //创建XSSFWorkbook对象来操作excel表格
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        //获取到索引为0的sheet
        XSSFSheet sheet = workbook.getSheetAt(0);
        //获取到第三行 索引是从0开始的
        XSSFRow row = sheet.getRow(2);
        //获取到第二列 索引是从0开始的
        XSSFCell cell = row.getCell(1);
        //将单元格的值用string类型获取
        String value = cell.getStringCellValue();
        System.out.println("value = " + value);

        workbook.close();
        fileInputStream.close();

    }

    //写 write.xlsx
    @Test
    public void writeExcel() throws IOException {
        List<String[]> stringList = new ArrayList<>();
        stringList.add(new String[]{"姓名","年龄","性别"});
        stringList.add(new String[]{"王小二","11","男"});
        stringList.add(new String[]{"王小三","12","男"});
        stringList.add(new String[]{"王小五","15","男"});


        //创建操作excel表格的对象
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建sheet
        XSSFSheet sheet = workbook.createSheet("学生信息表");
        //对list进行遍历
        for (int j = 0; j < stringList.size();j++) {
            //创建第j行
            XSSFRow row = sheet.createRow(j);
            for (int i = 0; i < stringList.get(j).length; i++) {
                XSSFCell cell = row.createCell(i, 1);
                cell.setCellValue(stringList.get(j)[i]);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\software\\ideaworkspace\\springboot_pe_2211\\src\\main\\resources\\write.xlsx");
        workbook.write(fileOutputStream);
        workbook.close();
        fileOutputStream.close();


    }

    //读write.xlsx
    @Test
    public void readExcel1() throws IOException {
       FileInputStream is = new FileInputStream("D:\\software\\ideaworkspace\\springboot_pe_2211\\src\\main\\resources\\write.xlsx");
       XSSFWorkbook workbook = new XSSFWorkbook(is);
       //读sheet
        XSSFSheet sheet = workbook.getSheet("学生信息表");
        //获取最后一行的索引
        int lastRowNum = sheet.getLastRowNum();
        //遍历lastRowNum 次
        for (int i = 0; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            //获取最后一列的num
            short lastCellNum = row.getLastCellNum();

            for (int j = 0; j <= lastCellNum; j++) {
                XSSFCell cell = row.getCell(j);
                //如果cell不为空
                if (cell != null) {
                    String stringCellValue = cell.getStringCellValue();
                    System.out.print(stringCellValue + "   ");
                }

            }
            System.out.println(

            );

        }


    }

}



