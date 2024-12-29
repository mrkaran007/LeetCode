//  https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/description/?envType=daily-question&envId=2024-12-29

//  Q.>  1639. Number of Ways to Form a Target String Given a Dictionary


/**
You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: words = ["acca","bbbb","caca"], target = "aba"
Output: 6
Explanation: There are 6 ways to form target.
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("caca")
"aba" -> index 0 ("acca"), index 1 ("bbbb"), index 3 ("acca")
"aba" -> index 0 ("acca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("acca")
"aba" -> index 1 ("caca"), index 2 ("bbbb"), index 3 ("caca")
Example 2:

Input: words = ["abba","baab"], target = "bab"
Output: 4
Explanation: There are 4 ways to form target.
"bab" -> index 0 ("baab"), index 1 ("baab"), index 2 ("abba")
"bab" -> index 0 ("baab"), index 1 ("baab"), index 3 ("baab")
"bab" -> index 0 ("baab"), index 2 ("baab"), index 3 ("baab")
"bab" -> index 1 ("abba"), index 2 ("baab"), index 3 ("baab")
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length <= 1000
All strings in words have the same length.
1 <= target.length <= 1000
words[i] and target contain only lowercase English letters.
*/




class Solution {
  public int numWays(String[] words, String target) {
    final int wordLength = words[0].length();
    Integer[][] mem = new Integer[target.length()][wordLength];
    // counts[j] := the count map of words[i][j], where 0 <= i < |words|
    int[][] counts = new int[wordLength][26];

    for (int i = 0; i < wordLength; ++i)
      for (final String word : words)
        ++counts[i][word.charAt(i) - 'a'];

    return numWays(target, 0, 0, counts, mem);
  }

  private static final int kMod = 1_000_000_007;

  // Returns the number of ways to form target[i..n) using word[j..n).
  private int numWays(final String target, int i, int j, int[][] counts, Integer[][] mem) {
    if (i == target.length())
      return 1;
    if (j == counts.length)
      return 0;
    if (mem[i][j] != null)
      return mem[i][j];
    return mem[i][j] = (int) ((numWays(target, i + 1, j + 1, counts, mem) *
                                   (long) (counts[j][target.charAt(i) - 'a']) +
                               numWays(target, i, j + 1, counts, mem)) %
                              kMod);
  }
}
