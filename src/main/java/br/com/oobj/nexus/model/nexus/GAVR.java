package br.com.oobj.nexus.model.nexus;

import br.com.oobj.nexus.model.maven.GAV;

/**
 * Represents the following set: a {@code groupId}, an {@code artifactId}, a {@code version} and a {@code repository}
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 */
public class GAVR extends GAV {

    /**
     *
     * @param groupId o groupId do artefato (obrigatório)
     * @param artifactId o artifactId do artefato (obrigatório)
     * @param version a version do artefato (obrigatório)
     * @param repository o repository que o artefato se encontra (obrigatório)
     */
    public GAVR(String groupId, String artifactId, String version, String repository) {
        setGroupdId(groupId);
        setArtifactId(artifactId);
        setVersion(version);
        this.repository = repository;
    }

    private String repository;

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        if (repository != null) {
            this.repository = repository;
        }
    }
}
