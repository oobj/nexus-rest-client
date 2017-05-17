package br.com.oobj.nexus.model.maven.impl;

import br.com.oobj.nexus.model.maven.Packaging;

/**
 * A list of the current core packaging values definied by Apache Maven.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 08/03/2017
 * @see <a href="https://maven.apache.org/pom.html#Maven_Coordinates">Maven Coordinates</a>
 */
public enum CorePackaging implements Packaging {
    POM("pom"),
    JAR("jar"),
    MAVEN_PLUGIN("maven-plugin"),
    EJB("ejb"),
    WAR("war"),
    EAR("ear"),
    RAR("rar"),
    PAR("par"),
    ZIP("zip");

    private String name;

    CorePackaging(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Build a {@link CorePackaging} from a String.
     *
     * @param packaging the core packaging name. The case will be ignored.
     * @return the {@link CorePackaging} correspondent to the argument, otherwise a {@link CorePackaging#JAR} if none of them matches.
     */
    public static CorePackaging from(String packaging) {
        for (CorePackaging corePackagingEnum : values()) {
            if (corePackagingEnum.getName().equalsIgnoreCase(packaging)) {
                return corePackagingEnum;
            }
        }
        return JAR;
    }
}
