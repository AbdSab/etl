package etl.transformation;

import java.util.ArrayList;
import java.util.List;

import etl.dataset.Dataset;

public class Select extends ITransformation{
	private Dataset data;
	public String operation;
	private List<String[]> records;
	private List<String[]> currentResult;
	private ITransformation transformation;
	private int id;
	public Select(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return this.id;
	}
	

	@Override
	public void setTransformation(ITransformation tr) {
		this.transformation = tr;
		
	}

	@Override
	public ITransformation getTransformation() {
		return this.transformation;
	}
	
	@Override
	public void setData(Dataset data) {
		this.data = data;
	}
	
	public List<String[]> select() {
		return records;
	}

	public List<String[]> select(int... cols) {

		List<String[]> result = new ArrayList<>();

		for (String[] rec : currentResult) {
			String[] s = new String[cols.length];
			for (int i = 0; i < cols.length; i++)
				s[i] = rec[cols[i]];
			result.add(s);
		}

		this.currentResult = result;

		return result;

	}
	
}
