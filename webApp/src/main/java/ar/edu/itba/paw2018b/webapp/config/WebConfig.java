package ar.edu.itba.paw2018b.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

@EnableWebMvc
@ComponentScan({"ar.edu.itba.paw2018b.webapp.controller", "ar.edu.itba.paw2018b.services","ar.edu.itba.paw2018b.persistence" })
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Value("classpath:script.sql")
    private Resource scriptSql;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource){
        final DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);
        dataSourceInitializer.setDatabasePopulator(databasePopulator());
        return dataSourceInitializer;
    }

    private DatabasePopulator databasePopulator(){
        final ResourceDatabasePopulator dbp = new ResourceDatabasePopulator();
        dbp.addScript(scriptSql);
        return dbp;
    }
    @Bean
    public ViewResolver viewResolver() {
        final InternalResourceViewResolver viewResolver =
                new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource ds = new SimpleDriverDataSource();
        ds.setDriverClass(org.postgresql.Driver.class);
        ds.setUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("postgres");
        ds.setPassword("atlas1");
        return ds;
    }
}