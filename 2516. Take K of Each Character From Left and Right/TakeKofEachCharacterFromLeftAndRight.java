//  https://leetcode.com/problems/take-k-of-each-character-from-left-and-right/description/?envType=daily-question&envId=2024-11-20

// Q.> 2516. Take K of Each Character From Left and Right
/**
 * You are given a string s consisting of the characters 'a', 'b', and 'c' and a non-negative integer k. Each minute, you may take either the leftmost character of s, or the rightmost character of s.

Return the minimum number of minutes needed for you to take at least k of each character, or return -1 if it is not possible to take k of each character.

 

Example 1:

Input: s = "aabaaaacaabc", k = 2
Output: 8
Explanation: 
Take three characters from the left of s. You now have two 'a' characters, and one 'b' character.
Take five characters from the right of s. You now have four 'a' characters, two 'b' characters, and two 'c' characters.
A total of 3 + 5 = 8 minutes is needed.
It can be proven that 8 is the minimum number of minutes needed.
Example 2:

Input: s = "a", k = 1
Output: -1
Explanation: It is not possible to take one 'b' or 'c' so return -1.
 

Constraints:

1 <= s.length <= 105
s consists of only the letters 'a', 'b', and 'c'.
0 <= k <= s.length
 */


 class Solution {
    public int takeCharacters(String s, int k) {
      final int n = s.length();
      int ans = n;
      int[] count = new int[3]; // Frequency array for 'a', 'b', 'c'
  
      // Count the frequency of each character in the string
      for (final char c : s.toCharArray())
        ++count[c - 'a'];
  
      // If any character has fewer than k occurrences, it's impossible
      if (count[0] < k || count[1] < k || count[2] < k)
        return -1;
  
      // Sliding window to find the maximum removable contiguous substring
      for (int l = 0, r = 0; r < n; ++r) {
        --count[s.charAt(r) - 'a']; // Decrease the count of current character
        // Shrink the window if any character count goes below k
        while (count[s.charAt(r) - 'a'] < k)
          ++count[s.charAt(l++) - 'a']; // Restore count for the left pointer
        // Update the minimum number of characters to take from ends
        ans = Math.min(ans, n - (r - l + 1));
      }
  
      return ans;
    }
  }
  