import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ECargo1Reducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
	
	Float average = 0f, sum = 0f;
	int cnt = 0;

	public void reduce(Text key, Iterable<FloatWritable> values, Context con) throws IOException, InterruptedException {
		
		for(FloatWritable val : values) {
			
			sum += val.get();
			cnt ++;
			
		}
		
		average = sum / cnt;
		sum = 0f;
		
		Text output_key = new Text("Block " + key + ":");
		FloatWritable output_value = new FloatWritable(average);
		con.write(output_key, output_value);
		
	}
	
}