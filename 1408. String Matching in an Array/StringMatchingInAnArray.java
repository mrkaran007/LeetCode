//  https://leetcode.com/problems/string-matching-in-an-array/description/?envType=daily-question&envId=2025-01-07

//  Q.>  1408. String Matching in an Array

/**
Given an array of string words, return all strings in words that is a substring of another word. You can return the answer in any order.

A substring is a contiguous sequence of characters within a string

 

Example 1:

Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
Example 2:

Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
Example 3:

Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.
 

Constraints:

1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.
*/


class Solution {
  public List<String> stringMatching(String[] words) {
    List<String> ans = new ArrayList<>();
    for (final String a : words)
      for (final String b : words)
        if (a.length() < b.length() && b.indexOf(a) != -1) {
          ans.add(a);
          break;
        }
    return ans;
  }
}
