import re
import csv
from collections import defaultdict

input_file = "task3_output.txt"
trend_data = defaultdict(lambda: [0, 0.0])  # {decade: [count, total_sentiment]}

with open(input_file, "r") as file:
    for line in file:
        parts = line.strip().split()
        if len(parts) != 2:
            continue

        # Extract year from first part, which looks like: (1,1813)
        match = re.search(r"\d+,\s*(\d{4})", parts[0])
        if not match:
            continue

        try:
            year = int(match.group(1))
            decade = (year // 10) * 10
            sentiment = float(parts[1])

            trend_data[decade][0] += 1  # count of entries
            trend_data[decade][1] += sentiment  # sum of sentiment
        except ValueError:
            continue

# Print results
print(f"{'Decade':<10} {'Entries':<10} {'AvgSentiment'}")
for decade in sorted(trend_data):
    count, total_sentiment = trend_data[decade]
    avg = total_sentiment / count if count else 0
    print(f"{decade:<10} {count:<10} {avg:.4f}")

with open("task4_output.csv", "w", newline="") as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(["Decade", "Count", "AvgSentiment"])
    for decade in sorted(trend_data):
        count, sentiment_sum = trend_data[decade]
        avg_sentiment = sentiment_sum / count if count else 0
        writer.writerow([decade, count, round(avg_sentiment, 4)])

