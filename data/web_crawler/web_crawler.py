# Alexis Jennings
# CS 4395.001
# Web Crawler Assignment

import pickle
from bs4 import BeautifulSoup
from urllib.request import Request, urlopen
import requests
import re
from nltk import sent_tokenize, word_tokenize
from nltk.corpus import stopwords
from collections import Counter


# tokenizes text from a file and finds the most common words
def preprocess():
    counter = 1
    words = []
    while counter <= 15:
        with open('%d_B.txt' % counter, 'r') as f:
            data = f.read()
        tokens = word_tokenize(data)
        # lowercase tokens, remove punctuation, remove stopwords
        tokens = [t.lower() for t in tokens]
        tokens = [t for t in tokens if t.isalpha() and t not in stopwords.words('english')]
        words.append(tokens)
        counter += 1
    words = [item for sublist in words for item in sublist]
    unique_words = set(words)
    counted = Counter(words).most_common()
    counted = [t for t in counted if t[0] in unique_words]
    word_dict = dict(counted)
    print("Top 40 terms: " + str(list(word_dict.items())[:40]))


# web crawler method
def crawler(starter_url):
    r = requests.get(starter_url)
    data = r.text
    soup = BeautifulSoup(data, features="html.parser")

    with open('urls.txt', 'w') as f:
        for link in soup.find_all('a'):
            link_str = str(link.get('href'))
            if 'Splatoon' in link_str or 'splatoon' in link_str:
                if 'wiki' in link_str:
                    continue
                if '&' in link_str:
                    i = link_str.find('&')
                    link_str = link_str[:i]
                if link_str.startswith('http') and 'google' not in link_str:
                    f.write(link_str + '\n')

    # get list of 15 relevant urls
    counter = 0
    url_list = []
    with open('urls.txt', 'r') as f:
        for line in f:
            if counter > 14:
                break
            print(line)
            url_list.append(line)
            counter += 1

    # save text of each url to a file
    counter = 1
    for url in url_list:
        req = Request(
            url=url,
            headers={'User-Agent': 'Mozilla/5.0'})
        html = urlopen(req)
        soup = BeautifulSoup(html, features="html.parser")
        data = soup.findAll(text=True)
        result = filter(visible, data)
        temp_list = list(result)  # list from filter
        temp_str = ' '.join(temp_list)
        with open('%d_A.txt' % counter, 'w') as f:
            f.write(temp_str)
        counter += 1

    # clean up text in files
    counter = 1
    while counter <= 15:
        with open('%d_A.txt' % counter, 'r') as f:
            data = f.read().replace('\n', '')
            data = data.replace('\t', '')
            data = data.strip()
        with open('%d_B.txt' % counter, 'w') as f:
            f.write(data)
        counter += 1

    # output sentences to files
    counter = 1
    while counter <= 15:
        with open('%d_B.txt' % counter, 'r') as f:
            data = f.read()
        sentences = sent_tokenize(data)
        with open('%d_C.txt' % counter, 'w') as f:
            f.writelines(sentences)
        counter += 1


# helper method
def visible(element):
    if element.parent.name in ['style', 'script', '[document]', 'head', 'title']:
        return False
    elif re.match('<!--.*-->', str(element.encode('utf-8'))):
        return False
    return True


def main():
    crawler("https://en.wikipedia.org/wiki/Splatoon")
    preprocess()
    top10 = ["nintendo", "game", "splatoon", "wii,", "online", "play", "switch", "mario", "anime", "fun"]
    knowledge_base = {"nintendo": [], "game": [], "splatoon": [], "wii": [], "online": [],
                      "play": [], "switch": [], "mario": [], "anime": [], "fun": []}
    # build knowledge base
    for term in knowledge_base:
        counter = 1
        while counter <= 15:
            with open('%d_C.txt' % counter, 'r') as f:
                sentences = f.readlines()
            for sentence in sentences:
                if term in sentence:
                    knowledge_base[term] += [sentence]
            counter += 1

    # save knowledge base dictionary to a pickle file
    pickle.dump(knowledge_base, open('knowledge_base.p', 'wb'))


if __name__ == '__main__':
    main()
