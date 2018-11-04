package etl.metier;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import au.com.bytecode.opencsv.CSVReader;

public class CSVImport extends IData{

	@Override
	public void importData(String path) {
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(path));
			CSVReader csvReader = new CSVReader(reader);
			records = csvReader.readAll();
			rowNum = records.size();
			colNum = records.get(0).length;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	


}
