import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class PriceContainer {

	protected Map<String, ProductPriceListing> productPricesMap = new HashMap<String, ProductPriceListing>();
	
	public void addPriceListing(String productName, Price price) {
		ProductPriceListing productPirceListing = productPricesMap.get(productName);
		if(productPirceListing == null) {
			productPirceListing = new ProductPriceListing(productName);
			productPricesMap.put(productName, productPirceListing);
		}
		productPirceListing.addPrice(price);
	}
	
	public Collection<ProductPriceListing> getProductPriceListings() {
		return productPricesMap.values();
	}
}
