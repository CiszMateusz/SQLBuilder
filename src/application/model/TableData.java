package application.model;

import java.util.ArrayList;
import java.util.List;

public class TableData {
	
	private String tableName;
	private List<Pair> params = new ArrayList<Pair>();
	
	public TableData(String tableName){
		this.tableName = tableName;
	}

	public List<Pair> getParams(){
		return params;
	}
	
	public String getTableName() {
		return tableName;
	}
}
