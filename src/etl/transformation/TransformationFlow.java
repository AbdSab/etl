package etl.transformation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import etl.dataset.Dataset;

public class TransformationFlow {
	public static List<ITransformation> transformations  = new ArrayList<>();
	public static List<Dataset> datasets = new ArrayList<>();
	
	public static Dataset getByDataId(int id) {
		for(Dataset t : datasets) {
			if(t.getId() == id) return t;
		}
		return null;
	}
	
	public static ITransformation getById(int id) {
		for(ITransformation t : transformations) {
			if(t.getId() == id) return t;
		}
		return null;
	}
}
