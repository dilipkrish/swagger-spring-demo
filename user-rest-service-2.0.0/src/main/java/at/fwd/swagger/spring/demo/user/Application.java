package at.fwd.swagger.spring.demo.user;

import at.fwd.swagger.spring.demo.user.controller.UserController;
import at.fwd.swagger.spring.demo.user.system.HotReloadConfiguration;
import at.fwd.swagger.spring.demo.user.system.SpringSwaggerController;
import com.google.common.base.Predicate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.annotations.EnableSwagger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
//replaces: @ComponentScan @EnableAutoConfiguration @Configuration

/**
 * Spring Boot Root Configuration
 *  
 * @author johannes.fiala@fwd.at
 *
 */
@SpringBootApplication
@EnableSwagger
@EnableSwagger2
@ComponentScan(basePackageClasses = {
        UserController.class, HotReloadConfiguration.class, SpringSwaggerController.class
})
public class Application {
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }  

    
    @Bean
    public Docket swaggerSpringMvcPlugin() {
      return new Docket(DocumentationType.SWAGGER_2)
              //.groupName("business-api")
              .select() 
                 //Ignores controllers annotated with @CustomIgnore
                //.apis(not(withClassAnnotation(CustomIgnore.class)) //Selection by RequestHandler
                //.paths(paths()) // and by paths
                .build()
              .apiInfo(apiInfo());
              //.securitySchemes(securitySchemes())
              //.securityContext(securityContext());
    }
    

    private ApiInfo apiInfo() {
	      ApiInfo apiInfo = new ApiInfo(
	              "My Apps API Title", "Demo Swagger Spring 2 API Description", "1.0",
	              "My Apps API terms of service", "My Apps API Contact Email",
	              "My Apps API Licence Type", "My Apps API License URL"
	        );
	      return apiInfo;
	    }

    //Here is an example where we select any api that matches one of these paths
    private Predicate<String> paths() {
    	return null;
       /* return  or(regex("/business.*"),
          regex("/some.*"),
          regex("/contacts.*"),
          regex("/pet.*"),
          regex("/springsRestController.*"),
          regex("/test.*"));*/
    }
    

    
}