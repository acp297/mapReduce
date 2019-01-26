package Model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {



    @JsonProperty("link")
    private String link;




    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
