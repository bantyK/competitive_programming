from typing import *


# 1640 https://leetcode.com/problems/check-array-formation-through-concatenation/
class Solution(object):

    # Shorter version
    def canFormArray(self, arr: List[int], pieces: List[List[int]]) -> bool:
        map = {x[0]: x for x in pieces}
        res = []
        for num in arr:
            res += map.get(num, [])

        return res == arr

    def canFormArray1(self, arr: List[int], pieces: List[List[int]]) -> bool:
        parents_map = {}

        for piece in pieces:
            parents_map[piece[0]] = piece  # make the first element of the piece as the parent of that group
        i = 0
        while i < len(arr):
            key = arr[i]
            if key not in parents_map:
                return False
            else:
                values = parents_map[key]
                if len(values) > 1:
                    if i + len(values) > len(arr):
                        return False

                    j = i
                    k = 0
                    while k < len(values):
                        if arr[j] != values[k]:
                            return False
                        else:
                            k += 1
                            j += 1
                    i = j
                else:
                    i += 1
        return True


obj = Solution()
# print(obj.canFormArray([85], [[85]]))
# print(obj.canFormArray([15, 81], [[88], [15]]))
# print(obj.canFormArray([91, 4, 64, 78], [[78], [4, 64], [91]]))
# print(obj.canFormArray([49, 18, 16], [[16, 18, 49]]))
print(obj.canFormArray([49, 18, 16, 12, 14, 16, 17], [[17], [49, 18, 16], [12, 14, 16]]))
