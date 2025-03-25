package com.task5;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.util.ArrayList;
import java.util.List;

public class BigramUDF extends UDF {
    public List<String> evaluate(List<String> words) {
        List<String> bigrams = new ArrayList<>();
        if (words == null || words.size() < 2) return bigrams;

        for (int i = 0; i < words.size() - 1; i++) {
            bigrams.add(words.get(i) + "_" + words.get(i + 1));
        }

        return bigrams;
    }
}

