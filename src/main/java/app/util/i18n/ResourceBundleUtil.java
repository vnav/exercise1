package app.util.i18n;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;


@Component
public class ResourceBundleUtil implements MessageSourceAware {
	private MessageSource messageSource;
	
	public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Gets the message.
     *
     * @param key the key
     * @param locale the locale
     * @return the message
     */
    public String getMessage(String key, String string, Locale locale) {
    	return messageSource.getMessage(key, null, string, locale);
    }

    public String getMessage(String key) {
    	return messageSource.getMessage(key, null, "", Locale.US);
    }

    public String getMessage(String key, String defaultMessage) {
    	return messageSource.getMessage(key, null, "", Locale.US);
    }
}