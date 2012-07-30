import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import JSonHandling.WriteJSonFile;


public class DefaultUnmatchedStatistics implements UnmatchedStatistics {

	protected Set<Product> unmatchedProducts = new HashSet<Product>();
	protected List<Price> unmatchedPrices = new ArrayList<Price>();
	protected String umatcedProductsFile;
	protected String umatcedPricesFile;
	
	
	public DefaultUnmatchedStatistics(String umatcedProductsFile,
			String umatcedPricesFile) {
		this.umatcedProductsFile = umatcedProductsFile;
		this.umatcedPricesFile = umatcedPricesFile;
	}

	public void addProduct(Product product) {
		unmatchedProducts.add(product);

	}

	public void productMathced(Product product) {
		unmatchedProducts.remove(product);

	}

	public void addUnmatchedPrice(Price price) {
		unmatchedPrices.add(price);

	}

	public Collection<Product> getUnmatchedProducts() {
		return unmatchedProducts;
	}

	public Collection<Price> getUnmatchedPrices() {
		return unmatchedPrices;
	}

	public void writeUmatchedStatistics() {
		Iterator<Product> ummatchedProductsIterator = unmatchedProducts.iterator();
		WriteJSonFile unmatchedProductsWriter = new WriteJSonFile(umatcedProductsFile);
		unmatchedProductsWriter.streamJSonObject(ummatchedProductsIterator);
		
		Iterator<Price> ummatchedPricesIterator = unmatchedPrices.iterator();
		WriteJSonFile unmatchedPricesWriter = new WriteJSonFile(umatcedPricesFile);
		unmatchedPricesWriter.streamJSonObject(ummatchedPricesIterator);
	}
}
