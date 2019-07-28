package org.home.recordings.configuration;

import org.home.recordings.domain.Recording;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration
public class Config implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(final RepositoryRestConfiguration config) {
        config.exposeIdsFor(Recording.class);

        // CORS
        config.getCorsRegistry().addMapping("/**");
    }

}
