package Interfaces;

import org.json.JSONArray;
import org.json.JSONObject;

public interface I_Json {
    public JSONObject toJson();

	public default JSONArray toJsonArray() {
		return new JSONArray().put(toJson());
	}
}
