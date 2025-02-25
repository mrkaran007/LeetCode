//  https://leetcode.com/problems/number-of-sub-arrays-with-odd-sum/?envType=daily-question&envId=2025-02-25

//  Q.> 1524. Number of Sub-arrays With Odd Sum


/**
Given an array of integers arr, return the number of subarrays with an odd sum.

Since the answer can be very large, return it modulo 109 + 7.

 

Example 1:

Input: arr = [1,3,5]
Output: 4
Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
All sub-arrays sum are [1,4,9,3,8,5].
Odd sums are [1,9,3,5] so the answer is 4.
Example 2:

Input: arr = [2,4,6]
Output: 0
Explanation: All subarrays are [[2],[2,4],[2,4,6],[4],[4,6],[6]]
All sub-arrays sum are [2,6,12,4,10,6].
All sub-arrays have even sum and the answer is 0.
Example 3:

Input: arr = [1,2,3,4,5,6,7]
Output: 16
 

Constraints:

1 <= arr.length <= 105
1 <= arr[i] <= 100
*/



class Solution {

    // Method to calculate the number of subarrays with odd sum
    public int numOfSubarrays(int[] arr) {
        // Initialize the modulus value as per the problem statement
        final int MOD = 1000000007;
      
        // Counter array to track even and odd sums, where index 0 is for even and index 1 is for odd
        int[] count = {1, 0};
      
        // Variable to store the final answer
        int answer = 0;
      
        // Variable to store the current sum
        int sum = 0;
      
        // Iterate through each element in the array
        for (int num : arr) {
            // Add the current element's value to the cumulative sum
            sum += num;
          
            // If the cumulative sum is odd, add the count of previous even sums to the answer.
            // If the cumulative sum is even, add the count of previous odd sums to the answer.
            // Then, take the modulo to handle large numbers
            answer = (answer + count[1 - (sum & 1)]) % MOD;
          
            // Increment the count of current parity (even/odd) of sum
            ++count[sum & 1];
        }
      
        // Return the final answer
        return answer;
    }
}
