
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class Driver extends Configured implements Tool{

    public static void main(String[] args) throws Exception{
        int exitCode = ToolRunner.run(new Driver(), args);
        System.exit(exitCode);
    }

    /**
     * It configures the Job.
     * @param args input output path
     * @return
     * @throws Exception
     */
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.printf("Invalid Arguments");
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }

        Job job = new Job();
        job.setJarByClass(Driver.class);
        job.setJobName("imageUploadJob");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapperClass(MapperClass.class);
        job.setNumReduceTasks(0);

        int returnValue = job.waitForCompletion(true) ? 0:1;
        System.out.println("job is Successful");
        return returnValue;
    }
}