package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders10 {

	//DP -> It will get data from the excel sheet and stored in 2 dimensional array. 
	
	// ExcelUtility9 are used here
	
	
	//DataProvider 1 
	
	@DataProvider(name = "LoginData")
	public String[][] getData() throws IOException
	{
		String path =".\\testData\\LoginData_OpenCart.xlsx"; //taking xl file from testData
		
		ExcelUtility9 xlutil = new ExcelUtility9(path); //creating ab object for xlutility
		
		int totalrows = xlutil.getRowCount("Sheet1"); 		// to get total no of rows in Sheet1
		
		int totalcols = xlutil.getCellCount("Sheet1", 1);	// to get total no of cols in Sheet1 and starts from row1
		
		//Here result is 5,3. so need to delete 1 row in below line to read all 4 rows
		System.out.println("totalrows: " + totalrows + " and totalcols: " + totalcols);
		
		// Data array size excludes header row (hence totalrows-1)
		String logindata[][] = new String [totalrows - 1][totalcols]; // created for two dimension array which can store 
		
		//i=1, ignoring header row i.e., i=0
		for (int i=1; i<totalrows; i++) //1 // read the data from xl storing in two dimensional array
		{
			for (int j=0; j<totalcols; j++) //0 i is rows j is col
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j).trim(); //1,0 // to trim spaces
				//i-1, becoz array index starts from 0
			}
		}
		
	return logindata; //returning two dimension array
	}
	
}
