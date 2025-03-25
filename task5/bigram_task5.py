import pandas as pd
from collections import defaultdict
from itertools import tee

# Load the lemmatized file from Task 2
df = pd.read_csv("output_task2.tsv", sep="\t", header=None, names=["book_id", "word", "year", "count"])

# Group by (book_id, year)
grouped = defaultdict(list)
for _, row in df.iterrows():
    grouped[(row["book_id"], row["year"])].append(row["word"])

# Generate bigrams
def generate_bigrams(words):
    a, b = tee(words)
    next(b, None)
    return [f"{x} {y}" for x, y in zip(a, b)]

# Build results
results = []
for (book_id, year), words in grouped.items():
    for bigram in generate_bigrams(words):
        results.append((book_id, year, bigram))

# Write to output
out_df = pd.DataFrame(results, columns=["book_id", "year", "bigram"])
out_df.to_csv("output_task5.tsv", sep="\t", index=False)

print("✅ Bigrams saved to output_task5.tsv")
