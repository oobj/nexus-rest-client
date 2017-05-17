package br.com.oobj.nexus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 10/03/2017
 */
@Configuration
@ComponentScan("br.com.oobj.nexus")
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
