package terminal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

public class Utils {
    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    public static Properties loadProperties(String configFile) {
        LOG.info("Loading config: " + new File(configFile).getAbsolutePath());
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(configFile);
            properties.load(inputStream);
        } catch (IOException e) {
            LOG.severe(e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.finest(e.getMessage());
                }
            }
        }
        LOG.fine("Loaded config: " + properties);
        return properties;
    }
}
