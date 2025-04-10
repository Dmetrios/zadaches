package com.example.demo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

//        System.out.println(Jwts.builder()
//                .setSubject("287756df-2c4f-4cd1-a031-213bfc3aae73")
//                .signWith(Keys.hmacShaKeyFor(
//                        Decoders.BASE64.decode("404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970")), SignatureAlgorithm.HS256)
//                .compact());
    }
    private static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            }
            else {
                map.put(num, 1);
            }
        }
        return map.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(0);
    }
    public static int findClosestToTen(int[] array) {
        int result = 0;
        int pro = Integer.MAX_VALUE;
        for (int i : array) {
            if (Math.abs(10 - i) < pro) {
                pro = Math.abs(10 - i);
                result = i;
            }
        }
        return result;
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

    private static boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> map1 = magazine
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));

        System.out.println(map1);

        Map<Character, Integer> map2 = ransomNote
                .chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
        System.out.println(map2);


        System.out.println(ransomNote);

        for(char c : ransomNote.toCharArray()) {
            Integer i = map2.get(c);
            Integer j = map1.get(c);
            if (i == null || j == null) {
                return false;
            }
            if(i > j)
            {
                return false;
            }

        }
        return true;
    }

    private static boolean can(String ransomNote, String magazine) {
        int[] chars = new int[128];

        for(char c : ransomNote.toCharArray()) {
            if(magazine.indexOf(c, chars[c]) != -1) {
                chars[c] = magazine.indexOf(c, chars[c])+1;
            }
            else {
                return false;
            }

        }
        return true;
    }
}

