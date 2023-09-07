# Alexis Jennings

import sys
import pathlib
from nltk import word_tokenize, pos_tag
from nltk.corpus import stopwords
from nltk.stem import WordNetLemmatizer
from collections import Counter
from random import seed
from random import randint


# method to preprocess a list of tokens
def preprocess(tokens):
    # lowercase tokens, remove punctuation, remove stopwords, and only keep words with lengths greater than 5
    tokens = [t.lower() for t in tokens]
    tokens = [t for t in tokens if t.isalpha() and t not in stopwords.words('english') and len(t) > 5]

    # lemmatize remaining tokens
    wnl = WordNetLemmatizer()
    lemmas = [wnl.lemmatize(t) for t in tokens]
    lemmas_unique = list(set(lemmas))

    # pos tag unique lemmas
    tags = pos_tag(lemmas_unique)

    # keep only nouns of unique lemmas
    nouns = [t[0] for t in tags if t[1] == 'NN']
    return tokens, nouns


# method to play a word guessing game using a given list of words
def game(word_list):
    points = 5
    guesses = ''
    game_reset = 0  # flag to reset the word to guess and guesses if the previous word has been correctly guessed
    seed(12348)

    while points >= 0:
        # if a new word is needed, get new word to guess, reset guesses, and print new guessing game prompt
        if game_reset == 0:
            word_to_guess = word_list[randint(0, 49)]
            guesses = ''
            game_reset = 1
            print("Guess the word:")

        # print the word: _ for not guessed letters, and the letter for guessed letters
        for c in word_to_guess:
            if c in guesses:
                print(c, end=" ")
            else:
                print("_", end=" ")

        # get the player's guess, add it to the guessed letters
        guess = input("\nPlease guess a letter: ")
        guesses += guess

        # if the guess is !, end the game
        if guess == '!':
            print("Game ended.")
            break
        # if the guessed letter is not in the word, deduct 1 point and tell the player the bad news
        elif guess not in word_to_guess:
            points -= 1
            print("Sorry, " + guess + " is not in the word.")
        # if the guessed letter is in the word, add 1 point and tell the player the good news
        else:
            points += 1
            print("Correct! " + guess + " is in the word.")

        # if points are not below 0, print current score and reset the string keeping track of remaining letters
        if points >= 0:
            print("Current score: " + str(points))
            letters_left = ''
        # if points are below 0, the player loses and the game ends
        else:
            print("You lose!")
            break

        # check if all letters of the word to guess have been guessed
        for c in word_to_guess:
            if c not in guesses:
                letters_left += c
        # if there are no letters to guess left, tell the player they won and reset the game
        if len(letters_left) == 0:
            print("You win!")
            game_reset = 0
            continue


def main():
    # make data file path cross-platform
    if __name__ == '__main__':
        if len(sys.argv) < 2:
            print('Please enter a filename as a system arg')
        else:
            fp = sys.argv[1]
            with open(pathlib.Path.cwd().joinpath(fp), 'r') as f:
                text = f.read()

    # calculate lexical diversity
    tokens = word_tokenize(text)
    words_set = set(tokens)

    # preprocess text
    tokens, nouns = preprocess(tokens)

    # create dictionary, keys are nouns and values are the count of that noun in tokens
    counted = Counter(tokens).most_common()
    counted = [t for t in counted if t[0] in nouns]
    word_dict = dict(counted)
    print("50 most common words and their counts: " + str(list(word_dict.items())[:50]))

    # save 50 most common words as list for game
    word_list = list(word_dict.keys())

    # start game
    game(word_list)


if __name__ == "__main__":
    main()
