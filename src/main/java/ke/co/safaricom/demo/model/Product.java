/**
 * 
 */
package ke.co.safaricom.demo.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author dcclxxvii
 *
 */
public class Product {
	
	@ApiModelProperty(notes = "Product id")
	private String id;
	@ApiModelProperty(notes = "Product name")
	private String name;
	
	public Product(String id, String name) {
		
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
