package owen.simpleserver.http;

import java.util.HashMap;
import java.util.Map;

public class Body {
	private Map<String,Object>  paramMap = new HashMap<String,Object>();

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
	
	
	public Body() {
		
		paramMap = new HashMap<String,Object>();
		
	}
	
	public void addEntry(String key,Object value) {
		
		paramMap.put(key, value);
		
	}
	
	
	
	
	
}
