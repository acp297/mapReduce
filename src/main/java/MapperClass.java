import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

class MapperClass extends Mapper<LongWritable, Text, Text, Text> {

    /**
     * Called once for each key/value pair in the input split.
     * Most applications should override this, but the default is the identity function.
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] imageUrls = line.split(",");

        Upload upload = new Upload();
        for (String imageUrl : imageUrls){
            String imgurlLink = upload.uploadImage(imageUrl);

            if (imgurlLink == null){
                System.err.printf("Uploading Failed !");
                return;
            }
            context.write(new Text(imgurlLink), new Text());
        }
    }
}
