package TimsheetTasks;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class FetchingDataFromExcel {
    static HashMap<String, String> hashMap1;
    static   HashMap<String, String> hashMap2;


    public static void main(String[] args) throws IOException {
      //  createHashMap(findColumnValue(getSheet()),getSheet());
        generateRandomStringUtils();
    }

    public static void createHashMap(int[] arr, XSSFSheet sheet) {
         hashMap1 = new HashMap<String, String>();
         hashMap2 = new HashMap<String, String>();


        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            String key = row.getCell(arr[0]).getStringCellValue();
            String Value = row.getCell(arr[1]).getStringCellValue();
            hashMap1.put(key, Value);
            hashMap2.put(Value, key);
        }
        System.out.println(hashMap1.toString());
        System.out.println(hashMap2.toString());
    }


    public static int[] findColumnValue(XSSFSheet sheet) throws IOException {
        int[] arr = new int[2];
        XSSFRow row;

        row = sheet.getRow(0);
        for (int i = 0; i < row.getLastCellNum(); i++) {
            if (row.getCell(i).getStringCellValue().equalsIgnoreCase("EMP_NAME")) {
                arr[0] = i;
                break;
            }
        }
        for (int j = 0; j < row.getLastCellNum(); j++) {
            if (row.getCell(j).getStringCellValue().equalsIgnoreCase("FG_NAME")) {
                arr[1] = j;
                break;
            }
        }
        return arr;
    }


    public static XSSFSheet getSheet() throws IOException {
        FileInputStream fis = new FileInputStream("/Users/kumarmayank/Documents/DemoData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet1");
        return sheet;
    }


    public static String getFGName(String str){
       return  hashMap1.get(str);

    }

    public static String generateRandomStringUtils(){
        String str="Auto/";
      String rand_int=  RandomStringUtils.randomAlphanumeric(10);
        String finalString=str+rand_int;
        return finalString;
    }

}
