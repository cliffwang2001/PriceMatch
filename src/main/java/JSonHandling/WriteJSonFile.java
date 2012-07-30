package JSonHandling;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

import org.codehaus.jackson.map.ObjectMapper;

public class WriteJSonFile {
	protected String jsonFileName;
	Writer output;
	ObjectMapper mapper = new ObjectMapper();

	public WriteJSonFile(String jsonFileName) {
		this.jsonFileName = jsonFileName;
	}
	
	public void openFile() {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(jsonFileName);
			OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8"); 
			output = new BufferedWriter(out);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		
	}
	
	public void writeJSonObject(Object jsonObject) {
		String JSonStr;
		try {
			JSonStr = mapper.writeValueAsString(jsonObject);
			output.write(JSonStr);
		} catch (Exception e) {
			try {
				output.close();
			} catch (IOException ioe) {
//				throw new RuntimeException(ioe);
			}
			throw new RuntimeException(e);
		}
		
	}
	
	public void writeJSonObjectWithNewLine(Object jsonObject) {
		String JSonStr;
		try {
			JSonStr = mapper.writeValueAsString(jsonObject);
			output.write(JSonStr + "\n");
			
		} catch (Exception e) {
			try {
				output.close();
			} catch (IOException ioe) {
//				throw new RuntimeException(ioe);
			}
			throw new RuntimeException(e);
		}
	}
	
	public void closeFile() {
		if(output != null) {
			try {
				output.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void streamJSonObject(Iterator jsonObjIterator) {
		openFile();
		try {
			while(jsonObjIterator.hasNext()) {
				Object jsonObject = jsonObjIterator.next();
				String JSonStr = mapper.writeValueAsString(jsonObject);
				output.write(JSonStr + "\n");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			closeFile();
		}
	}
	
	public void streamJSonObject(JSonObjectFetcher fetcher) {
		openFile();
		try {
			Object jsonObject = null;
			while((jsonObject = fetcher.nextJSonObject()) != null) {
				String JSonStr = mapper.writeValueAsString(jsonObject);
				output.write(JSonStr + "\n");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			closeFile();
		}
		
		
	}
	
	public static interface JSonObjectFetcher {
		public Object nextJSonObject();
	}
}
