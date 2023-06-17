import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ECargo3Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	protected void map (LongWritable offset, Text priority, Context con) throws IOException, InterruptedException {
		
		String lines[] = priority.toString().split(",");
		int shipment_time = Integer.parseInt(lines[11]);	
		String prior = lines[7];
		Text output_key = new Text(prior);
		IntWritable output_value = new IntWritable(shipment_time);
		con.write(output_key, output_value);
			
	}

}