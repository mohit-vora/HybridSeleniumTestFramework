package utils;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebTable extends BaseClass{
	
	WebElement table = null;
	List<WebElement> rows = null;

	public WebTable(WebElement table) {
		this.table = table;
		rows = table.findElements(By.tagName("tr"));
	}
	
	public List<String> getEntireRow(int index){
		ArrayList<String> rowData = new ArrayList<>();
		WebElement row = rows.get(index);

		for (WebElement ele: row.findElements(By.tagName("td"))){
			rowData.add(ele.getText());
		}
		
		return rowData;
	}	
	
	public String getData(int rowIndex, int columnIndex){
		return rows.get(rowIndex).findElements(By.tagName("td"))
				.get(columnIndex).getText();
	}
	
	public int getRowSize(){
		return rows.size();
	}
	
	public int getColumnSize(int rowIndex){
		return rows.get(rowIndex).findElements(By.tagName("td")).size();
	}
	
	public List<String> getEntireColumn (int index){
		return getEntireColumn(index, 0);
	}
	
	public List<String> getEntireColumn(int colIndex, int startFromRowNum){
		ArrayList<String> colData = new ArrayList<>();

		for (int rowIndex=startFromRowNum; rowIndex<getRowSize();rowIndex++){
			colData.add(rows.get(rowIndex).findElements(By.tagName("td"))
					.get(colIndex).getText());
		}
		
		return colData;
	}
	
	public void printTable(){
		
		int i=0;
		
		System.out.println("row size: "+rows.size());
		
		for (WebElement row: rows){
			int j=0;
			for (WebElement col: row.findElements(By.tagName("td"))){
				System.out.print("["+i+"],["+j+"]: "+col.getText());
				j++;
			}
			i++;
			System.out.println();
		}
	}
}
