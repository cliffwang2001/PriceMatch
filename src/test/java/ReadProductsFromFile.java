import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.codehaus.jackson.map.ObjectMapper;


public class ReadProductsFromFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			FileInputStream fis = new FileInputStream("src/main/resources/products.txt");
			InputStreamReader in = new InputStreamReader(fis, "UTF-8"); 
			BufferedReader br = new BufferedReader(in);
			
			ObjectMapper mapper = new ObjectMapper();
			
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				Product product = mapper.readValue(strLine, Product.class);
//				System.out.println(product.getProduct_name());
				System.out.println (strLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
