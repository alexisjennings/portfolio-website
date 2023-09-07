# Alexis Jennings

import pathlib
import pickle
from nltk import word_tokenize, ngrams


# method to create unigram and bigram dictionaries given a file with text
def create_dicts(filename):
    fp = filename
    with open(pathlib.Path.cwd().joinpath(fp), 'r') as f:
        fin = f.read()

    # replace newlines with spaces
    text = fin.replace('\n', ' ')
    # tokenize the text
    tokens = word_tokenize(text)
    # create the lists of unigrams and bigrams
    unigrams = list(ngrams(tokens, 1))
    bigrams = list(ngrams(tokens, 2))
    # create the dictionaries of unigrams and bigrams
    unigram_dict = {t: unigrams.count(t) for t in set(unigrams)}
    bigram_dict = {b: bigrams.count(b) for b in set(bigrams)}
    return unigram_dict, bigram_dict


def main():
    # create dictionaries of unigrams and bigrams for the training data
    unigram_dict_eng, bigram_dict_eng = create_dicts("LangId.train.English")
    unigram_dict_fre, bigram_dict_fre = create_dicts("LangId.train.French")
    unigram_dict_ita, bigram_dict_ita = create_dicts("LangId.train.Italian")

    # pickle the dictionaries
    pickle.dump(unigram_dict_eng, open('unigram_dict_eng.p', 'wb'))
    pickle.dump(bigram_dict_eng, open('bigram_dict_eng.p', 'wb'))
    pickle.dump(unigram_dict_fre, open('unigram_dict_fre.p', 'wb'))
    pickle.dump(bigram_dict_fre, open('bigram_dict_fre.p', 'wb'))
    pickle.dump(unigram_dict_ita, open('unigram_dict_ita.p', 'wb'))
    pickle.dump(bigram_dict_ita, open('bigram_dict_ita.p', 'wb'))


if __name__ == '__main__':
    main()
