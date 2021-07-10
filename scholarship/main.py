# Array to store the titles
words = []

# Review the titles and put them into a array for future use
def reviewInits():
    reader = open("titles.txt", "r")
    
    if (reader is None):
        return 0
    
    size = 0
    i = 0
    for element in reader:
        s = element
        words.append(s)
        size = size + 1

    reader.close()
    return size

# Convert a sentence into a array
def convert(lst):
    return ([i for item in lst for i in item.split()])

# Review the matching word, from the bank of titles
def reviewMatch(targetWords, targets):
    arr =  []
    arr.append(targetWords)

    # Convert the sentence into array of individual words
    lst = convert(arr)
    total = len(lst)
    match = 0

    print("'", targetWords, "'", sep = "", end= "")

    # Find the matching words
    for tar in target:
        for element in lst:
            if element == tar:
                match += 1
    
    print(", total " , total ,", matched ", match, ", percentage ", float(match / total), sep = "")

reviewInits()
target = ["What is life and death"]
target = convert(target)

for element in words:
    reviewMatch(element.strip(), target)
