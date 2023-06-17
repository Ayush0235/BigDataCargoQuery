import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.util.HashMap;
import java.util.Map.Entry;


public class ECargo2Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	int sum = 0, min = 0, max = 0;
	HashMap<String, Integer> hm = new HashMap<String, Integer>(10);
	
	public void reduce(Text key, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException {
		
		String my_key = String.valueOf(key);
		
		for(IntWritable val : values)
			sum += val.get();
		
		hm.put(my_key, sum);
		sum = 0;
		
	}
	
	protected void cleanup(Context con) throws IOException, InterruptedException {
		
		int max_value = 0;
		String max_key = "";
		
		for(Entry<String, Integer> entry : hm.entrySet() ) {
			
			Text output_key = new Text("" + entry.getKey());
			IntWritable output_value = new IntWritable(entry.getValue());
			con.write(output_key, output_value);
			
			if(max_value < entry.getValue()) {
				
				max_value = entry.getValue();
				max_key = entry.getKey();
				
			}
			
		}
		
		Text max_output_key = new Text("The method of " + max_key + " receives the most inquiries from customers i.e.");
		IntWritable max_output_value = new IntWritable(max_value);
		con.write(max_output_key, max_output_value);
		
	}
	
}