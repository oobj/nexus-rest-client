package br.com.oobj.nexus.model.maven.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests of {@link CorePackaging} Enum.
 *
 * <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 09/03/2017
 */
public class CorePackagingTest {

    @Test
    public void testFromPom() throws Exception {
        CorePackaging pom = CorePackaging.from("pom");

        assertEquals(CorePackaging.POM, pom);
    }

    @Test
    public void testFromJar() throws Exception {
        CorePackaging jar = CorePackaging.from("jar");

        assertEquals(CorePackaging.JAR, jar);
    }

    @Test
    public void testFromMavenPlugin() throws Exception {
        CorePackaging mavenPlugin = CorePackaging.from("maven-plugin");

        assertEquals(CorePackaging.MAVEN_PLUGIN, mavenPlugin);
    }

    @Test
    public void testFromEjb() throws Exception {
        CorePackaging ejb = CorePackaging.from("ejb");

        assertEquals(CorePackaging.EJB, ejb);
    }

    @Test
    public void testFromWar() throws Exception {
        CorePackaging war = CorePackaging.from("war");

        assertEquals(CorePackaging.WAR, war);
    }

    @Test
    public void testFromEar() throws Exception {
        CorePackaging ear = CorePackaging.from("ear");

        assertEquals(CorePackaging.EAR, ear);
    }

    @Test
    public void testFromRar() throws Exception {
        CorePackaging rar = CorePackaging.from("rar");

        assertEquals(CorePackaging.RAR, rar);
    }

    @Test
    public void testFromPar() throws Exception {
        CorePackaging par = CorePackaging.from("par");

        assertEquals(CorePackaging.PAR, par);
    }

    @Test
    public void testNullReturnsJar() {
        CorePackaging nullCorePackaging = CorePackaging.from(null);

        assertEquals(CorePackaging.JAR, nullCorePackaging);
    }

    @Test
    public void testEmptyStringReturnsJar() {
        CorePackaging emptyCorePackaging = CorePackaging.from("");

        assertEquals(CorePackaging.JAR, emptyCorePackaging);
    }

}