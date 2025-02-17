//  https://leetcode.com/problems/letter-tile-possibilities/?envType=daily-question&envId=2025-02-17

//  Q.> 1079. Letter Tile Possibilities

/**
You have n  tiles, where each tile has one letter tiles[i] printed on it.

Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.

 

Example 1:

Input: tiles = "AAB"
Output: 8
Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
Example 2:

Input: tiles = "AAABBC"
Output: 188
Example 3:

Input: tiles = "V"
Output: 1
 

Constraints:

1 <= tiles.length <= 7
tiles consists of uppercase English letters.
*/


class Solution {
  public int numTilePossibilities(String tiles) {
    int[] count = new int[26];

    for (final char t : tiles.toCharArray())
      ++count[t - 'A'];

    return dfs(count);
  }

  private int dfs(int[] count) {
    int possibleSequences = 0;

    for (int i = 0; i < 26; ++i) {
      if (count[i] == 0)
        continue;
      // Put c in the current position. We only care about the number of possible
      // sequences of letters but don't care about the actual combination.
      --count[i];
      possibleSequences += 1 + dfs(count);
      ++count[i];
    }

    return possibleSequences;
  }
}
