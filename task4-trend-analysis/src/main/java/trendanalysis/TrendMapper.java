package trendanalysis;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

public class TrendMapper extends Mapper<LongWritable, Text, Text, Text> {
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String[] fields = value.toString().split("\t");
        if (fields.length < 5) return;

        String bookID = fields[0];
        int year = Integer.parseInt(fields[1]);
        String lemma = fields[2];
        int count = Integer.parseInt(fields[3]);
        double sentiment = Double.parseDouble(fields[4]);

        int decade = (year / 10) * 10;

        // Key = Decade, Value = count,sentiment
        context.write(new Text(Integer.toString(decade)),
                new Text(count + "," + sentiment));
    }
}

