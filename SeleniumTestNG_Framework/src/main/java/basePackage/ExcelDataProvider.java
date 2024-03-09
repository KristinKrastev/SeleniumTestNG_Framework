package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

	private static final DataFormatter dataFormatter = new DataFormatter();
	
	
	
	
	@DataProvider(name="excelData")
	public static Object[][] getData(Method method) throws IOException{
		File file = new File(System.getProperty("user.dir")+"/src/test/resources/ExcelFiles/TestAutomationDemo.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		
		XSSFSheet sheet = workbook.getSheet(method.getName());
		
		Iterable<Row> rows = sheet::rowIterator;
		List<Map<String, String>> results = new ArrayList<>();
		boolean header = true; // ???
		
		List<String> keys = null;
		
		for (Row row : rows) {
			
			List<String> values = getValuesInEachRow(row);
			if(header) {
				header = false;
				keys=values;
				continue;
			}
			results.add(transform(keys, values));
		}
			workbook.close();
		return asTwoDimensionalArray(results);
		
	}
	
	 private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) {
		    Object[][] results = new Object[interimResults.size()][1];
		    int index = 0;
		    for (Map<String, String> interimResult : interimResults) {
		      results[index++] = new Object[] {interimResult};
		    }
		    return results;
		  }
	
	private static Map<String, String> transform(List<String> names, List<String> values) {
	    Map<String, String> results = new HashMap<>();
	    for (int i = 0; i < names.size(); i++) {
	      String key = names.get(i);
	      String value = values.get(i);
	      results.put(key, value);
	    }
	    return results;
	  }
	
	private static List<String> getValuesInEachRow(Row row){
		List <String> data = new ArrayList<>();
		
		Iterable<Cell> columns = row::cellIterator;
		for (Cell column : columns) {
			data.add(dataFormatter.formatCellValue(column));
		}
		
		return data;
	}

}
