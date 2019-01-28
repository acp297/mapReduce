package Model;


/**
 * It is POJO class used by the jackson object mapper to create
 * object out of json response from imgur.
 */
public class ImgurResponse {

    private Data data;
    private String success;
    private int status;

    /**
     * It captures the data field from imgur.
     */
    public Data getData() {
        return data;
    }

    /**
     * It sets the data field.
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * It captures the success field from imgur.
     */
    public String getSuccess() {
        return success;
    }

    /**
     * It sets the success field.
     */
    public void setSuccess(String success) {
        this.success = success;
    }

    /**
     * It captures the status field from imgur.
     */
    public int getStatus() {
        return status;
    }

    /**
     * It sets the status field.
     */
    public void setStatus(int status) {
        this.status = status;
    }
}
