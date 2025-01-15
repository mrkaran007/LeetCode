//  https://leetcode.com/problems/minimize-xor/?envType=daily-question&envId=2025-01-15

//  Q.> 2429. Minimize XOR

/**
Given two positive integers num1 and num2, find the positive integer x such that:

x has the same number of set bits as num2, and
The value x XOR num1 is minimal.
Note that XOR is the bitwise XOR operation.

Return the integer x. The test cases are generated such that x is uniquely determined.

The number of set bits of an integer is the number of 1's in its binary representation.

 

Example 1:

Input: num1 = 3, num2 = 5
Output: 3
Explanation:
The binary representations of num1 and num2 are 0011 and 0101, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 3 = 0 is minimal.
Example 2:

Input: num1 = 1, num2 = 12
Output: 3
Explanation:
The binary representations of num1 and num2 are 0001 and 1100, respectively.
The integer 3 has the same number of set bits as num2, and the value 3 XOR 1 = 2 is minimal.
*/


class Solution {
  public int minimizeXor(int num1, int num2) {
    final int kMaxBit = 30;
    int bits = Integer.bitCount(num2);
    // Can turn off all the bits in `num1`.
    if (Integer.bitCount(num1) == bits)
      return num1;

    int ans = 0;

    // Turn off the MSB if we have `bits` quota.
    for (int i = kMaxBit - 1; i >= 0; --i)
      if ((num1 >> i & 1) == 1) {
        ans |= 1 << i;
        if (--bits == 0)
          return ans;
      }

    // Turn on the LSB if we still have `bits`.
    for (int i = 0; i < kMaxBit; ++i)
      if ((num1 >> i & 1) == 0) {
        ans |= 1 << i;
        if (--bits == 0)
          return ans;
      }

    return ans;
  }
}
