package br.com.oobj.nexus.model.maven;

/**
 * The classifier allows to distinguish artifacts that were built from the same POM but differ in their content.
 * It is some optional and arbitrary string that - if present - is appended to the artifact name just after the
 * version number.
 *
 * <p>
 * As a motivation for this element, consider for example a project that offers an artifact targeting JRE 1.5 but at
 * the same time also an artifact that still supports JRE 1.4. The first artifact could be equipped with the classifier
 * {@code jdk15} and the second one with {@code jdk14} such that clients can choose which one to use.
 * </p>
 *
 * <p>
 * Another common use case for classifiers is the need to attach secondary artifacts to the project's main artifact.
 * If you browse the Maven central repository, you will notice that the classifiers {@code sources} and {@code javadoc}
 * are used to deploy the project source code and API docs along with the packaged class files.
 * </p>
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 * @see <a href="https://maven.apache.org/pom.html#Maven_Coordinates">Maven Coordinates</a>
 */
public interface Classifier {

    /**
     *
     * @return the classifier name
     */
    String getName();
}
