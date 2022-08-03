package org.jhm.linkedlist;

/**
 * @Description:
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/26 11:02 上午
 * @Version: 1.0.0
 */
public class Joseph {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.createJosephCircle(25);
        circleSingleLinkedList.showBoy();

        circleSingleLinkedList.countBoy(1, 2, 25);
    }
}

class CircleSingleLinkedList {

    /**
     * first node, has no number
     */
    private Boy first = null;

    /**
     * @Description: create joseph circle by nums
     * @Param: nums
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/26 11:25 上午
     */
    public void createJosephCircle(int nums) {
        if (nums < 1) {
            System.out.println("nums's data is not currect");
            return;
        }
        // curBoy is a helper node
        Boy curBoy = null;
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            if (i == 1) {
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }

        }
    }

    /**
     * @Description: show all of joseph node
     * @Param:
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/26 11:22 上午
     */
    public void showBoy() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Boy cur = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", cur.getNo());
            if (cur.getNext() == first) {
                break;
            }
            cur = cur.getNext();
        }
    }

    /**
     * @Description: get out of nodes by countNum step,start with startNo within nums
     * @Param: startNo the start no
     * @Param: countNum step of length
     * @Param: nums joseph length
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/26 1:25 下午
     */
    public void countBoy(int startNo, int countNum, int nums) {
        if (startNo < 1 || countNum < 1 || first == null) {
            System.out.println("the init params is not correct");
        }
        // helper is before node of first
        Boy helper = first;
        while (helper.getNext() != first) {
            helper = helper.getNext();
        }
        // move helper node and first node with startNo
        while (startNo - 1 > 0) {
            first = first.getNext();
            helper = helper.getNext();
            startNo--;
        }

        while (true) {
            if (first == helper) {
                System.out.println("only one node in circle,close function");
                break;
            }
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("the outer node is: %d \n", first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("the lasted node is %d \n", first.getNo());

    }
}


/**
 * @Description: josephe node
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/26 11:04 上午
 * @Version 1.0.0
 */
class Boy {

    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
