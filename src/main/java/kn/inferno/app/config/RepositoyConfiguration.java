package kn.inferno.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"kn.inferno.domain.model"})
@EnableJpaRepositories(basePackages = {"kn.inferno"})
@EnableTransactionManagement
public class RepositoyConfiguration {
    
}
