package br.com.oobj.nexus.model.nexus;

import br.com.oobj.nexus.config.AppConfig;
import br.com.oobj.nexus.model.maven.Classifier;
import br.com.oobj.nexus.model.maven.Packaging;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.contains;

/**
 * Unit tests for {@link ArtifactMavenResolveParametersBuilder}.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 17/05/2017
 */
@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ArtifactMavenResolveParametersBuilderTest {

    private static final String GROUP_ID = "com.my.company";
    private static final String ARTIFACT_ID = "my-project";
    private static final String VERSION = "1.0.0";
    private static final String REPOSITORY = "release";
    private static final Packaging PACKAGING = () -> "packaging";
    private static final Classifier CLASSIFIER = () -> "classifier";
    private static final Extension EXTENSION = () -> "extension";

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private ArtifactMavenResolveParameters artifactMavenResolveParameters;

    @Autowired
    private ArtifactMavenResolveParametersBuilder sut;

    @Before
    public void setUp() throws Exception {
        artifactMavenResolveParameters = sut.groupId(GROUP_ID)
                .artifactId(ARTIFACT_ID)
                .version(VERSION)
                .repository(REPOSITORY)
                .packaging(PACKAGING)
                .classifier(CLASSIFIER)
                .extension(EXTENSION)
                .build();
    }

    @Test
    public void testGroupId() throws Exception {
        assertEquals(GROUP_ID, artifactMavenResolveParameters.getGroupdId());
    }

    @Test
    public void testArtifactId() throws Exception {
        assertEquals(ARTIFACT_ID, artifactMavenResolveParameters.getArtifactId());
    }

    @Test
    public void testVersion() throws Exception {
        assertEquals(VERSION, artifactMavenResolveParameters.getVersion());
    }

    @Test
    public void testRepository() throws Exception {
        assertEquals(REPOSITORY, artifactMavenResolveParameters.getRepository());
    }

    @Test
    public void testPackaging() throws Exception {
        assertEquals(PACKAGING, artifactMavenResolveParameters.getPackaging());
    }

    @Test
    public void testClassifier() throws Exception {
        assertEquals(CLASSIFIER, artifactMavenResolveParameters.getClassifier());
    }

    @Test
    public void testExtension() throws Exception {
        assertEquals(EXTENSION, artifactMavenResolveParameters.getExtension());
    }

    @Test
    public void testEmptyGroupIdMustThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(contains("groupId"));

        artifactMavenResolveParameters = sut.groupId("")
                .artifactId(ARTIFACT_ID)
                .version(VERSION)
                .repository(REPOSITORY)
                .build();
    }

    @Test
    public void testEmptyArtifactIdMustThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(contains("artifactId"));

        artifactMavenResolveParameters = sut.groupId(GROUP_ID)
                .artifactId("")
                .version(VERSION)
                .repository(REPOSITORY)
                .build();
    }

    @Test
    public void testEmptyVersionMustThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(contains("version"));

        artifactMavenResolveParameters = sut.groupId(GROUP_ID)
                .artifactId(ARTIFACT_ID)
                .version("")
                .repository(REPOSITORY)
                .build();
    }

    @Test
    public void testEmptyRepositoryMustThrowIllegalArgumentException() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage(contains("repository"));

        artifactMavenResolveParameters = sut.groupId(GROUP_ID)
                .artifactId(ARTIFACT_ID)
                .version(VERSION)
                .repository("")
                .build();
    }


}