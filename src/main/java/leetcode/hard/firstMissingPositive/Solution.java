package leetcode.hard.firstMissingPositive;
/*
https://leetcode.com/problems/first-missing-positive/
 */


class Solution {

    int firstMissingPositive(int[] nums) {

        if (nums.length == 0) {
            return 1;
        }

        SelfSortNonDuplicateLinkedList positiveNumberStorage = new SelfSortNonDuplicateLinkedList();
        int smallestNumberInInput = nums[0];
        boolean firstPositiveNumberSpotted = false;
        int largestNumberInInput = nums[0];

        for (int num : nums) {
            if (num > 0) {
                positiveNumberStorage.addToList(num);
                if (!firstPositiveNumberSpotted) {
                    firstPositiveNumberSpotted = true;
                    smallestNumberInInput = num;
                    largestNumberInInput = num;
                } else {
                    if (num < smallestNumberInInput) {
                        smallestNumberInInput = num;
                    }
                    if (num > largestNumberInInput) {
                        largestNumberInInput = num;
                    }
                }
            }
        }

        if (!firstPositiveNumberSpotted) {
            return 1;
        }

        if (smallestNumberInInput > 1) {
            return 1;
        }

        Node positiveNumber = positiveNumberStorage.head;

        while (positiveNumber != null) {

            if (positiveNumber.getValue() == (smallestNumberInInput)) {
                positiveNumber = positiveNumber.next;
                ++smallestNumberInInput;
            } else {
                return smallestNumberInInput;
            }
        }
        return smallestNumberInInput;


    }


    class SelfSortNonDuplicateLinkedList {
        private Node head;

        void addToList(int value) {
            if (head == null) {
                head = new Node(value);
                return;
            }

            if (value == head.getValue()) {
                return;
            }

            if (value < head.getValue()) {
                Node newNode = new Node(value);
                newNode.next = head;
                head = newNode;
                return;
            }

            Node tempNode = head;
            while (true) {
                if (!tempNode.hasNext()) {
                    tempNode.next = new Node(value);
                    return;
                }
                if (tempNode.next.getValue() == value) {
                    return;
                }
                if (tempNode.next.getValue() < value) {
                    tempNode = tempNode.next;
                    continue;
                }
                Node newNode = new Node(value);
                newNode.next = tempNode.next;
                tempNode.next = newNode;
                return;

            }
        }

    }

    class Node {
        private int value;
        private Node next;

        Node(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }

        boolean hasNext() {
            return next != null;
        }
    }
}
