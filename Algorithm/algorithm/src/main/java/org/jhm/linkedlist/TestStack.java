package org.jhm.linkedlist;

import java.util.Stack;

/**
 * @Description:
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/24 10:18 下午
 * @Version: 1.0.0
 */
public class TestStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }
}
