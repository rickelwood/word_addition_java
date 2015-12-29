## Base 27 word addition in Java

word_addition_java is an attempt to improve the speed of the python word addition function. Running every English word against every other English word and checking to see if the result is also an English word was very slow in Python.

Using the same algorithm in Java as the Python version resulted in a significant increase in speed but was still very slow as we were comparing every result with every word in the dictionary for matches.

To fix this bottleneck, this version utilizes a hash table for instantaneous lookup of words changing the O(n²) runtime to O(n) resulting in a 2940% increase in speed over the basic add and search algorithm.
