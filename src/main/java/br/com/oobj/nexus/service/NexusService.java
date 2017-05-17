package br.com.oobj.nexus.service;

import br.com.oobj.nexus.exception.ArtifactNotFoundException;
import br.com.oobj.nexus.exception.AuthenticationException;
import br.com.oobj.nexus.model.nexus.ArtifactMavenResolveParameters;
import br.com.oobj.nexus.model.nexus.GAVR;
import org.apache.maven.MavenMetadata;
import org.sonatype.nexus.ArtifactResolveResource;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;
import java.util.concurrent.Future;

/**
 * Consume some Nexus services.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 */
public interface NexusService {

    /**
     * Retrieves the content of the requested artifact in a synchronous way.
     *
     * <p>
     * Depending on the artifact size, the invocation of this method can be extremely slow.
     * </p>
     *
     * @param parameters  the artifact parameters that will be retrieved
     * @return the content of the requested artifact.
     * @throws AuthenticationException throw if there is an error related to authentication problems
     * @throws ArtifactNotFoundException throw if the artifact was not found on Nexus
     * @see #getMavenArtifactContentAsync(ArtifactMavenResolveParameters)
     */
    ResponseEntity<byte[]> getMavenArtifactContent(ArtifactMavenResolveParameters parameters)
            throws AuthenticationException, ArtifactNotFoundException;

    /**
     * Retrieves the content of the requested artifact in a asynchronous way.
     *
     * @param parameters  the artifact parameters that will be retrieved
     * @return the content of the requested artifact.
     * @throws AuthenticationException throw if there is an error related to authentication problems
     * @throws ArtifactNotFoundException throw if the artifact was not found on Nexus
     */
    Future<Response> getMavenArtifactContentAsync(ArtifactMavenResolveParameters parameters)
            throws AuthenticationException, ArtifactNotFoundException;

    /**
     * Returns the POM Model in a serialized form.
     *
     * <p>
     * <b>CAUTION: the POM Model returned by Nexus is NOT consumable by Maven.</b>
     * </p>
     *
     * @param gavr the artifact groupdId, artifactId, version and repository
     * @return the POM Model
     * @throws AuthenticationException throw if there is an error related to authentication problems
     * @throws ArtifactNotFoundException throw if the artifact was not found on Nexus
     */
    String queryMavenArtifactPomModel(GAVR gavr) throws AuthenticationException, ArtifactNotFoundException;

    /**
     * Returns a {@link MavenMetadata} related to the {@code maven-metadata.xml} file.
     *
     * @param groupId the artifact groupdId (mandatory)
     * @param artifactId the artifact artifactId (mandatory)
     * @return the {@link MavenMetadata}
     * @throws AuthenticationException throw if there is an error related to authentication problems
     * @throws ArtifactNotFoundException throw if the artifact was not found on Nexus
     */
    MavenMetadata queryMavenArtifactMetaData(String groupId, String artifactId)
            throws AuthenticationException, ArtifactNotFoundException;

    /**
     * Query a Maven Artifact hosted in Nexus and retrieve it details.
     *
     * @param parameters the artifact parameters that will be queried
     * @return the artifact details in form of an {@link ArtifactResolveResource}.
     * @throws AuthenticationException throw if there is an error related to authentication problems
     */
    ArtifactResolveResource queryMavenArtifactDetails(ArtifactMavenResolveParameters parameters)
            throws AuthenticationException, ArtifactNotFoundException;
}
