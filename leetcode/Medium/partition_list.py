# 86 https://leetcode.com/problems/partition-list/

class ListNode:
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next


class Solution:
    def partition(self, head: ListNode, x: int) -> ListNode:
        if not head or not head.next:
            return head

        node = head
        prev_left = head
        while node and node.val < x:
            prev_left = node
            node = node.next

        if not node or not node.next:
            return head

        left = node
        prev_right = left
        right = prev_right.next

        while right:
            while right and right.val >= x:
                prev_right = right
                right = right.next

            if not right:
                return head

            prev_right.next = right.next
            right.next = left

            if prev_left != left:
                prev_left.next = right

            if left == head:
                head = right

            prev_left = right
            left = prev_left.next

            right = prev_right.next

        return head

    def partition_2(self, head: ListNode, x: int) -> ListNode:
        bigger, smaller = ListNode(1000), ListNode(-1000)
        node1, node2 = bigger, smaller

        node = head
        while node:
            if node.val < x:
                node2.next = node
                node2 = node2.next
            else:
                node1.next = node
                node1 = node1.next
            node = node.next

        node2.next = bigger.next
        node1.next = None

        return smaller.next
