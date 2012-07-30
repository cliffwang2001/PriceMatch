package JSonHandling;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.codehaus.jackson.map.ObjectMapper;


public class ReadJSonFile<T> {

	protected String jsonFileName;
	protected Class<T> javaType;
	
	public ReadJSonFile(String jsonFileName, Class<T> javaType) {
		this.jsonFileName = jsonFileName;
		this.javaType = javaType;
	}
	
	public void readFile(JSonObjectProcessor processor) {
		BufferedReader br = null;
		try {
			FileInputStream fis = new FileInputStream(jsonFileName);
			InputStreamReader in = new InputStreamReader(fis, "UTF-8"); 
			br = new BufferedReader(in);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				T jsonObject = mapper.readValue(strLine, javaType);
				processor.processJSonObject(jsonObject);
			}
//			in.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
//					throw new RuntimeException(e);
				}
			}
	     }
	}
	
	public static interface JSonObjectProcessor {
		void processJSonObject(Object jsonObject);
	}
}
