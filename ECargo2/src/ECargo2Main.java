import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class ECargo2Main {
	
	public static void main(String[] args) throws Exception {
		
		Configuration c = new Configuration();
		@SuppressWarnings("deprecation")
		Job j = new Job(c, "Cargo2");
		j.setJar("ECargo2.jar");
		j.setJarByClass(ECargo2Main.class);
		j.setMapperClass(ECargo2Mapper.class);
		j.setReducerClass(ECargo2Reducer.class);
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));
		System.exit(j.waitForCompletion(true)? 0 : 1);

	}

}