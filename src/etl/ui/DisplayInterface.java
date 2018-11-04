package etl.ui;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DisplayInterface extends JFrame{
	
	public DisplayInterface(List<String[]> data) {
		
		String[][] dataArray = new String[data.size()][1];
		dataArray = data.toArray(dataArray);
		
		String[] columnNames = new String[data.get(0).length];
		for(int i=0;i<columnNames.length;i++) {
			columnNames[i] = "Column "+ i;
		}
		
		JTable result = new JTable(dataArray, columnNames);
		JScrollPane sp = new JScrollPane(result); 
		
		this.add(sp);
		
		this.setSize(800,600);
		this.setTitle("Home");
		this.setVisible(true);
		
	}

}
