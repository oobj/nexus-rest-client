package br.com.oobj.nexus.model.maven;

/**
 * Representa um conjunto de {@code groupId}, {@code artifactId} e {@code version}
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 * @see <a href="https://maven.apache.org/pom.html#Maven_Coordinates">Maven Coordinates</a>
 */
public class GAV {

    private String groupdId;
    private String artifactId;
    private String version;

    public String getGroupdId() {
        return groupdId;
    }

    public final void setGroupdId(String groupdId) {
        this.groupdId = groupdId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public final void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getVersion() {
        return version;
    }

    public final void setVersion(String version) {
        this.version = version;
    }
}
