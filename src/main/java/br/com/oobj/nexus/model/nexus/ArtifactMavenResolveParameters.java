package br.com.oobj.nexus.model.nexus;

import br.com.oobj.nexus.model.maven.Classifier;
import br.com.oobj.nexus.model.maven.Packaging;

/**
 * Example of the most basic usage:
 *
 * <pre>
 *  ArtifactMavenResolveParameters amrp =
 *          new ArtifactMavenResolveParameters("com.my.company", "my-project", "1.0.0", "release");
 * </pre>
 *
 * You also can use a Builder to set packaging, classifier and/or extension information:
 *
 * <pre>
 *  ArtifactMavenResolveParameters amrp =
 *          new ArtifactMavenResolveParametersBuilder("com.my.company", "my-project","1.0.0", "release")
 *           .packaging(myPackaging)
 *           .classifier(classifier)
 *           .extension(extension)
 *           .build();
 * </pre>
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 * @see <a href="http://maven.oobj.com.br/nexus/nexus-restlet1x-plugin/default/docs/path__artifact_maven_resolve.html">Nexus Core Rest API - Artifact Maven Resolve Endpoint</a>
 * @see ArtifactMavenResolveParametersBuilder
 */
public class ArtifactMavenResolveParameters {

    private GAVR gavr;
    private Packaging packaging;
    private Classifier classifier;
    private Extension extension;

    private ArtifactMavenResolveParameters() {
        super();
    }

    /**
     * Default package-protected constructor.
     *
     * @param groupId the artifact groupId(mandatory)
     * @param artifactId the artifactId (mandatory)
     * @param version the artifcat version (mandatory)
     * @param repository the artifact repository (mandatory)
     */
    ArtifactMavenResolveParameters(String groupId, String artifactId, String version, String repository) {
        this.gavr = new GAVR(groupId, artifactId, version, repository);
    }

    public String getGroupdId() {
        return gavr.getGroupdId();
    }

    public final void setGroupdId(String groupdId) {
        gavr.setGroupdId(groupdId);
    }

    public String getArtifactId() {
        return gavr.getArtifactId();
    }

    public final void setArtifactId(String artifactId) {
        gavr.setArtifactId(artifactId);
    }

    public String getVersion() {
        return gavr.getVersion();
    }

    public final void setVersion(String version) {
        gavr.setVersion(version);
    }

    public String getRepository() {
        return gavr.getRepository();
    }

    public void setRepository(String repository) {
        gavr.setRepository(repository);
    }

    public Packaging getPackaging() {
        return packaging;
    }

    public void setPackaging(Packaging packaging) {
        this.packaging = packaging;
    }

    public Classifier getClassifier() {
        return classifier;
    }

    public void setClassifier(Classifier classifier) {
        this.classifier = classifier;
    }

    public Extension getExtension() {
        return extension;
    }

    public void setExtension(Extension extension) {
        this.extension = extension;
    }

}
