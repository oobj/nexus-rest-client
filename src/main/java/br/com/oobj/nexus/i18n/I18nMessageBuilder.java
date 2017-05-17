package br.com.oobj.nexus.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * A service to retrieve internationalized messages.
 *
 * @author <a href="mailto:guimaraes.djl@gmail.com">Danilo Guimar&atilde;es</a>
 * @since 13/03/2017
 */
@Service
public class I18nMessageBuilder {

    private final MessageSource messageSource;

    @Autowired
    public I18nMessageBuilder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Retrieve an internationalized message.
     *
     * @param message
     *            an instance of {@link InternationalizedMessage}
     *
     * @return {@link String} representing the internationlized message
     */
    public String getMessage(InternationalizedMessage message) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message.getMessageId(), null, locale);
    }

    /**
     * Retrieve an internationalized message with parameters.
     *
     * @param message
     *            an instance of {@link InternationalizedMessage}
     * @param params
     *            parameter array received in the same order that will be replaced
     * @return {@link String} representing the internationlized message
     */
    public String getMessage(InternationalizedMessage message, Object ... params) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(message.getMessageId(), params, locale);
    }
}
