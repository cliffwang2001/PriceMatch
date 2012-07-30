import JSonHandling.ReadJSonFile.JSonObjectProcessor;


public class PriceMatcher implements JSonObjectProcessor {

	protected ProductContainer productContainer;
	protected PriceContainer productPricesContainer = new PriceContainer();
	protected UnmatchedStatistics unmathcedStatisObject;
	
	public PriceMatcher(ProductContainer productContainer) {
		this.productContainer = productContainer;
	}

	public void processJSonObject(Object jsonObject) {
		Price price = (Price)jsonObject;
		Product matchedProduct = productContainer.getMatchedProduct(price);
		if(matchedProduct != null) {
			productPricesContainer.addPriceListing(matchedProduct.getProduct_name(), price);
		}
		
		if(unmathcedStatisObject != null) {
			if(matchedProduct != null) {
				unmathcedStatisObject.productMathced(matchedProduct);
			}else {
				unmathcedStatisObject.addUnmatchedPrice(price);
			}
		}
	}

	public PriceContainer getProductPricesContainer() {
		return productPricesContainer;
	}

	public UnmatchedStatistics getUnmathcedStatisObject() {
		return unmathcedStatisObject;
	}

	public void setUnmathcedStatisObject(UnmatchedStatistics unmathcedStatisObject) {
		this.unmathcedStatisObject = unmathcedStatisObject;
	}

	
}
