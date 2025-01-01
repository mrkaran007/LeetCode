//  https://leetcode.com/problems/maximum-score-after-splitting-a-string/?envType=daily-question&envId=2025-01-01

//  Q.>  1422. Maximum Score After Splitting a String

/**
Given a string s of zeros and ones, return the maximum score after splitting the string into two non-empty substrings (i.e. left substring and right substring).

The score after splitting a string is the number of zeros in the left substring plus the number of ones in the right substring.

 

Example 1:

Input: s = "011101"
Output: 5 
Explanation: 
All possible ways of splitting s into two non-empty substrings are:
left = "0" and right = "11101", score = 1 + 4 = 5 
left = "01" and right = "1101", score = 1 + 3 = 4 
left = "011" and right = "101", score = 1 + 2 = 3 
left = "0111" and right = "01", score = 1 + 1 = 2 
left = "01110" and right = "1", score = 2 + 1 = 3
Example 2:

Input: s = "00111"
Output: 5
Explanation: When left = "00" and right = "111", we get the maximum score = 2 + 3 = 5
Example 3:

Input: s = "1111"
Output: 3
 

Constraints:

2 <= s.length <= 500
The string s consists of characters '0' and '1' only.
*/



class Solution {
    public int maxScore(String s) {
        // Step 1: Count total 1s in the string
        int totalOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') {
                totalOnes++;
            }
        }

        // Step 2: Traverse the string and calculate the score
        int leftZeros = 0;
        int maxScore = 0;
        int rightOnes = totalOnes; // Initially, all 1s are on the right

        // Iterate until the second last character to ensure both substrings are non-empty
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                leftZeros++; // Increment left 0 count
            } else {
                rightOnes--; // Decrement right 1 count
            }

            // Calculate the current score
            int currentScore = leftZeros + rightOnes;
            maxScore = Math.max(maxScore, currentScore); // Update max score if needed
        }

        return maxScore;
    }
}
