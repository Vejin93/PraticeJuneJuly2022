package com.example.uploadexample;

import org.apache.coyote.http11.AbstractHttp11JsseProtocol;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UploadExampleApplication {

	@Bean
	public TomcatServletWebServerFactory tomcatEmbedder(){

		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();

		tomcat.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> {if((connector.getProtocolHandler() instanceof AbstractHttp11JsseProtocol<?>)){
		// - 1 means unlimited
			((AbstractHttp11Protocol<?>) connector.getProtocolHandler()).setMaxSwallowSize(-1);
		}
		});
		return tomcat;
	}

	public static void main(String[] args) {
		SpringApplication.run(UploadExampleApplication.class, args);
	}

}
