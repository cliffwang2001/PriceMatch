import java.util.ArrayList;
import java.util.List;


public class ProductPriceListing {

	protected String product_name;
	protected List<Price> listings = new ArrayList<Price>();
	
	public ProductPriceListing(String product_name) {
		this.product_name = product_name;
	}
	
	public void addPrice(Price price) {
		listings.add(price);
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public List<Price> getListings() {
		return listings;
	}

	public void setListings(List<Price> listings) {
		this.listings = listings;
	}
	
	
}
