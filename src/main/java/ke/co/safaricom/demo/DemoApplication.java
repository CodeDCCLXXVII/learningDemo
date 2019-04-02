package ke.co.safaricom.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
	
	private static final Logger logger =
			LoggerFactory.getLogger(DemoApplication.class);
	

	public static void main(String[] args) {
		
		logger.info("this is info mesage");
		logger.warn("this is a warm mesage");
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@RequestMapping(value="/")
	public String getHello() {
		return "Hello";
	}

}
