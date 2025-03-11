package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.stream.Stream;

//@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);

        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(4);
        TreeNode node8 = new TreeNode(4);
        TreeNode node9 = new TreeNode(4);
        TreeNode node10 = new TreeNode(4);
        TreeNode node11 = new TreeNode(4);
        TreeNode node12 = new TreeNode(4);
        TreeNode node13 = new TreeNode(5);
        TreeNode node14 = new TreeNode(5);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = node7;
        node3.right = node8;

        node4.left = node9;
        node4.right = node10;

        node5.left = node11;
        node5.right = node12;

        node7.left = node13;
        node7.right = node14;

        System.out.println(isBalanced(root));
    }


    private static boolean palindrome(String word) {
        if(word == null){
            throw new NullPointerException();
        }
        char[] wordArray = toArrayChar(word);
        int count = 0;

        for (int i = 0; i < wordArray.length/2+1; i++) {
            if (wordArray[i] == wordArray[wordArray.length - i - 1] && i <= wordArray.length/2) {
                count++;
            }
        }
        if (count == wordArray.length / 2 + 1) {
            return true;
        }
        return false;
    }

    private static boolean palindromeStringBuilder(String word) {
        if(word == null){
            throw new NullPointerException();
        }
        StringBuilder str = new StringBuilder(toString(word));
        if (str.length() % 2 ==0) {
            String s = str.reverse().substring(0,str.length()/2);
            String t = str.substring(0,str.length()/2);
            return s.equals(t);
        }
        else {
            String s = str.reverse().substring(0,str.length()/2+1);
            String t = str.substring(0,str.length()/2+1);
            return s.equals(t);
        }
    }

    private static char[] toArrayChar(String word) {
        return word.toLowerCase().replaceAll("\\p{Punct}", "").replace(" ", "").toCharArray();
    }

    private static String toString(String word) {
        return word.toLowerCase().replaceAll("\\p{Punct}", "").replace(" ", "");
    }


    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if((root.val < p.val || root.val < q.val) && (root.val > p.val || root.val > q.val)) {
            return root;
        }
        if((root.val < p.val && root.val < q.val)){
            return lowestCommonAncestor(root.right, p, q);
        }

        if((root.val > p.val && root.val > q.val)){
            return lowestCommonAncestor(root.left, p, q);
        }

        return root;
    }

    private static boolean isBalanced(TreeNode root) {
        System.out.println(height(root));
        return height(root) >= 0;
    }

    private static int height(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private static boolean isAnagram(String word1, String word2) {
        Set<Character> set1 = new HashSet<>();

        for (char c : word1.toCharArray()) {
            set1.add(c);
        }
        int sizeSet1 = set1.size();
        for (char c : word2.toCharArray()) {
            set1.add(c);
        }
        int sizeSet2 = set1.size();

        if (sizeSet1 != sizeSet2) {
            return false;
        }
        return true;
    }
}

