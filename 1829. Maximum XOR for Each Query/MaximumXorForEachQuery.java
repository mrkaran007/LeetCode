//  https://leetcode.com/problems/maximum-xor-for-each-query/?envType=daily-question&envId=2024-11-08

//  Q.> 1829. Maximum XOR for Each Query

/**
You are given a sorted array nums of n non-negative integers and an integer maximumBit. You want to perform the following query n times:

Find a non-negative integer k < 2maximumBit such that nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR k is maximized. k is the answer to the ith query.
Remove the last element from the current array nums.
Return an array answer, where answer[i] is the answer to the ith query.

 

Example 1:

Input: nums = [0,1,1,3], maximumBit = 2
Output: [0,3,2,3]
Explanation: The queries are answered as follows:
1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
4th query: nums = [0], k = 3 since 0 XOR 3 = 3.
Example 2:

Input: nums = [2,3,4,7], maximumBit = 3
Output: [5,2,6,5]
Explanation: The queries are answered as follows:
1st query: nums = [2,3,4,7], k = 5 since 2 XOR 3 XOR 4 XOR 7 XOR 5 = 7.
2nd query: nums = [2,3,4], k = 2 since 2 XOR 3 XOR 4 XOR 2 = 7.
3rd query: nums = [2,3], k = 6 since 2 XOR 3 XOR 6 = 7.
4th query: nums = [2], k = 5 since 2 XOR 5 = 7.
Example 3:

Input: nums = [0,1,2,2,5,7], maximumBit = 3
Output: [4,3,6,4,6,7]
 

Constraints:

nums.length == n
1 <= n <= 105
1 <= maximumBit <= 20
0 <= nums[i] < 2maximumBit
nums​​​ is sorted in ascending order.
*/


/*  Best Solution */
class Solution {
    public int[] getMaximumXor(int[] nums, int maximumBit) {
        // int mask = (int) Math.pow(2, maximumBit) - 1;
        /*  or  */
        int mask = (1 << maximumBit) - 1;  // Maximum number with all `maximumBit` bits set to 1

        // Calculate the cumulative XOR of all elements in nums
        int x = 0;
        for (int i = 0; i < nums.length; i++) {
            x = x ^ nums[i];
        }

        int[] ans = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            // This will give you the flipped value of XOR, i.e. best k
            int k = x ^ mask;   // Maximum XOR for current cumulative XOR
            ans[i] = k;     

            // Remove the last element in reverse order
            x = (x ^ nums[nums.length - i - 1]);
        }
        return ans;
    }
}


/*  Not Suitable for large inputs */

// class Solution {
//     public int[] getMaximumXor(int[] nums, int maximumBit) {
//         // Define the maximum possible value with given number of bits (maximumBit).
//         int last = (int) Math.pow(2, maximumBit);  // This is 2^maximumBit
//         int[] ans = new int[nums.length];

//         for (int i = 0; i < nums.length; i++) {
//             // Calculate the XOR of all elements up to the decreasing size
//             int x = nums[0];
//             for (int j = 1; j < nums.length - i; j++) {
//                 x = x ^ nums[j];
//             }

//             // Initialize variables to find the maximum XOR
//             int k = -1;                           // Variable to store the best value for max XOR
//             int max = Integer.MIN_VALUE;          // Stores the maximum XOR value
//             int newMax = Integer.MIN_VALUE;

//             // Loop to find the number `n` within the range [0, 2^maximumBit - 1] that maximizes the XOR with `x`
//             for (int n = 0; n < last; n++) {
//                 newMax = x ^ n;                   // Calculate XOR of current number with `n`
                
//                 // If the new XOR value is greater or equal to the previous max, update k and max
//                 if (newMax >= max) {
//                     k = n;                        // Update `k` to store the number producing max XOR
//                     max = newMax;                 // Update max to the current highest XOR value
//                 }
//             }

//             // Store the result for this prefix in the answer array
//             ans[i] = k;
//         }
//         return ans;
//     }
// }

