package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

//@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        String s = ("музаfазум");
        System.out.println(palindromeStringBuilder(s));

        TreeNode root = new TreeNode(6);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(5);

        root.left = node1;
        root.right = node2;

        node1.left = node3;
        node1.right = node4;

        node2.left = node5;
        node2.right = node6;

        node3.left = null;
        node3.right = null;

        node4.left = node7;
        node4.right = node8;
        System.out.println(lowestCommonAncestor(root, node1, node4).val);
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
}

