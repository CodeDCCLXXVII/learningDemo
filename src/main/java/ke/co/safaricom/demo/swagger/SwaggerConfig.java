/**
 * 
 */
package ke.co.safaricom.demo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author dcclxxvii
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	public static String CLIENT_ID = "dcclxxvii";
	public static String CLIENT_SECRET = "password";
	public static String AUTH_SERVER = "http://localhost:8081";
	
	@Bean
	public Docket api() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("ke.co.safaricom.demo.controller"))
				.paths(regex("/product.*"))
				.build()
				.securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()))
				.tags(new Tag("products", "Operations pertaining to products in context"))
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		
		List<VendorExtension> vendorExtensions = new ArrayList<>();
		
		ApiInfo apiInfo = new ApiInfo(
				"Spring Boot REST API",
				"Spring Boot REST API for Online Store Learning purposes",
				"1.0",
				"Terms of Service",
				new Contact("DccLxxVii", "https://github.com/CodeDCCLXXVII", "munenekuria777@gmail.com"),
				"Apache License version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0", vendorExtensions);
		
		
		return apiInfo;
		
	}
	
	@Bean
	public SecurityConfiguration security() {
		
		return SecurityConfigurationBuilder.builder()
				.clientId(CLIENT_ID)
				.clientSecret(CLIENT_SECRET)
				.scopeSeparator(" ")
				.useBasicAuthenticationWithAccessCodeGrant(true)
				.build();
	}
	
	/**
	 * Describe how API is secured
	 * @return
	 */
	private SecurityScheme securityScheme()
	{
		
		GrantType grantType = new AuthorizationCodeGrantBuilder()
				.tokenEndpoint(new TokenEndpoint(AUTH_SERVER +"/token", "oauthtoken"))
				.tokenRequestEndpoint(
						new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
				.build();
		
		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
				.grantTypes(Arrays.asList(grantType))
				.scopes(Arrays.asList(scopes()))
				.build();
		return oauth;
	}
	
	/**
	 * 
	 * @return
	 */
	private AuthorizationScope[] scopes() {
		
		AuthorizationScope[] scopes = {
				new AuthorizationScope("read", "for read operations"),
				new AuthorizationScope("write", "for writw operations"),
				new AuthorizationScope("product", "Access Product API")
		};
		
		return scopes;
		
	}
	
	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(
						Arrays.asList(new SecurityReference("spring_oauth", scopes())))
				.forPaths(PathSelectors.regex("/product.*"))
				.build();
	}
}
