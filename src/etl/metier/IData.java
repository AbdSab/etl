package etl.metier;

import java.util.List;

public abstract class IData {
	
	protected int colNum;
	protected int rowNum;
	protected List<String[]> records;
	
	public int getColNum() {
		return colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public List<String[]> getRecords() {
		return records;
	}

	public void setRecords(List<String[]> records) {
		this.records = records;
	}
	
	public abstract void importData(String path);

}
