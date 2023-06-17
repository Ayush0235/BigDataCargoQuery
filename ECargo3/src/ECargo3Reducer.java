import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class ECargo3Reducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	
	public void reduce(Text key, Iterable<IntWritable> values, Context con) throws IOException, InterruptedException {
		
		int total_cnt = 0, ontime_cnt = 0 , not_on_time_cnt = 0; 
		float fail_per = 0f, pass_per = 0f;
		
		for(IntWritable val : values) {
			
			int i = val.get();
			if(Integer.compare(i, 1) == 0)
				not_on_time_cnt ++;
			else
				ontime_cnt ++;
			total_cnt ++;
			
		}
		
		float x = ontime_cnt, y = not_on_time_cnt, z = total_cnt;
		fail_per = (y / z) * 100;
		pass_per = (x / z) * 100;
		
		System.out.println(total_cnt + " " + ontime_cnt + " " + not_on_time_cnt);
		Text output_key = new Text("Total shipments = " + total_cnt + " - For " + key + " product importance reached on time percentage is " + pass_per + " and not reached on time percentage is " + fail_per);
		con.write(output_key, null);
		
	}
	
}