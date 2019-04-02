package ke.co.safaricom.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ke.co.safaricom.demo.model.Product;

/**
 * 
 * @author dcclxxvii
 *
 */

@RestController
@RequestMapping("/product")
@Api(value="Online store", description="Operations pertaining to products in context")
public class ProductController {

	private static Map<String, Product> productRepo = new HashMap<>();
	
	static {
		
		productRepo.put("1", new Product("1", "Honey"));
		productRepo.put("2", new Product("2", "Yoghurt"));
		
	}
	
	@ApiOperation(value="View a list of available Products", response = Product.class )
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ResponseEntity<Object> getProducts(){
		
		return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
	}

	@ApiOperation(value="Add a new product")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> createProduct(@RequestBody Product product)

	{
		productRepo.put(product.getId(), product);

		return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
	}

	@ApiOperation(value="Update a product")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {

		productRepo.remove(id);
		product.setId(id);
		productRepo.put(id, product);
		return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);

	}
	
	@ApiOperation(value = "Delete a product")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces= "application/json")
	public ResponseEntity<Object> delete(@PathVariable("id") String id){
		
		productRepo.remove(id);
		
		return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
	}

}
