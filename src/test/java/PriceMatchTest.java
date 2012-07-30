import java.util.Collection;
import java.util.Iterator;


import JSonHandling.ReadJSonFile;
import JSonHandling.WriteJSonFile;


public class PriceMatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadJSonFile<Product> productReader = new ReadJSonFile<Product>("src/test/resources/products.txt", Product.class);
		DefaultUnmatchedStatistics umatchedStatis = new DefaultUnmatchedStatistics(
				"output/umatchedProducts.txt", "output/umatchedPrices.txt");
		ProductContainer productContainer = new ProductContainer();
		productContainer.setUnmathcedStatisObject(umatchedStatis);
		
		productReader.readFile(productContainer);
		
		ReadJSonFile<Price> priceReader = new ReadJSonFile<Price>("src/test/resources/listings.txt", Price.class);
		PriceMatcher priceProcessor = new PriceMatcher(productContainer);
		priceProcessor.setUnmathcedStatisObject(umatchedStatis);
		priceReader.readFile(priceProcessor);
		
		PriceContainer pricesContainer = priceProcessor.getProductPricesContainer();
		Collection<ProductPriceListing> priceListings = pricesContainer.getProductPriceListings();
		
		WriteJSonFile listingsWriter = new WriteJSonFile("output/product_listings.txt");
		Iterator<ProductPriceListing>  listingsIterator = priceListings.iterator();
		listingsWriter.streamJSonObject(listingsIterator );
		
		umatchedStatis.writeUmatchedStatistics();
		
		
		
		
		
/*		
		ObjectMapper mapper = new ObjectMapper();
		Writer output = null;
		try {	
			FileOutputStream fos = new FileOutputStream("src/main/resources/product_listings.txt");
			OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8"); 
			output = new BufferedWriter(out);
			for(ProductPriceListing listing : priceListings) {
				String listingJson = mapper.writeValueAsString( listing);
//				System.out.println(listingJson);
				output.write(listingJson + "\n");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(output != null) {
				try {
					output.close();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
*/		
	}

}
