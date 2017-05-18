package br.com.oobj.nexus.model.maven;

/**
 * Represents a packaging value from a Maven artifact.
 *
 * <p>
 * Packaging is the project artifact type. When no packaging is declared, Maven assumes the artifact is by default
 * {@code jar}.
 * </p>
 *
 * <p>
 * The current core packaging values are: {@code pom}, {@code jar}, {@code maven-plugin}, {@code ejb}, {@code war},
 * {@code ear}, {@code rar}, {@code par}.
 * </p>
 *
 * You will sometimes see Maven print out a project coordinate as {@code groupId:artifactId:packaging:version}.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 * @see <a href="https://maven.apache.org/pom.html#Maven_Coordinates">Maven Coordinates</a>
 */
public interface Packaging {

    /**
     *
     * @return the packaging name
     */
    String getName();
}
