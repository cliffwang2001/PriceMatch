import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

import JSonHandling.ReadJSonFile.JSonObjectProcessor;




public class ProductContainer implements JSonObjectProcessor{

	protected Map<String, ProductFamily> manufFamilysMap = new HashMap<String, ProductFamily>();  
	protected UnmatchedStatistics unmathcedStatisObject;
	
	public void addProduct(Product product) {
		String manufacturer = product.getManufacturer();
		ProductFamily productFamily = manufFamilysMap.get(manufacturer);
		if(productFamily == null) {
			productFamily = new ProductFamily();
			manufFamilysMap.put(manufacturer, productFamily);
		}
		productFamily.addProduct(product);
		
		if(unmathcedStatisObject != null )
			unmathcedStatisObject.addProduct(product);
	}
	
	public Product getMatchedProduct(Price price) {
		String manufacturer = price.getManufacturer();
		ProductFamily productFamily = manufFamilysMap.get(manufacturer);
		if(productFamily == null) {
			manufacturer = manufacturer.split("\\s")[0];
			productFamily = manufFamilysMap.get(manufacturer);
			if(productFamily == null)
				return null;
		}
		return productFamily.getMatchedProduct(price);
		
	}
	
	protected static class ProductFamily {
		protected List<Product> noFamilyProductList = new ArrayList<Product>(); 
		protected Map<String, List<Product>>  familyProductsMap = new HashMap<String, List<Product>>(); 
		
		public void addProduct(Product product) {
			String family = product.getFamily();
			if(StringUtils.isEmpty(family)) {
				noFamilyProductList.add(product);
			}else {
				List<Product> productList = familyProductsMap.get(family);
				if(productList == null) {
					productList = new ArrayList<Product>();
					familyProductsMap.put(family, productList);
				}
				productList.add(product);
			}
		}
		
		public Product getMatchedProduct(Price price) {
			Product product = null;
			String title = price.getTitle();
			for(Entry<String, List<Product>> entry : familyProductsMap.entrySet()) {
				String family = entry.getKey();
				if(title.contains(family)) {
					List<Product> productList = entry.getValue();
					product = searchModel(title, productList);
				}
			}
			if(product == null) {
				product = searchModel(title, noFamilyProductList);
			}
			return product;
		}

		private Product searchModel(String title, List<Product> productList) {
			for(Product product : productList) {
				String model = product.getModel();
				if(title.matches(".*\\b" + model + "\\b.*"))
					return product;
				else if(SimiliarStringSearch.match(title, model)) 
					return product;
					
			}
			return null;
		}
	}

	public void processJSonObject(Object jsonObject) {
		Product product = (Product)jsonObject;
		addProduct(product);
		
	}

	public UnmatchedStatistics getUnmathcedStatisObject() {
		return unmathcedStatisObject;
	}

	public void setUnmathcedStatisObject(UnmatchedStatistics unmathcedStatisObject) {
		this.unmathcedStatisObject = unmathcedStatisObject;
	}
	
	
}
