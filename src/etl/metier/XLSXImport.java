package etl.metier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class XLSXImport{
/*
	@Override
	public void importData(String path) {

		records = new ArrayList<>();

		File excelFile = new File(path);
		FileInputStream fis;
		try {
			fis = new FileInputStream(excelFile);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			workbook.setMissingCellPolicy(MissingCellPolicy.CREATE_NULL_AS_BLANK);


			// we iterate on rows
			Iterator<Row> rowIt = sheet.iterator();

			while (rowIt.hasNext()) {
				Row row = rowIt.next();
				List<String> info = new ArrayList<>();
				for (int i = 0; i < row.getLastCellNum(); i++) {
					Cell c = row.getCell(i);
					info.add(c.toString());
				}
				String[] infoArr = new String[info.size()];
				infoArr = info.toArray(infoArr);
				records.add(infoArr);
			}

			workbook.close();
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
*/
}
