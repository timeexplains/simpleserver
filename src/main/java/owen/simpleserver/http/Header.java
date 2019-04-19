package owen.simpleserver.http;

import java.util.HashMap;
import java.util.Map;

public class Header {
	private Map<String,String> map = new HashMap<String,String>();
	
	public Header() {
		map = new HashMap<String,String>();
	}
	
	
	public void addEntry(String key,String value) {
		map.put(key, value);
	}

}
