import java.util.Date;

import org.codehaus.jackson.annotate.JsonProperty;


public class Product {

	protected String product_name;
	protected String manufacturer;
	protected String model;
	protected String family;
	@JsonProperty("announced-date")
	protected Date announced_date;
	
	public Product() {
		super();
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public Date getAnnounced_date() {
		return announced_date;
	}

	public void setAnnounced_date(Date announced_date) {
		this.announced_date = announced_date;
	} 
	
	
}
