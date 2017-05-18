package br.com.oobj.nexus.model.nexus;

import br.com.oobj.nexus.model.maven.Classifier;
import br.com.oobj.nexus.model.maven.Packaging;

/**
 * This is an "extension" of {@link GAVR}.
 * It's the information used to query at the Nexus Core Rest API - Artifact Maven Resolve Endpoint
 *
 * <p>
 * To create an instance of this class, you must use the Builder:
 * </p>
 *
 * <pre>
 *  ArtifactMavenResolveParameters amrp =
 *          new ArtifactMavenResolveParametersBuilder("com.mycompany", "my-project", "1.0.0", "release")
 *          .build();
 * </pre>
 *
 * <p>
 * You can also set packaging, classifier and/or extension information during the building process:
 * </p>
 *
 * <pre>
 *  ArtifactMavenResolveParameters amrp =
 *          new ArtifactMavenResolveParametersBuilder("com.mycompany", "my-project", "1.0.0", "release")
 *           .packaging(myPackaging)
 *           .classifier(classifier)
 *           .extension(extension)
 *           .build();
 * </pre>
 *
 * <p>
 * The {@link ArtifactMavenResolveParametersBuilder#build build()} method invocation can produce an
 * {@link IllegalArgumentException} (see Javadoc for more details).
 * </p>
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 * @see <a href="https://repository.sonatype.org/nexus-restlet1x-plugin/default/docs/path__artifact_maven_resolve.html">Nexus Core Rest API - Artifact Maven Resolve Endpoint</a>
 * @see ArtifactMavenResolveParametersBuilder
 */
public class ArtifactMavenResolveParameters {

    private GAVR gavr;
    private Packaging packaging;
    private Classifier classifier;
    private Extension extension;

    /**
     * Default package-protected constructor.
     *
     * <p>
     * All arguments are required.
     * </p>
     *
     * @param groupId the artifact groupId (mandatory)
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
