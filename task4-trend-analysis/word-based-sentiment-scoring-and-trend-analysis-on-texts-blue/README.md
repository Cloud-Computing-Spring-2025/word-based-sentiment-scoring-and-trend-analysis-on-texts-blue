# multi-stage-sentiment-analysis-Mapreduce_hive
This is Multi-Stage Sentiment Analysis on Historical Literature application is developed with map reduce and hive

## Overview

This project aims to analyze sentiment trends in historical literature by processing digitized texts from the 18th and 19th centuries. The analysis is conducted using Hadoop MapReduce in Java and Apache Hive for bigram analysis. The dataset consists of multiple books, each with metadata including book ID, title, and year of publication.

## Objectives

1.**Data Extraction & Cleaning:** Extract raw text data (book ID, title, publication year) and preprocess by converting to lowercase and removing stop words.

2.**Word Frequency Analysis:** Split sentences into words, apply lemmatization, and compute word frequencies.

3.**Sentiment Analysis:** Assign sentiment scores using sentiment lexicons (AFINN or SentiWordNet) and map them to books.

4.**Trend Analysis:** Aggregate sentiment scores and word frequencies over decades to observe long-term trends.

5.**Bigram Analysis:** Implement a custom Hive UDF to extract and analyze bigrams.

## Setup and Execution

### 1. **Start the Hadoop Cluster**

Run the following command to start the Hadoop cluster:

```bash
docker compose up -d
```

### 2. **Build the Code**

Build the code using Maven:

```bash
mvn install
```

### 3. **Copy JAR to Docker Container**

Copy the JAR file to the Hadoop ResourceManager container:

```bash
docker cp DataCleaningMapReduce-1.0.0.jar resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/
```

### 4. **Move Dataset to Docker Container**

Copy the dataset to the Hadoop ResourceManager container:

```bash
docker cp input/historical_books.csv resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/
```

In each and every task will continue here without change any line, and here after we will change the input as output of task1 and from the task3 to task5, task2 output is used as a input. Everything will continue without change.

### 5. **Connect to Docker Container**

Access the Hadoop ResourceManager container:

```bash
docker exec -it resourcemanager /bin/bash
```

Navigate to the Hadoop directory:

```bash
cd /opt/hadoop-3.2.1/share/hadoop/mapreduce/
```

### 6. **Set Up HDFS**

Create a folder in HDFS for the input dataset:

```bash
hadoop fs -mkdir -p /input/dataset
```

Copy the input dataset to the HDFS folder:

```bash
hadoop fs -put ./historical_books.csv /input/dataset
```

### 7. **Execute the MapReduce Job**

Run your MapReduce job using the following command:

```bash
hadoop jar /opt/hadoop-3.2.1/share/hadoop/mapreduce/DataCleaningMapReduce-1.0.0.jar com.example.driver /input/dataset/historical_books.csv /output_task1
```

### 8. **View the Output**

To view the output of your MapReduce job, use:

```bash
hadoop fs -cat /output_task1/*
```

### 9. **Copy Output from HDFS to Local OS**

To copy the output from HDFS to your local machine:

1. Use the following command to copy from HDFS:
    ```bash
    hdfs dfs -get /output /opt/hadoop-3.2.1/share/hadoop/mapreduce/
    ```

2. use Docker to copy from the container to your local machine:
   ```bash
   exit 
   ```
    ```bash
    docker cp resourcemanager:/opt/hadoop-3.2.1/share/hadoop/mapreduce/output/ output_task1/
    ```
3. Commit and push to your repo so that we can able to see your output

### Implementation

**Task 1:** Preprocessing MapReduce Job

Extract book ID, title, and publication year.

Convert text to lowercase, remove punctuation, and stop words.

**Task 2:** Word Frequency Analysis with Lemmatization

Tokenize text and apply lemmatization.

Compute word frequencies per book and year.

**Task 3:** Sentiment Scoring

Match words to sentiment lexicons (AFINN/SentiWordNet).

Compute sentiment scores for each book and year.

**Task 4:** Trend Analysis & Aggregation

Aggregate sentiment scores and word frequencies over decades.

Summarize sentiment trends per book and time period.

**Task 5:** Bigram Analysis using Hive UDF

Implement a Java-based Hive UDF to extract bigrams.

Store and analyze bigram frequencies in Hive tables.

### Final Deliverables

## Source Code:

Java MapReduce implementations.

Hive UDF for bigram analysis.

### Results:

Processed datasets with word frequencies and sentiment scores.

Optional visualizations for sentiment trends and bigram distributions.
