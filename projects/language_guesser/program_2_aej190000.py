# Alexis Jennings
# CS 4395.001
# N-Grams Program 2

import pathlib
import pickle
from nltk import ngrams, word_tokenize


# method to compute the probability of the given text being in English, French, or Italian
def compute_prob(text, unigram_dict, bigram_dict, v):
    # n is the number of tokens in the training data
    # v is the vocabulary size in the training data (unique tokens)

    # tokenize the text
    unigrams_test = word_tokenize(text)
    # get the bigrams
    bigrams_test = list(ngrams(unigrams_test, 2))
    # calculate probabilities using Laplace smoothing
    p_laplace = 1
    for bigram in bigrams_test:
        n = bigram_dict[bigram] if bigram in bigram_dict else 0
        d = unigram_dict[bigram[0]] if bigram[0] in unigram_dict else 0
        p_laplace = p_laplace * ((n + 1) / (d + v))

    return p_laplace


def main():
    # unpickle the dictionaries
    unigram_dict_eng = pickle.load(open('unigram_dict_eng.p', 'rb'))  # read binary
    bigram_dict_eng = pickle.load(open('bigram_dict_eng.p', 'rb'))  # read binary
    unigram_dict_fre = pickle.load(open('unigram_dict_fre.p', 'rb'))  # read binary
    bigram_dict_fre = pickle.load(open('bigram_dict_fre.p', 'rb'))  # read binary
    unigram_dict_ita = pickle.load(open('unigram_dict_ita.p', 'rb'))  # read binary
    bigram_dict_ita = pickle.load(open('bigram_dict_ita.p', 'rb'))  # read binary

    with open(pathlib.Path.cwd().joinpath("LangId.test"), 'r') as f:
        text = f.readlines()

    f = open("probability_results.txt", "a")

    # get the number of unigrams for each language
    v_eng = len(unigram_dict_eng)
    v_fre = len(unigram_dict_fre)
    v_ita = len(unigram_dict_ita)

    for line_id, line in enumerate(text):
        # calculate the probabilities for each language
        prob_eng = compute_prob(line, unigram_dict_eng, bigram_dict_eng, v_eng)
        prob_fre = compute_prob(line, unigram_dict_fre, bigram_dict_fre, v_fre)
        prob_ita = compute_prob(line, unigram_dict_ita, bigram_dict_ita, v_ita)

        # find the highest probability from the three languages, write the result to a file
        highest = max(prob_eng, prob_fre, prob_ita)
        if highest == prob_eng:
            f.write(str(line_id + 1) + " English\n")
        elif highest == prob_fre:
            f.write(str(line_id + 1) + " French\n")
        elif highest == prob_ita:
            f.write(str(line_id + 1) + " Italian\n")
        else:
            f.write("Not found\n")

    with open(pathlib.Path.cwd().joinpath("LangId.sol"), 'r') as f:
        answers = f.readlines()
    with open(pathlib.Path.cwd().joinpath("probability_results.txt"), 'r') as f:
        guesses = f.readlines()

    num_correct = 0
    total = len(answers)
    incorrect = []

    # compare the lines of the solutions and results files to find the accuracy of the models
    for line_id, line in enumerate(answers):
        if line == guesses[line_id]:
            num_correct += 1
        else:
            incorrect.append(line_id)
    accuracy = (total - len(incorrect)) / total
    print("Accuracy: %.5f" % accuracy)
    print("Incorrect lines:")
    print(incorrect)


if __name__ == "__main__":
    main()
