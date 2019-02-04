# PoemGenerator

This program generates a random poem according to rules specified in a text file.

## The Rules

1. To the left of the colon is the name of the rule.

2. To the right of the colon is the rule definition which can consist of words, keywords
and references to other rules.

3. A reference to another rule is marked with angle brackets, for example &lt;NOUN&gt;.
Rules can reference themselves, making them recursive.

4. Keywords are marked with $. There are two keywords: LINEBREAK and END. LINEBREAK
adds a line break to the output, END marks that the end of a line has been reached.
This means that a line can only end with an adjective, a noun or a verb.

5. A grouping of elements separated by | means that one of those elements should be
selected at random.

6. Anything else that is plain text can be considered a word, for example murky or
heart.

## Usage

The source code for the program is in the file src/PoemGenerator.java 

The compiled code is in the file bin/PoemGenerator.class

In order to run the program from the bin folder use the command "java PoemGenerator <Path to Filename>.txt". 
  
To run the test file provided in the requirements use the command "java PoemGenerator ../testFiles/GrammarRules.txt".

