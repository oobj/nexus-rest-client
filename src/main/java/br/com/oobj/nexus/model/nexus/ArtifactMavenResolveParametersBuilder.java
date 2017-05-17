package br.com.oobj.nexus.model.nexus;

import br.com.oobj.nexus.i18n.I18nMessageBuilder;
import br.com.oobj.nexus.i18n.I18nMessages;
import br.com.oobj.nexus.model.maven.Classifier;
import br.com.oobj.nexus.model.maven.Packaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A builder of {@link ArtifactMavenResolveParameters} instances.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 */
@Component
public class ArtifactMavenResolveParametersBuilder {

    @Autowired
    private I18nMessageBuilder i18nMessageBuilder;

    private String groupId;
    private String artifactId;
    private String version;
    private String repository;
    private Packaging packaging;
    private Classifier classifier;
    private Extension extension;

    /**
     * @param groupId the artifact groupId(mandatory)
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder groupId(String groupId) {
        this.groupId = groupId;
        return this;
    }

    /**
     *
     * @param artifactId the artifactId (mandatory)
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder artifactId(String artifactId) {
        this.artifactId = artifactId;
        return this;
    }

    /**
     *
     * @param version the artifact version (mandatory)
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder version(String version) {
        this.version = version;
        return this;
    }

    /**
     *
     * @param repository the artifact repository (mandatory)
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder repository(String repository) {
        this.repository = repository;
        return this;
    }

    /**
     *
     * @param packaging
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder packaging(Packaging packaging) {
        this.packaging = packaging;
        return this;
    }

    /**
     *
     * @param classifier
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder classifier(Classifier classifier) {
        this.classifier = classifier;
        return this;
    }

    /**
     *
     * @param extension
     * @return the builder itself
     */
    public ArtifactMavenResolveParametersBuilder extension(Extension extension) {
        this.extension = extension;
        return this;
    }

    /**
     * Build the {@link ArtifactMavenResolveParameters}.
     *
     * @return the {@link ArtifactMavenResolveParameters} built.
     */
    public ArtifactMavenResolveParameters build() {
        Assert.hasText(groupId, i18nMessageBuilder.getMessage(I18nMessages.GROUP_ID_MUST_NOT_BE_NULL_OR_EMPTY));
        Assert.hasText(artifactId, i18nMessageBuilder.getMessage(I18nMessages.ARTIFACT_ID_MUST_NOT_BE_NULL_OR_EMPTY));
        Assert.hasText(version, i18nMessageBuilder.getMessage(I18nMessages.VERSION_MUST_NOT_BE_NULL_OR_EMPTY));
        Assert.hasText(repository, i18nMessageBuilder.getMessage(I18nMessages.REPOSITORY_MUST_NOT_BE_NULL_OR_EMPTY));

        ArtifactMavenResolveParameters parameters = new ArtifactMavenResolveParameters(groupId, artifactId, version, repository);
        parameters.setPackaging(packaging);
        parameters.setClassifier(classifier);
        parameters.setExtension(extension);

        return parameters;
    }
}
