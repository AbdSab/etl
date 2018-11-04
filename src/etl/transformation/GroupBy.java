package etl.transformation;

import etl.dataset.Dataset;

public class GroupBy extends ITransformation{
	private Dataset data;
	private ITransformation transformation;
	private String columns;
	private int id;
	public GroupBy(int id) {
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
}
