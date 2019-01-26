import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

class MapperClass extends Mapper<LongWritable, Text, Text, Text> {

    private final static IntWritable one = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        String[] imageUrls = line.split(",");

        Upload upload = new Upload();
        for (String imageUrl : imageUrls){
            String imgurlLink = upload.uploadImage(imageUrl);
            context.write(new Text(imgurlLink), new Text());
        }
    }
}
