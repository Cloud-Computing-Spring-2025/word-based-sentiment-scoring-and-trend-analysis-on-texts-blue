package trendanalysis;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class TrendReducer extends Reducer<Text, Text, Text, Text> {
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        int totalCount = 0;
        double totalSentiment = 0.0;

        for (Text val : values) {
            String[] parts = val.toString().split(",");
            int count = Integer.parseInt(parts[0]);
            double sentiment = Double.parseDouble(parts[1]);

            totalCount += count;
            totalSentiment += sentiment;
        }

        double avgSentiment = totalCount == 0 ? 0 : totalSentiment / totalCount;
        context.write(key, new Text("WordCount: " + totalCount + ", AvgSentiment: " + avgSentiment));
    }
}

