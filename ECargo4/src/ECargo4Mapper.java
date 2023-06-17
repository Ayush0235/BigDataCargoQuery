import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ECargo4Mapper extends Mapper<LongWritable, Text, Text, IntWritable> {
	
	protected void map (LongWritable offset, Text prior_purchases, Context con) throws IOException, InterruptedException {
		
		String lines[] = prior_purchases.toString().split(",");
		String prior_p = lines[6];
		int discount = Integer.parseInt(lines[9]);
		Text output_key = new Text(prior_p);
		IntWritable output_value = new IntWritable(discount);
		con.write(output_key, output_value);
			
	}

}