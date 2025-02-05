package utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    //Data provider1

    @DataProvider(name = "loginData")
    public String[][] getData() throws IOException {

        String path = ".\\testData\\Opencart_LoginData.xlsx";  //taking xl file from testData

        ExcelUtility xlutil = new ExcelUtility(path); //creating an object for XLUtility

        int totalRows = xlutil.getRowCount("Sheet1");
        int totalCols = xlutil.getCellCount("Sheet1", totalRows);

        String[][] loginData = new String[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j <= totalCols - 1; j++) {
                loginData[i - 1][j] = xlutil.getCellData("Sheet1", i, j);

            }
        }
        return loginData; //returning a two-dimensional array

    }


    public void readingLoginData() throws IOException {
        String[][] arr = getData();
        for (String[] array : arr) {
            for (String x : array) {
                System.out.print(x + "\t");
            }
            System.out.println();
        }
    }
}
