package com.task5;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class BigramUDF extends UDF {

    public Text evaluate(Text input) {
        if (input == null) return null;

        String[] words = input.toString().split("\\s+");

        StringBuilder bigrams = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            bigrams.append(words[i]).append("_").append(words[i + 1]).append(" ");
        }

        return new Text(bigrams.toString().trim());
    }
}
