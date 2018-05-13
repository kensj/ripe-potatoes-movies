package potatoes.project;

import org.springframework.boot.web.server.MimeMappings;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomizationBean implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    @Override
    public void customize(ConfigurableServletWebServerFactory container) {
    	MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
        mappings.add("webm", "video/webm");
        container.setMimeMappings(mappings);
    }
}
