import java.util.Collection;


public interface UnmatchedStatistics {

	public void addProduct(Product product);
	public void productMathced(Product product);
	public void addUnmatchedPrice(Price price);
	public Collection<Product> getUnmatchedProducts();
	public Collection<Price> getUnmatchedPrices();
}
