import java.io.IOException;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


public class ECargo1Mapper extends Mapper<LongWritable, Text, Text, FloatWritable> {
	
	protected void map (LongWritable offset, Text warehouse_block, Context con) throws IOException, InterruptedException {
		
		String lines[] = warehouse_block.toString().split(",");
		float weight_in_gms = Float.parseFloat(lines[10]);	
		String wb = lines[1];
		Text output_key = new Text(wb);
		FloatWritable output_value = new FloatWritable(weight_in_gms);
		con.write(output_key, output_value);
			
	}

}