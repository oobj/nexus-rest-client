package br.com.oobj.nexus.model.nexus;

/**
 * The artifact extension on Nexus
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 * @see <a href="https://maven.apache.org/pom.html#Maven_Coordinates">Maven Coordinates</a>
 */
public interface Extension {

    /**
     *
     * @return the extension name
     */
    String getName();
}
