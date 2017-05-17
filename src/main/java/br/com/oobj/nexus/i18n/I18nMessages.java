package br.com.oobj.nexus.i18n;

/**
 * Internationalized messages enumeration.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 13/03/2017
 */
public enum I18nMessages implements InternationalizedMessage {

    GROUP_ID_MUST_NOT_BE_NULL_OR_EMPTY("group.id.must.not.be.null.or.empty"),
    ARTIFACT_ID_MUST_NOT_BE_NULL_OR_EMPTY("artifact.id.must.not.be.null.or.empty"),
    VERSION_MUST_NOT_BE_NULL_OR_EMPTY("version.must.not.be.null.or.empty"),
    REPOSITORY_MUST_NOT_BE_NULL_OR_EMPTY("repository.must.not.be.null.or.empty"),
    GAVR_MUST_NOT_BE_NULL("gavr.must.not.be.null"),

    ARTIFACT_NOT_FOUND("artifact.not.found"),

    MAVEN_METADATA_XML_PARSE_FAILED("maven.metadata.xml.parse.failed"),

    AUTHENTICATION_PROBLEM("authentication.problem")
    ;

    private String messageId;

    I18nMessages(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String getMessageId() {
        return messageId;
    }
}
