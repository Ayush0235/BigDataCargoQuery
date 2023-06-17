import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class ECargo1Main {
	
	public static void main(String[] args) throws Exception {
		
		Configuration c = new Configuration();
		@SuppressWarnings("deprecation")
		Job j = new Job(c, "Cargo1");
		j.setJar("ECargo1.jar");
		j.setJarByClass(ECargo1Main.class);
		j.setMapperClass(ECargo1Mapper.class);
		j.setReducerClass(ECargo1Reducer.class);
		j.setOutputKeyClass(Text.class);
		j.setOutputValueClass(FloatWritable.class);
		FileInputFormat.addInputPath(j, new Path(args[0]));
		FileOutputFormat.setOutputPath(j, new Path(args[1]));
		System.exit(j.waitForCompletion(true)? 0 : 1);

	}

}