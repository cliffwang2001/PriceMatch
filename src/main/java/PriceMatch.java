import java.util.Collection;
import java.util.Iterator;

import JSonHandling.ReadJSonFile;
import JSonHandling.WriteJSonFile;


public class PriceMatch {

	protected static final String OUTPUT_FILE = "product_listings.txt";
	
	public static void main(String[] args) {
		if(args.length != 2) {
			System.out.println("Usage: PriceMatch <productFile> <priceListingFile>");
			return;
		}
		
		String productFile = args[0];
		String priceFile = args[1];
		
		ReadJSonFile<Product> productReader = new ReadJSonFile<Product>(productFile, Product.class);
		ProductContainer productContainer = new ProductContainer();
		
		productReader.readFile(productContainer);
		
		ReadJSonFile<Price> priceReader = new ReadJSonFile<Price>(priceFile, Price.class);
		PriceMatcher priceProcessor = new PriceMatcher(productContainer);
		priceReader.readFile(priceProcessor);
		
		PriceContainer pricesContainer = priceProcessor.getProductPricesContainer();
		Collection<ProductPriceListing> priceListings = pricesContainer.getProductPriceListings();
		
		WriteJSonFile listingsWriter = new WriteJSonFile(OUTPUT_FILE);
		Iterator<ProductPriceListing>  listingsIterator = priceListings.iterator();
		listingsWriter.streamJSonObject(listingsIterator );
		
	}

}
