package etl.transformation;

import etl.dataset.Dataset;

public abstract class ITransformation {
	public abstract int getId();
	public abstract void setTransformation(ITransformation tr);
	public abstract ITransformation getTransformation();
	public abstract void setData(Dataset data);
}
