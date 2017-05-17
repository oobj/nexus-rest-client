package br.com.oobj.nexus.service;

/**
 * Nexus service type enumeration.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 */
public enum NexusServiceType {

    MAVEN_ARTIFACT_CONTENT("/service/local/artifact/maven/content"),
    MAVEN_ARTIFACT_DETAILS("/service/local/artifact/maven/resolve"),
    MAVEN_ARTIFACT_POM_MODEL("/service/local/artifact/maven/"),
    /**
     * The first argument is the groupId (separated by '/') and the second argument is the artifactId
     */
    MAVEN_ARTIFACT_META_MODEL("/content/groups/public/%s/%s/maven-metadata.xml");

    private String url;

    NexusServiceType(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
