package com.nicholaslocicero.portfolio;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This is a class for creating instances to generate random arrays of strings.
 * <p>
 * It includes a basic constructor for instantiating instances.  These instances have 3
 * methods that can generate a random array of strings from a base array of strings using
 * a random object both given as inputs.
 * <p>
 * Each instance can generate a random array with the strings duplicated or without the
 * strings duplicated.
 * <p>
 * The size of the generated random array can be specified.
 */
public class Generator {

  private static final String NULL_RNG_MESSAGE = "Random number generator must not be null.";
  private static final String NULL_WORDS_MESSAGE = "Array of words must not be null.";
  private static final String EMPTY_WORDS_MESSAGE = "Array of words must not be empty.";
  private static final String NEGATIVE_NUMBER_WORDS_MESSAGE = "Number of words to be selected must not be negative.";
  private static final String INSUFFICIENT_WORDS_MESSAGE = "Number of distinct words requested must not exceed number of words in pool.";

  private String[] words;
  private Random rng;

  /**
   * Creates an instance of the generator class.
   *
   * @param words An array of strings from which the random string arrays will be generated.
   * @param rng The type of random object used to create the random string array.
   * @throws NullPointerException Is thrown if either parameter is null.
   * @throws IllegalArgumentException Is thrown if words is empty.
   */
  public Generator(String[] words, Random rng)
      throws NullPointerException, IllegalArgumentException {
    if (rng == null) {
      throw new NullPointerException(NULL_RNG_MESSAGE);
    }
    if (words == null) {
      throw new NullPointerException(NULL_WORDS_MESSAGE);
    }
    if (words.length == 0) {
      throw new IllegalArgumentException(EMPTY_WORDS_MESSAGE);
    }
    Set<String> pool = new HashSet<>();
    for (String word : words) {
      word = word.toLowerCase();
      if (!pool.contains(word)) {
        pool.add(word);
      }
    }
    this.words = pool.toArray(new String[pool.size()]);
    this.rng = rng;
  }

  /**
   * Selects and returns a random word from the array of words to choose from.
   * @return Returns a random word from the array of random words.
   */
  public String next() {
    return words[rng.nextInt(words.length)];
  }

  /**
   * This method generates a random String array with or without duplicated strings in its
   * generated array.
   *
   * @param numWords Specifies the size of the randomly generated array of strings.
   * @param duplicatesAllowed Specifies if we will repeat words in the array of strings.
   * @return Returns an array of strings randomly generated by our rng object.
   * @throws NegativeArraySizeException We cannot generate an array of size less that 0.
   * @throws IllegalArgumentException We cannot generate an array without duplicates if
   *     the size of the generated array has more words than we have to fill the array.
   */
  public String[] next(int numWords, boolean duplicatesAllowed)
      throws NegativeArraySizeException, IllegalArgumentException {

    if (numWords < 0) {
      throw new NegativeArraySizeException(NEGATIVE_NUMBER_WORDS_MESSAGE);
    }

    if (!duplicatesAllowed && numWords > words.length) {
      throw new IllegalArgumentException(INSUFFICIENT_WORDS_MESSAGE);
    }

    List<String> selection = new LinkedList<>();

    while (selection.size() < numWords) {
      String pick = next();
      if (duplicatesAllowed || !selection.contains(pick)) {
        selection.add(pick);
      }
    }
    return selection.toArray(new String[selection.size()]);
  }

  /**
   * This method is used as default for generating a random array of strings with duplicates
   * allowed.
   *
   * @param numWords Specifies the size of the randomly generated array of strings.
   * @return Returns an array of strings randomly generated by our rng object.
   * @throws NegativeArraySizeException We cannot generate an array of size less that 0.
   */
  public String[] next(int numWords)
      throws NegativeArraySizeException {
    return next(numWords, true);
  }
}

