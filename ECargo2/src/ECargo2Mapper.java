import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ECargo2Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	protected void map (LongWritable offset, Text shipment_mode, Context con) throws IOException, InterruptedException {
		
		String lines[] = shipment_mode.toString().split(",");
		int customer_calls = Integer.parseInt(lines[3]);	
		String sm = lines[2];
		Text output_key = new Text(sm);
		IntWritable output_value = new IntWritable(customer_calls);
		con.write(output_key, output_value);
			
	}

}