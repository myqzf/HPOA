package com.hpkj.staff.util;


import java.io.File;
import  java.io.FileNotFoundException;
import  java.io.FileOutputStream;
import  java.io.IOException;
import java.util.ArrayList;
import  java.util.Calendar;
import java.util.List;

import  org.apache.poi.hssf.usermodel.HSSFCell;
import  org.apache.poi.hssf.usermodel.HSSFCellStyle;
import  org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import  org.apache.poi.hssf.usermodel.HSSFRow;
import  org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.hpkj.core.exception.BaseException;
import com.hpkj.staff.vo.StaffDetailInfoVo;

/** 
 * 生成导出Excel文件对象
* 
*  @author  lpl
* 
 */ 
 public   class  ToExcel  {

 	//  设置cell编码解决中文高位字节截断 
// 	private static short XLS_ENCODING=HSSFWorkbook.ENCODING_UTF_16;
 	
 	//  定制日期格式 
 	private static  String DATE_FORMAT=" m/d/yy " ;  //  "m/d/yy h:mm"
 	//  定制浮点数格式 
// 	private   static  String NUMBER_FORMAT  =   " #,##0.00 " ;
    private  String xlsFileName;
    private  HSSFWorkbook workbook;
    private  HSSFSheet sheet;
    private  HSSFRow row;
    
    /** 
     * 初始化Excel
     * @param fileName
     */
	public  ToExcel(String fileName,String[]TableTitle,List<StaffDetailInfoVo> list)  {
		this .xlsFileName  =  fileName;
		this .workbook  =   new  HSSFWorkbook();
		this .sheet  =  workbook.createSheet();	
		this.createRow(0);
		//添加表头
		for(int i=0;i<TableTitle.length;i++){
			this.setCell((short) i, new HSSFRichTextString(TableTitle[i].toString()));
			this.setCellBold((short)i);
			this.setCollWidth((short)i, TableTitle[i].length()*1000);
		}
		//设置表头高度
		this.setRowHeight((short)0,(short)500);
		//添加数据 
		//添加数据
		if(!list.isEmpty()){
			for(int i=0;i<list.size();i++){
				this.createRow((short) (i + 1));
				Object[] o=list.get(i).toObject();				
				for(int j=0;j<o.length;j++){
					if(o[j]==null){
						this.setCell((short)(j),new HSSFRichTextString(""));
					}
					else{
						this.setCell((short)(j),new HSSFRichTextString(o[j].toString()));
					}
				}				
			}
		}
	} 
	
	/**
	 * 导出Excel文件
	 * @throws BaseException
	 */
	public void exportXLS() throws BaseException{
        try{
	        File file=new File(xlsFileName);
	        if (!file.getParentFile().exists()){
	        	file.getParentFile().mkdirs();  
	    	}
           FileOutputStream fOut=new  FileOutputStream(xlsFileName);
           workbook.write(fOut);
           fOut.flush();
           fOut.close();
       }catch(FileNotFoundException e)  {
           e.printStackTrace();
           throw new  BaseException( " 生成导出Excel文件出错! " , e);
       }catch  (IOException e)  {
    	   throw   new  BaseException( " 写入Excel文件出错! " , e);        
       } 
 
	} 
 
     /** 
      * 增加一行
      * @param index
      */
      public   void  createRow( int  index){
    	  this.row  =   this .sheet.createRow(index);
      } 
      public void createCell(short index){
    	  this.row.createCell(index);
      }
      
     /** 
      * 设置单元格
      * @param index列号
      * @param value单元格填充值
      */
      public   void  setCell( int  index, HSSFRichTextString hssfRichTextString)  {
	       HSSFCell cell  =   this .row.createCell(( short ) index);
	       cell.setCellType(HSSFCell.CELL_TYPE_STRING);
	//       cell.setEncoding(XLS_ENCODING);
	       cell.setCellValue(hssfRichTextString);
      }
      /**
       * 设置列宽
       * @param index列号
       * @param width列宽值 
       */
      public void setCollWidth(int index,int width){
    	  this.sheet.setColumnWidth(index, width);
      }
      /**
       * 设置表头单元格样式，字体加粗
       * @param index
       */
      public void setCellBold(short index){
    	  	HSSFCellStyle style =workbook.createCellStyle(); // 样式对象
    	  	HSSFFont font=workbook.createFont();
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
	    	font.setFontHeightInPoints((short) 14);
	        style.setFont(font);
	        this.row.getCell(index).setCellStyle(style);
      }
      /**
       * 设置单元格字体大小
       * @param index
       * @param height
       */
      public void setCellFont(short index,short height){
    	  HSSFCellStyle style=workbook.createCellStyle();
    	  HSSFFont font=workbook.createFont();
    	  font.setFontHeightInPoints(height);
    	  style.setFont(font);
    	  this.row.getCell(index).setCellStyle(style);
      }
      /**
       * 设置行高
       * @param index
       * @param height
       */
      public void setRowHeight(short index,short height){
    	  this.sheet.getRow(index).setHeight(height);
      }
     /** 
      * 设置单元格
      * @param index列号
      * @param value单元格填充值 
      */
      public void setCell( int  index, Calendar value)  {
	       HSSFCell cell  =   this .row.createCell(( short ) index);
	       cell.setCellValue(value.getTime());
	       HSSFCellStyle cellStyle  =  workbook.createCellStyle();  //  建立新的cell样式 
	       cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat(DATE_FORMAT));  //  设置cell样式为定制的日期格式 
	       cell.setCellStyle(cellStyle);  //  设置该cell日期的显示格式 
      } 
 
     /** 
      * 设置单元格
      * @param index
      * @param value
      */
      public   void  setCell( int  index,  int  value)  {
	       HSSFCell cell  =   this .row.createCell(( short ) index);
	       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	       cell.setCellValue(value);
      } 
 
     /**
      * 设置单元格
      * @param index
      * @param value
      */
      public   void  setCell( int  index,  double  value)  {
	       HSSFCell cell  =   this .row.createCell(( short ) index);
	       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
	       cell.setCellValue(value);
	       HSSFCellStyle cellStyle  =  workbook.createCellStyle();  //  建立新的cell样式 
	       HSSFDataFormat format  =  workbook.createDataFormat();
     } 
 
}