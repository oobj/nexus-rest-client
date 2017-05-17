package br.com.oobj.nexus.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Document-me!
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 */
@Configuration
@PropertySource("classpath:nexus.properties")
public class NexusConfig {

    @Value("${nexus.url}")
    private String url;

    @Value("${nexus.username}")
    private String nexusUsername;

    @Value("${nexus.password}")
    private String nexusPassword;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNexusUsername() {
        return nexusUsername;
    }

    public void setNexusUsername(String nexusUsername) {
        this.nexusUsername = nexusUsername;
    }

    public String getNexusPassword() {
        return nexusPassword;
    }

    public void setNexusPassword(String nexusPassword) {
        this.nexusPassword = nexusPassword;
    }
}
