"""
#32 https://leetcode.com/problems/longest-valid-parentheses/

After going through all the characters in the string, the invalid characters which cannot be matched will
remain in the stack.

These points basically divides the string into set of indices which are matching. Just count the matching indices.
"""


class Solution:
    def longestValidParentheses(self, s: str) -> int:
        stack = [(-1, ')')]

        longest = 0

        for i in range(0, len(s)):
            if s[i] == '(':
                stack.append((i, '('))
            else:
                if stack[-1][1] == '(':
                    # stack is not empty and last item in stack, is an opening bracket, pop the stack
                    stack.pop()
                    longest = max(longest, i - stack[-1][0])
                else:
                    stack.append((i, ')'))

        # print(stack)

        return longest


obj = Solution()
longest = obj.longestValidParentheses(")()())")
print(longest)
