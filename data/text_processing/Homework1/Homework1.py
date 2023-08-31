# Alexis Jennings
# aej190000
# CS 4395.001
# Homework 1

import sys
import pathlib
import re
import pickle


# Class that holds employee information
class Person:
    def __init__(self, last, first, mi, i_d, phone):
        self.last = last
        self.first = first
        self.mi = mi
        self.i_d = i_d
        self.phone = phone

    # Display the information of the employee
    def display(self):
        print("Employee ID: " + self.i_d)
        print("        " + self.first + " " + self.mi + " " + self.last)
        print("        " + self.phone + "\n")


def process(text_in):  # Process the text file
    data = []
    # Puts each piece of the lines separated by commas in its own list
    for i in text_in:
        temp = i.split(',')
        data.append(temp)

    for i in range(len(data)):
        # Capitalize first and last names
        data[i][0] = data[i][0].capitalize()
        data[i][1] = data[i][1].capitalize()

        # If they have a middle name, capitalize the initial
        if data[i][2]:
            data[i][2] = data[i][2].capitalize()
        else:
            data[i][2] = "X"

        # Create a regex for the ID
        regex_id = re.compile("^[A-Za-z]{2}[0-9]{4}$")

        # Check if ID is valid, if not then have them enter a new one
        if regex_id.match(data[i][3]) is None:
            print("Invalid ID: " + data[i][3])
            print("ID should be two letters followed by four digits")
            data[i][3] = input("Please enter a valid id: ")

        # Create a regex for the phone number
        regex_phone = re.compile(r'^\d{3}-\d{3}-\d{4}$')

        # Check if phone number is valid, if not then have them enter a new one
        if regex_phone.match(data[i][4]) is None:
            print("Invalid phone number: " + data[i][4])
            print("Enter phone number in form 123-456-7890")
            data[i][4] = input("Please enter a valid phone number: ")

    # Create dictionary of employees
    persons = {}
    for i in range(len(data)):
        person = Person(data[i][0], data[i][1], data[i][2], data[i][3], data[i][4])
        persons[data[i][3]] = person

    # Return dictionary of employees
    return persons


def main():
    # Make data file path cross-platform
    if __name__ == '__main__':
        if len(sys.argv) < 2:
            print('Please enter a filename as a system arg')
        else:
            fp = sys.argv[1]
            with open(pathlib.Path.cwd().joinpath(fp), 'r') as f:
                fin = f.readlines()
                # Get rid of first line
                fin = fin[1:]

    # Process the data file
    persons = process(fin)

    # Save to pickle file
    pickle.dump(persons, open('persons.p', 'wb'))  # write binary

    # Read pickle file
    persons_in = pickle.load(open('persons.p', 'rb'))  # read binary

    # Print the employee dictionary
    print("\nEmployee list:\n")
    for v in persons.values():
        v.display()


if __name__ == "__main__":
    main()
