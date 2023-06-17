import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ECargo4Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException {
		
		int sum = 0, cnt = 0;
		float x = 0f, y = 0f, discount = 0f;
		
		for(IntWritable val : values) {
			
			sum += val.get();
			cnt ++;
			
		}
		
		x = sum;
		y = cnt;
		discount = x / y;
		Text output_key = new Text("Customers with " + key + " prior purchases recieves an average discount of " + discount + "%");
		con.write(output_key, null);
		
	}
	
}