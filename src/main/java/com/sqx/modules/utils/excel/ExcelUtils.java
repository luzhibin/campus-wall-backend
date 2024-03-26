package com.sqx.modules.utils.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author fang
 * @date 2021/1/27
 */
@Slf4j
public class ExcelUtils {

    /*public static List<OilStation> get(String fileUrl){
        MultipartFile fileItem = createFileItem(fileUrl, null);
        String substring = fileUrl.substring(fileUrl.lastIndexOf("."));
        List<OilStation> list=new ArrayList<>();
        try {
//            String fileName = fileItem.getOriginalFilename();
            String xls=".xlsx";
            InputStream inputStream = fileItem.getInputStream();
            log.info("文件名:{}", substring);
            int serviceStationNo=0;
            int serviceStationName=0;
            int oilName=0;
            int activity=0;
            int downPrice=0;
            //区分两种excel表格
            if(substring.indexOf(xls)!=-1){
                XSSFWorkbook workbook=new XSSFWorkbook(inputStream);
                //获取第一个工作表
                org.apache.poi.xssf.usermodel.XSSFSheet hs=workbook.getSheetAt(0);
                //获取Sheet的第一个行号和最后一个行号
                int last=hs.getLastRowNum();
                int first=hs.getFirstRowNum();
                //遍历获取单元格里的信息
                for (int i = first; i <=last; i++) {
                    XSSFRow row=hs.getRow(i);
                    int firstCellNum=row.getFirstCellNum();//获取所在行的第一个行号
                    int lastCellNum=row.getLastCellNum();//获取所在行的最后一个行号
                    OilStation oilStation=new OilStation();
                    for (int j = firstCellNum; j <lastCellNum; j++) {
                        XSSFCell cell=row.getCell(j);
                        cell.setCellType(CellType.STRING);
                        String value=cell.getStringCellValue();
                        if("油站编码".equals(value)){
                            serviceStationNo=j;
                        }else if("油站名称".equals(value)){
                            serviceStationName=j;
                        }else if("油品".equals(value)){
                            oilName=j;
                        }else if("活动时间".equals(value)){
                            activity=j;
                        }else if("直降".equals(value)){
                            downPrice=j;
                        }else{
                            if(j==serviceStationNo){
                                oilStation.setServiceStationNo(value);
                            }else if(j==serviceStationName){
                                oilStation.setServiceStationName(value);
                            }else if(j==oilName){
                                oilStation.setOilName(value);
                            }else if(j==activity){
                                oilStation.setActivity(value);
                            }else if(j==downPrice){
                                oilStation.setDownPrice(value);
                            }
                        }
                    }
                    if(StringUtils.isNotEmpty(oilStation.getServiceStationNo())){
                        list.add(oilStation);
                    }
                }
            }else{
                HSSFWorkbook workbook=new HSSFWorkbook(inputStream);
                //获取第一个工作表
                HSSFSheet hs=workbook.getSheetAt(0);
                //获取Sheet的第一个行号和最后一个行号
                int last=hs.getLastRowNum();
                int first=hs.getFirstRowNum();
                //遍历获取单元格里的信息
                for (int i = first; i <=last; i++) {
                    HSSFRow row=hs.getRow(i);
                    int firstCellNum=row.getFirstCellNum();//获取所在行的第一个行号
                    int lastCellNum=row.getLastCellNum();//获取所在行的最后一个行号
                    OilStation oilStation=new OilStation();
                    for (int j = firstCellNum; j <lastCellNum; j++) {
                        HSSFCell cell=row.getCell(j);
                        cell.setCellType(CellType.STRING);
                        String value=cell.getStringCellValue();
                        if("油站编码".equals(value)){
                            serviceStationNo=j;
                        }else if("油站名称".equals(value)){
                            serviceStationName=j;
                        }else if("油品".equals(value)){
                            oilName=j;
                        }else if("活动时间".equals(value)){
                            activity=j;
                        }else if("直降".equals(value)){
                            downPrice=j;
                        }else{
                            if(j==serviceStationNo){
                                oilStation.setServiceStationNo(value);
                            }else if(j==serviceStationName){
                                oilStation.setServiceStationName(value);
                            }else if(j==oilName){
                                oilStation.setOilName(value);
                            }else if(j==activity){
                                oilStation.setActivity(value);
                            }else if(j==downPrice){
                                oilStation.setDownPrice(value);
                            }
                        }
                    }
                    if(StringUtils.isNotEmpty(oilStation.getServiceStationNo())){
                        list.add(oilStation);
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }*/


    public static MultipartFile createFileItem(String url, String fileName) {
        FileItem item = null;
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setReadTimeout(30000);
            conn.setConnectTimeout(30000);
            //设置应用程序要从网络连接读取数据
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream is = conn.getInputStream();

                FileItemFactory factory = new DiskFileItemFactory(16, null);
                String textFieldName = "uploadfile";
                item = factory.createItem(textFieldName, ContentType.APPLICATION_OCTET_STREAM.toString(), false, fileName);
                OutputStream os = item.getOutputStream();

                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = is.read(buffer, 0, 8192)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.close();
                is.close();
            }
        } catch (IOException e) {
            throw new RuntimeException("文件下载失败", e);
        }

        return new CommonsMultipartFile(item);
    }


}