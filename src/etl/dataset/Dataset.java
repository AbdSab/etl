package etl.dataset;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Dataset {
	
	private List<String[]> data;
	private int rowNum, colNum;
	private String path;
	private int id;
	
	public Dataset(int id, String path) {
		this.id = id;
		this.path = path;
		this.pathToData();
	}
	
	public List<String[]> getData() {
		return data;
	}

	public void setData(List<String[]> data) {
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	private void pathToData() {
		/*Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(path));
			CSVReader csvReader = new CSVReader(reader);
			data = csvReader.readAll();
			rowNum = data.size();
			colNum = data.get(0).length;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	};

	
}
