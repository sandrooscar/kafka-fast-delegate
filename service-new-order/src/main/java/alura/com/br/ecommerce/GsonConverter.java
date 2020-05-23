package alura.com.br.ecommerce;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonConverter<T> {
	private final Gson gson = new GsonBuilder().create();
	
	public T deserialize(String typeName, byte[] value) {
		Class<T> type;
		try {
			type = (Class<T>)Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Type for deserialization does nor exists in the classpath");
		}		
		return this.gson.fromJson(new String(value), type);
	}
	

}
