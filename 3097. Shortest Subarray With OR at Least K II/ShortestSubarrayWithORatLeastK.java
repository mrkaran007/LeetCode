//  https://leetcode.com/problems/shortest-subarray-with-or-at-least-k-ii/description/?envType=daily-question&envId=2024-11-10

// Q.> 3097. Shortest Subarray With OR at Least K II
/**
 * You are given an array nums of non-negative integers and an integer k.

An array is called special if the bitwise OR of all of its elements is at least k.

Return the length of the shortest special non-empty 
subarray
 of nums, or return -1 if no special subarray exists.

 

Example 1:

Input: nums = [1,2,3], k = 2

Output: 1

Explanation:

The subarray [3] has OR value of 3. Hence, we return 1.

Example 2:

Input: nums = [2,1,8], k = 10

Output: 3

Explanation:

The subarray [2,1,8] has OR value of 11. Hence, we return 3.

Example 3:

Input: nums = [1,2], k = 0

Output: 1

Explanation:

The subarray [1] has OR value of 1. Hence, we return 1.

 

Constraints:

1 <= nums.length <= 2 * 105
0 <= nums[i] <= 109
0 <= k <= 109
 */

        /*  suitable solution */
 class Solution {
    // Method to update the bit count for the given number `num` by `delta` (can be +1 or -1)
    private void updateBits(int[] count, int num, int delta) {
        for (int i = 0; i < 32; i++) {
            // Check if the i-th bit of num is set
            if ((num & (1 << i)) != 0) {
                count[i] += delta; // Update the count for this bit
            }
        }
    }

    // Method to compute the OR result from the bit count array
    private int getVal(int[] count) {
        int orResult = 0;
        for (int i = 0; i < 32; i++) {
            // If the count for this bit is greater than 0, set this bit in the result
            if (count[i] > 0) {
                orResult |= (1 << i);
            }
        }
        return orResult; // Return the computed OR value
    }

    // Main method to find the minimum length of a subarray with an OR value >= k
    public int minimumSubarrayLength(int[] nums, int k) {
        int[] count = new int[32]; // Array to keep track of the count of each bit (0-31)
        int start = 0, end = 0; // Pointers for the sliding window
        int minLength = Integer.MAX_VALUE; // Initialize minimum length to a large value

        // Iterate through the array with the end pointer
        while (end < nums.length) {
            updateBits(count, nums[end], 1); // Add the current number to the bit count

            // While the OR result of the current window meets or exceeds `k`
            while (start <= end && getVal(count) >= k) {
                // Update the minimum length of the subarray found
                minLength = Math.min(minLength, end - start + 1);
                // Remove the number at the start of the window from the bit count
                updateBits(count, nums[start], -1);
                start++; // Move the start pointer to the right
            }
            end++; // Move the end pointer to the right
        }

        // If no valid subarray was found, return -1; otherwise, return the minimum length
        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }
}



        /*  Brute force ( Not Suitable for large input size) */
//  class Solution {
//     public int minimumSubarrayLength(int[] nums, int k) {
//         int n = nums.length;
//         for(int num: nums){
//             if(num >= k){
//                 return 1;
//             }
//         }

//         // Brute force ( Not Suitable for large input size)
//         int count = 0;
//         boolean flag = false;
//         int feq = Integer.MAX_VALUE;
//         for(int i = 0; i < n - 1; i++){
//             int j = i + 1;
//             int atLeast = nums[i];
//             count = 1;
//             while(j < n){
//                 atLeast |= nums[j];
//                 count++;
//                 if(atLeast >= k){
//                     if(count < feq){
//                         feq = count;
//                     }
//                     flag = true;
//                     j = n + 1;
//                 }
//                 j++;
//             }
//         }
        
//         if(flag){
//             return feq;
//         }
//         return -1;
//     }
// }