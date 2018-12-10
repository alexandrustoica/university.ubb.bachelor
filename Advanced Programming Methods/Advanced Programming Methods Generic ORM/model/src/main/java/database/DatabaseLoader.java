package database;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import javax.persistence.Entity;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author Alexandru Stoica
 * @version 1.0
 */

public class DatabaseLoader implements DatabaseGateway {

    private Configuration configuration;
    private final SessionFactory factory;
    private static Logger logger;

    public DatabaseLoader(DatabaseTypeInterface configurationType) {
        logger = Logger.getLogger(DatabaseLoader.class);
        setupConfiguration(configurationType);
        Collection<URL> urls = ClasspathHelper.forPackage("domain");
        Reflections reflections = new Reflections(ConfigurationBuilder.build()
                .setUrls(urls)
                .setScanners(new TypeAnnotationsScanner(), new SubTypesScanner()));
        Set<Class<?>> list = reflections.getTypesAnnotatedWith(Entity.class);
        list.forEach(type -> configuration.addAnnotatedClass(type));
        factory = configuration.buildSessionFactory();
    }

    @Override
    public Session openSession() {
        return factory.openSession();
    }

    private void setupConfiguration(DatabaseTypeInterface type) {
        Properties properties = new Properties();
        String url = ResourceBundle.getBundle("database_url").getString(type.getConfigurationKey());
        loadProperties(properties, url);
        configuration = new Configuration().addProperties(properties);
    }

    private void loadProperties(Properties properties, String url) {
        InputStream stream = getClass().getResourceAsStream(url);
        try {
            properties.load(stream);
        } catch (IOException exception) {
            logger.error(exception.getCause());
            throw new RuntimeException("Unable to load the database configuration...");
        }
    }
}
