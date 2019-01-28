package Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * It is POJO class used by the jackson object mapper to create
 * object out of json response from imgur.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

    @JsonProperty("link")
    private String link;

    /**
     * It captures the imgur image link from Imgur response.
     */
    public String getLink() {
        return link;
    }

    /**
     * It sets the link field.
     */
    public void setLink(String link) {
        this.link = link;
    }
}
