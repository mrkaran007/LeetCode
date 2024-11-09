//  https://leetcode.com/problems/minimum-array-end/description/?envType=daily-question&envId=2024-11-09

// Q.> 3133. Minimum Array End
/**
 * You are given two integers n and x. You have to construct an array of
 * positive integers nums of size n where for every 0 <= i < n - 1, nums[i + 1]
 * is greater than nums[i], and the result of the bitwise AND operation between
 * all elements of nums is x.
 * 
 * Return the minimum possible value of nums[n - 1].
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: n = 3, x = 4
 * 
 * Output: 6
 * 
 * Explanation:
 * 
 * nums can be [4,5,6] and its last element is 6.
 * 
 * Example 2:
 * 
 * Input: n = 2, x = 7
 * 
 * Output: 15
 * 
 * Explanation:
 * 
 * nums can be [7,15] and its last element is 15.
 * 
 * 
 * 
 * Constraints:
 * 
 * 1 <= n, x <= 108
 */


        /*  Best Solution */ 
// For detail explanation refer--> https://youtu.be/rChLZzzggjo?si=KOfmKn_NNcLz-IJu
 class Solution {
    public long minEnd(int n, int x) {
        long ans = x;
        for(int i = 1; i < n; i++){
            // you think ans may be (ans + 1), but it not then it need to be "OR" for next minimum ans num
            ans = (ans + 1) | x;
        }       
        return ans;
    }
}

        /*  Not Suitable Solution (Brute Force, Give TLE) */

// class Solution {
//     /**
//      * Finds the minimum integer 'ans' such that `x & ans == x` is satisfied
//      * after performing the AND operation 'n-1' times. For n == 1, returns x
//      * directly.
//      *
//      * Note: This implementation uses a brute-force approach that will lead to
//      * performance issues (TLE) for large input sizes.
//      *
//      * @param n The number of times to repeat the process of finding `ans` that
//      *          satisfies `x & ans == x`.
//      * @param x The target integer with which the bitwise AND operation is compared.
//      * @return The minimum value that satisfies `x & ans == x`.
//      */
//     public long minEnd(int n, int x) {
//         // If n is 1, we only need to return x itself as the minimum satisfying number.
//         if (n == 1) {
//             return x;
//         }

//         // Start from the next integer after x to find the minimum ans such that `x &
//         // ans == x`.
//         long ans = x + 1;
//         int i = 0; // Keeps track of how many times we've found a valid `ans`
//         long j = ans; // Current candidate for ans in each inner loop iteration

//         // Repeat the process `n-1` times to find valid values for `ans`.
//         while (i < n - 1) {
//             while (j >= 0) { // Search through values starting from current j
//                 long temp = x;

//                 // Perform bitwise AND with candidate `j` to check if it satisfies `x & j == x`
//                 temp &= j;

//                 // If `temp == x`, we've found a valid `ans`
//                 if (temp == x) {
//                     ans = j; // Set `ans` to current candidate
//                     j = -11; // Break the inner loop by setting `j` to an invalid value
//                 }
//                 j++; // Increment `j` to check the next candidate
//             }

//             // Reset `j` to start from `ans + 1` for the next iteration
//             j = ans + 1;
//             i++; // Increment `i` to indicate one more valid `ans` found
//         }

//         // Return the final value of `ans` found
//         return ans;
//     }
// }
