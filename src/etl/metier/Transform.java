package etl.metier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Transform {

	protected List<String[]> records;
	protected List<String[]> currentResult;
	protected String operations;

	public Transform(List<String[]> records) {
		this.records = records;
		this.currentResult = records;
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

	public List<String[]> where(Map<Integer, String> conditions) {

		List<String[]> result = new ArrayList<>();

		int recNum = 0;

		for (String[] rec : records) {
			int test = 0;
			Set<Integer> keys = conditions.keySet();
			for (int k : keys) {
				if (!rec[k].toLowerCase().trim().equals(conditions.get(k).toLowerCase().trim())) {
					test = 1;
				}
			}
			if (test == 0) result.add(currentResult.get(recNum));
			recNum++;
		}

		this.currentResult = result;

		return result;

	}

	public Map<String[], List<String>> groupSelect(int aggcol, int... cols) {

		Map<String[], List<String>> result = new HashMap<>();

		for (String[] rec : currentResult) {
			String[] s = new String[cols.length];
			for (int i = 0; i < cols.length; i++)
				s[i] = rec[cols[i]];

			int index = containsRec(result, s);
			if (index == -1) {
				List<String> arr = new ArrayList<>();
				arr.add(rec[aggcol]);
				result.put(s, arr);
			} else {
				Set<String[]> keys = result.keySet();
				int j = 0;
				for (String[] k : keys) {
					if (j == index) {
						List<String> arr = result.get(k);
						arr.add(rec[aggcol]);
						result.put(s, arr);
						break;
					}
					j++;
				}

			}
		}

		// this.currentResult = result;

		return result;

	}

	private int containsRec(Map<String[], List<String>> map, String[] rec) {
		Set<String[]> keys = map.keySet();
		int index = 0;
		for (String[] k : keys) {
			int test = 0;
			for (int i = 0; i < k.length; i++) {
				if (!k[i].toLowerCase().trim().equals(rec[i].toLowerCase().trim())) test = 1;
			}
			if (test == 0)
				return index;
			index++;
		}
		return -1;
	}

	public List<String[]> groupBy(String func, int aggcol, int... cols) {

		Transform temp = new Transform(currentResult);

		Map<String[], List<String>> result = temp.groupSelect(aggcol, cols);
		List<String[]> result1 = new ArrayList<>();

		Set<String[]> keys = result.keySet(); 
		
		if(func.equals("MIN")) {
			for(String[] k : keys) {
				String[] newK = new String[k.length + 1];
				int i;
				for(i=0;i<k.length;i++) newK[i] = k[i];
				newK[i] = min(result.get(k));
				if(!listContains(result1, newK)) result1.add(newK);
			}
		}else if(func.equals("MAX")) {
			for(String[] k : keys) {
				String[] newK = new String[k.length + 1];
				int i;
				for(i=0;i<k.length;i++) newK[i] = k[i];
				newK[i] = max(result.get(k));
				if(!listContains(result1, newK)) result1.add(newK);
			}
		}else if(func.equals("FIRST")) {
			for(String[] k : keys) {
				String[] newK = new String[k.length + 1];
				int i;
				for(i=0;i<k.length;i++) newK[i] = k[i];
				newK[i] = first(result.get(k));
				if(!listContains(result1, newK)) result1.add(newK);
			}
		}else if(func.equals("LAST")) {
			for(String[] k : keys) {
				String[] newK = new String[k.length + 1];
				int i;
				for(i=0;i<k.length;i++) newK[i] = k[i];
				newK[i] = last(result.get(k));
				if(!listContains(result1, newK)) result1.add(newK);
			}
		}else if(func.equals("AVG")) {
			for(String[] k : keys) {
				
				String average = avg(result.get(k));
				if(average != null) {
					String[] newK = new String[k.length + 1];
					int i;
					for(i=0;i<k.length;i++) newK[i] = k[i];
					newK[i] = average;
					if(!listContains(result1, newK)) result1.add(newK);
				}
				else System.out.println("Can't calculate average of Strings");
			}
		}

		return result1;

	}

	private String min(List<String> records) {
		
		Collections.sort(records);
		
		return records.get(0);
	}
	
	private String max(List<String> records) {
		
		Collections.sort(records);
		
		return records.get(records.size() - 1);
	}

	private String first(List<String> records) {
		return records.get(0);
	}
	
	private String last(List<String> records) {
		return records.get(records.size() - 1);
	}

	private String avg(List<String> records) {
		
		
		if(!isNum(records.get(0))) return null;
		
		double sum = 0;
		for(String s : records) {
			sum += Double.parseDouble(s);
		}
		
		double average = sum / records.size();
		
		return String.valueOf(average);
		
	}
	
	private boolean isNum(String strNum) {
	    boolean ret = true;
	    try {

	        Double.parseDouble(strNum);

	    }catch (Exception e) {
	        ret = false;
	    }
	    return ret;
	}
	
	private boolean listContains(List<String[]> keys, String[] key)
	{
		for(String[] k : keys) {
			int test = 0;
			for(int i=0;i<k.length;i++) {
				if(!k[i].toLowerCase().trim().equals(key[i].toLowerCase().trim())) {
					test = 1; break;
				}
			}
			if(test == 0) return true;
		}
		return false;
	}

	public List<String[]> getRecords() {
		return records;
	}

	public void setRecords(List<String[]> records) {
		this.records = records;
	}

	public List<String[]> getCurrentResult() {
		return currentResult;
	}

	public void setCurrentResult(List<String[]> currentResult) {
		this.currentResult = currentResult;
	}
	
	
}
