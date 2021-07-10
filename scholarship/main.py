# Array to store the titles
words = []

# Review the titles and put them into a array for future use
def reviewInits():
    # Open a file for reading.
    reader = open("titles.txt", "r")
    
    if (reader is None):
        return 0
    
    size = 0
    i = 0

    # Read the titles in the file and add into the array.
    for element in reader:
        s = element
        words.append(s)
        size = size + 1

    # Close the file reader and return the size.
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

    if total == 0:
        print("Total is 0, unavailable to divide by 0")
        return
    
    # Find the matching words
    for tar in target:
        for element in lst:
            if element == tar:
                match += 1
    
    print(", total " , total ,", matched ", match, ", percentage ", float(match / total), "%",sep = "")

# Add a title into the titles.txt.
def add_words(title: str):
    # Open the titles.txt as append mode.
    with open("titles.txt", "a") as reader:
        reader.write("\n")
        reader.write(title)

print("Input the title of the scholarships title that you want to evaluate without the question mark. \nexample: Why do you deserve this scholarship")

while True:
    # Get the title from the user.
    target = input("Scholarship title: ")
    target = convert(target)

    # Open the file, and read it for future use.
    reviewInits()

    # Find the matching
    for element in words:
        reviewMatch(element.strip(), target)
    
    # Ask the user if they want to add the tile into the file.
    cont = input("Add to the file, 0 to add: ")
    if cont == 0:
        string = ""
        listToStr = ' '.join([str(elem) for elem in target])
        add_words(listToStr)

    # Ask the user if they want to repeat.
    cont = input("Repeat?, 0 to repeat: ")
    if cont != 0:
        break
