import Model.ImgurResponse;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class Upload {
    private static final Logger logger = Logger.getLogger(Upload.class.getName());

    private Properties properties;
    private final String IMGUR_URL = "imgurUrl";
    private final String CONFIG_PROPERTIES = "config.properties";
    private final String CLIENT_ID = "clientId";
    private final String POST = "POST";
    private final String AUTHORIZATION = "Authorization";
    private final String CONTENT_TYPE = "Content-Type";
    private final String APPLICATION_FORM = "application/x-www-form-urlencoded";
    private final String ACCEPT = "Accept";
    private final String APPLICATION_JSON = "application/json";
    private final String CONNECTION_ID = "Client-ID";
    private final String SPACE = " ";

    public Upload() throws IOException {
        this.properties = new Properties();
        InputStream input = this.getClass().getClassLoader().getResourceAsStream(CONFIG_PROPERTIES);
        properties.load(input);
    }

    public String uploadImage(String imageUrl) throws IOException {
        URL url = new URL(properties.getProperty(IMGUR_URL));
        InputStream inputStream = establishConnectionForPost(url, imageUrl, properties.getProperty(CLIENT_ID));

        ObjectMapper mapper = new ObjectMapper();
        ImgurResponse imgurResponse = mapper.readValue(inputStream, ImgurResponse.class);

        if (imgurResponse != null && imgurResponse.getData() != null && imgurResponse.getData().getLink() != null){
            return imgurResponse.getData().getLink();
        } else {
            logger.info("Uploading Image failed: " + imageUrl);
            return null;
        }
    }


    InputStream establishConnectionForPost(URL url, String imageUrl, String clientId) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(POST);
        connection.setDoInput(true);
        connection.setDoOutput(true);

        System.out.println(APPLICATION_FORM);
        connection.setRequestProperty(AUTHORIZATION , CONNECTION_ID + SPACE + clientId);
        connection.setRequestProperty(CONTENT_TYPE, APPLICATION_FORM);
        connection.setRequestProperty(ACCEPT, APPLICATION_JSON);

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        outputStreamWriter.write(imageUrl);
        outputStreamWriter.flush();
        InputStream inputStream = connection.getInputStream();

        return inputStream;
    }
}
