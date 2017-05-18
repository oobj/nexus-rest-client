package br.com.oobj.nexus.service.impl;

import br.com.oobj.nexus.config.NexusConfig;
import br.com.oobj.nexus.exception.ArtifactNotFoundException;
import br.com.oobj.nexus.exception.AuthenticationException;
import br.com.oobj.nexus.i18n.I18nMessageBuilder;
import br.com.oobj.nexus.i18n.I18nMessages;
import br.com.oobj.nexus.model.nexus.ArtifactMavenResolveParameters;
import br.com.oobj.nexus.model.nexus.ArtifactMavenResolveParametersBuilder;
import br.com.oobj.nexus.model.nexus.GAVR;
import br.com.oobj.nexus.service.NexusService;
import br.com.oobj.nexus.service.NexusServiceType;
import org.apache.maven.MavenMetadata;
import org.glassfish.jersey.internal.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.nexus.ArtifactResolveResource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import static br.com.oobj.nexus.i18n.I18nMessages.ARTIFACT_ID_MUST_NOT_BE_NULL_OR_EMPTY;
import static br.com.oobj.nexus.i18n.I18nMessages.GAVR_MUST_NOT_BE_NULL;
import static br.com.oobj.nexus.i18n.I18nMessages.GROUP_ID_MUST_NOT_BE_NULL_OR_EMPTY;
import static br.com.oobj.nexus.i18n.I18nMessages.MAVEN_METADATA_XML_PARSE_FAILED;

