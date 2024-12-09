package org.APIAutomationFramework.util;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadDataFromExcel {

    Workbook workbook;
    Sheet sheet;

    public Object[][] readDataUsingExcelDoc(String sheetName){

        FileInputStream file;

        try {
            file = new FileInputStream("src/test/resources/docst.xlsx");
        }catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

        try {
            workbook = WorkbookFactory.create(file);
        }catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        sheet = workbook.getSheet(sheetName);

        Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

        for (int i=0; i<sheet.getLastRowNum(); i++){
            for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++){
                data[i][j] = sheet.getRow(i+1).getCell(j).toString();
            }
        }
        return data;
    }

    @DataProvider
    public Object[][] getData(){

        return readDataUsingExcelDoc("Sheet1");
    }
}
