// https://leetcode.com/problems/find-the-power-of-k-size-subarrays-i/?envType=daily-question&envId=2024-11-16

// Q.> 3254. Find the Power of K-Size Subarrays I

/**
 * You are given an array of integers nums of length n and a positive integer k.

The power of an array is defined as:

Its maximum element if all of its elements are consecutive and sorted in ascending order.
-1 otherwise.
You need to find the power of all 
subarrays
 of nums of size k.

Return an integer array results of size n - k + 1, where results[i] is the power of nums[i..(i + k - 1)].

 

Example 1:

Input: nums = [1,2,3,4,3,2,5], k = 3

Output: [3,4,-1,-1,-1]

Explanation:

There are 5 subarrays of nums of size 3:

[1, 2, 3] with the maximum element 3.
[2, 3, 4] with the maximum element 4.
[3, 4, 3] whose elements are not consecutive.
[4, 3, 2] whose elements are not sorted.
[3, 2, 5] whose elements are not consecutive.
Example 2:

Input: nums = [2,2,2,2,2], k = 4

Output: [-1,-1]

Example 3:

Input: nums = [3,2,3,2,3,2], k = 2

Output: [-1,3,-1,3,-1]

 

Constraints:

1 <= n == nums.length <= 500
1 <= nums[i] <= 105
1 <= k <= n
 */


 class Solution {
    private boolean sortedAndConsecutive(int[] arr, int start, int end){
        for(int i = start; i < end; i++){
            if(arr[i] >= arr[i+1] || (arr[i + 1] != arr[i] + 1)){
                return false;
            }
        }
        return true;
    }
    public int[] resultsArray(int[] nums, int k) {
        int n = nums.length;
        int rSize = n - k + 1;  // Size of the result array
        int[] result = new int[rSize];

        int i = 0;
        while(i < n){
            int j = i + k - 1;
            if(j < n && sortedAndConsecutive(nums, i, j)){
                result[i] = nums[j];    // Last/Maximum element of the valid subarray
            }else{
                if(j >= n){
                    break;
                }
                result[i] = -1; // Mark as invalid
            }
            i++;
        }
        return result;
    }
}

// Note:
// Time Complexity: 
// O(n*k) where n is the size of the input array and k is the size of the
// Space Complexity: 
// O(n)