/**
 * Default implementation of {@link NexusService}.
 *
 * <p>
 * It access Nexus service through HTTP Requests on his Rest API.
 * </p>
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 */
@Service
public class NexusServiceImpl implements NexusService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NexusServiceImpl.class);

    private final NexusConfig nexusConfig;
    private final RestTemplate restTemplate;
    private final Jaxb2Marshaller marshaller;
    private final I18nMessageBuilder i18nMessageBuilder;
    private final ArtifactMavenResolveParametersBuilder artifactMavenResolveParametersBuilder;

    /**
     * Default constructor.
     *
     * @param nexusConfig Nexus Config
     * @param restTemplate Spring REST Template
     * @param marshaller JSON Marshaller
     * @param i18nMessageBuilder I18N Message Builder
     */
    @Inject
    public NexusServiceImpl(NexusConfig nexusConfig, RestTemplate restTemplate,
                            Jaxb2Marshaller marshaller, I18nMessageBuilder i18nMessageBuilder,
                            ArtifactMavenResolveParametersBuilder artifactMavenResolveParametersBuilder ) {
        Assert.notNull(nexusConfig, "Nexus Config must not be null");
        Assert.notNull(restTemplate, "RestTemplate must not be null");
        Assert.notNull(i18nMessageBuilder, "MessageBuilder must not be null");
        Assert.notNull(artifactMavenResolveParametersBuilder, "ArtifactMavenResolveParametersBuilder must not be null");

        this.nexusConfig = nexusConfig;
        this.restTemplate = restTemplate;
        this.marshaller = marshaller;
        this.i18nMessageBuilder = i18nMessageBuilder;
        this.artifactMavenResolveParametersBuilder = artifactMavenResolveParametersBuilder;

        String basicAuthenticationBase64 = Base64.encodeAsString(
                nexusConfig.getNexusUsername() + ":" + nexusConfig.getNexusPassword());

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new HeaderRequestInterceptor("Authorization", "Basic " + basicAuthenticationBase64));
        this.restTemplate.setInterceptors(interceptors);
    }

    @Override
    public ResponseEntity<byte[]> getMavenArtifactContent(ArtifactMavenResolveParameters parameters)
            throws AuthenticationException, ArtifactNotFoundException {
        String url = urlDownload(parameters);

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, byte[].class);

        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ArtifactNotFoundException(i18nMessageBuilder.getMessage(I18nMessages.ARTIFACT_NOT_FOUND,
                    parameters.getGroupdId(), parameters.getArtifactId()));
        }

        if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
            throw new AuthenticationException(i18nMessageBuilder.getMessage(I18nMessages.AUTHENTICATION_PROBLEM,
                    nexusConfig.getNexusUsername()));
        }

        return response;
    }

    @Async
    @Override
    public Future<Response> getMavenArtifactContentAsync(ArtifactMavenResolveParameters parameters)
            throws AuthenticationException, ArtifactNotFoundException {
        String url = urlDownload(parameters);

        Map<String, Object> map = urlParameters(parameters);

        Response results = restTemplate.getForObject(url, Response.class, map);
        return new AsyncResult<>(results);
    }

    private String urlDownload(ArtifactMavenResolveParameters parameters) {
        return buildUrl(NexusServiceType.MAVEN_ARTIFACT_CONTENT, parameters);
    }

    @Override
    public String queryMavenArtifactPomModel(GAVR gavr) {
        Assert.notNull(gavr, i18nMessageBuilder.getMessage(GAVR_MUST_NOT_BE_NULL));

        String url = buildUrl(NexusServiceType.MAVEN_ARTIFACT_POM_MODEL, gavr);

        return restTemplate.getForObject(url, String.class);
    }

    @Override
    public MavenMetadata queryMavenArtifactMetaData(String groupId, String artifactId)
            throws AuthenticationException, ArtifactNotFoundException {
        Assert.hasText(groupId, i18nMessageBuilder.getMessage(GROUP_ID_MUST_NOT_BE_NULL_OR_EMPTY));
        Assert.hasText(artifactId, i18nMessageBuilder.getMessage(ARTIFACT_ID_MUST_NOT_BE_NULL_OR_EMPTY));

        String groupIdParsed = groupId.replaceAll("\\.", "/");

        String url = nexusConfig.getUrl() +
                String.format(NexusServiceType.MAVEN_ARTIFACT_META_MODEL.getUrl(), groupIdParsed, artifactId);

        String xmlMavenMetadata = restTemplate.getForObject(url, String.class);

        MavenMetadata emp = null;
        try {
            Source source = new StreamSource(new StringReader(xmlMavenMetadata));
            Object obj = marshaller.unmarshal(source);

            if (obj instanceof JAXBElement<?>) {
                JAXBElement<MavenMetadata> jaxbElement = (JAXBElement<MavenMetadata>) obj;
                emp = jaxbElement.getValue();
            } else if (obj instanceof MavenMetadata) {
                emp = (MavenMetadata) obj;
            }
        } catch (XmlMappingException e) {
            String errorMessage = i18nMessageBuilder.getMessage(MAVEN_METADATA_XML_PARSE_FAILED);
            LOGGER.error(errorMessage, e);
        }

        return emp;
    }

    @Override
    public ArtifactResolveResource queryMavenArtifactDetails(ArtifactMavenResolveParameters parameters) {
        String url = buildUrl(NexusServiceType.MAVEN_ARTIFACT_DETAILS, parameters);

        Map<String, Object> map = urlParameters(parameters);

        ResponseEntity<ArtifactResolveResource> response = restTemplate.getForEntity(url, ArtifactResolveResource.class, map);

        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatusCodeValue());
        }

        ArtifactResolveResource output = response.getBody();

        LOGGER.info("Output from Nexus .... \n" + output);

        return output;
    }

    private String buildUrl(NexusServiceType nexusServiceType, GAVR gavr) {
        Assert.notNull(gavr, i18nMessageBuilder.getMessage(I18nMessages.GAVR_MUST_NOT_BE_NULL));

        final ArtifactMavenResolveParameters parameters = artifactMavenResolveParametersBuilder
                .groupId(gavr.getGroupdId())
                .artifactId(gavr.getArtifactId())
                .version(gavr.getVersion())
                .repository(gavr.getRepository())
                .build();

        return buildUrl(nexusServiceType, parameters);
    }

    private String buildUrl(NexusServiceType nexusServiceType, ArtifactMavenResolveParameters parameters) {
        final StringBuilder url = new StringBuilder(nexusConfig.getUrl());


        url.append(nexusServiceType.getUrl())
                .append("?g=").append(parameters.getGroupdId())
                .append("&a=").append(parameters.getArtifactId())
                .append("&v=").append(parameters.getVersion())
                .append("&r=").append(parameters.getRepository())
                ;

        if (parameters.getClassifier() != null) {
            url.append("&c=").append(parameters.getClassifier().getName());
        }

        if (parameters.getExtension() != null) {
            url.append("&e=").append(parameters.getExtension().getName());
        }

        LOGGER.debug("URL built: " + url);
        return url.toString();
    }

    private Map<String, Object> urlParameters(ArtifactMavenResolveParameters parameters) {
        Map<String, Object> urlParameters = new HashMap<>();

        urlParameters.put("g", parameters.getGroupdId());
        urlParameters.put("a", parameters.getArtifactId());
        urlParameters.put("v", parameters.getVersion());
        urlParameters.put("r", parameters.getRepository());

        if (parameters.getClassifier() != null) {
            urlParameters.put("c", parameters.getClassifier().getName());
        }

        if (parameters.getExtension() != null) {
            urlParameters.put("e", parameters.getExtension().getName());
        }

        return urlParameters;
    }
}
