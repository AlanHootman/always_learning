package org.jhm.linkedlist;

import java.util.Stack;

/**
 * @Description:
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/22 7:38 下午
 * @Version: 1.0.0
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode heroNode5 = new HeroNode(5, "鲁智深", "花和尚");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode2);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode5);

        System.out.println("翻转前：");
        singleLinkedList.list();

        System.out.println("逆序打印：");
        SingleLinkedList.reversePrint(singleLinkedList.getHead());

        SingleLinkedList.reverseList(singleLinkedList.getHead());
        System.out.println("翻转后：");
        singleLinkedList.list();

        System.out.println();
        singleLinkedList.update(new HeroNode(2, "江厚民", "杭州吴彦祖"));
        System.out.printf("返回节点个数为：%d", SingleLinkedList.getLength(singleLinkedList.getHead()));

        System.out.println();
        singleLinkedList.list();
        singleLinkedList.delete(new HeroNode(2, "江厚民", "杭州吴彦祖"));
        System.out.printf("返回节点个数为：%d", SingleLinkedList.getLength(singleLinkedList.getHead()));

        System.out.println();
        singleLinkedList.list();
        System.out.println();
        System.out.println(SingleLinkedList.getLastIndexHeroNode(singleLinkedList.getHead(), 1));
    }
}

/**
 * 构造单链表（尾部添加、按位置no添加）
 *
 * @version 1.0.0
 * @author: jianghm40441 <jianghm40441@hundsun.com>
 * @date: 2021/10/26 11:05 上午
 */
class SingleLinkedList {

    public HeroNode getHead() {
        return head;
    }

    private final HeroNode head = new HeroNode(0, "", "");

    /**
     * 链表添加时，从头结点开始寻址，找到next域为空时，将新结点地址添加到next域
     *
     * @param: heroNode
     * @return: void
     * @author: jianghm40441 <jianghm40441@hundsun.com>
     * @date: 2021/10/26 11:09 上午
     */
    public void add(HeroNode heroNode) {
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    /**
     * 按照节点no位置添加，若节点no存在，则不添加
     *
     * @param: heroNode
     * @return: void
     * @author: jianghm40441 <jianghm40441@hundsun.com>
     * @date: 2021/10/26 11:14 上午
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        int no = heroNode.no;
        // flag标志添加的编号是否存在，默认为false
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no > no) {
                break;
            } else if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (!flag) {
            heroNode.next = temp.next;
            temp.next = heroNode;
        } else {
            System.out.printf("准备插入的节点已经存在，编号%d", heroNode.no);
        }

    }

    /**
     * 按照no找到要更新的节点并更新
     *
     * @param: heroNode
     * @return: void
     * @author: jianghm40441 <jianghm40441@hundsun.com>
     * @date: 2021/10/26 11:23 上午
     */
    public void update(HeroNode heroNode) {
        // 表示是否找到该节点
        boolean flag = false;
        if (heroNode == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("找不到编号为：%d的节点", heroNode.no);
        }
    }

    public void delete(HeroNode heroNode) {
        // 找到待删除节点的前一个节点  因为这是单向链表
        HeroNode temp = head;
        // 表示是否找到要删除的节点
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("没有找到编号为%d的节点", heroNode.no);
        }
    }


    /**
     * @Description: 获取单链表节点的个数  如果有头结点  要去掉
     * @Param: heroNode 头结点
     * @Return: int
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/22 11:12 下午
     */
    public static int getLength(HeroNode heroNode) {
        if (heroNode == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = heroNode.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    /**
     * @Description: 找到链表中倒数第几个node
     * @Param: head 头结点
     * @Param: index 倒数第几个
     * @Return: com.jhm.linkedlist.HeroNode
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/22 11:25 下午
     */
    public static HeroNode getLastIndexHeroNode(HeroNode head, int index) {
        if (head == null) {
            return null;
        }
        int length = getLength(head);
        if (index <= 0 | index > length) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < length - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public void list() {
        if (head.next == null) {
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * @Description: 翻转链表
     * @Param: head 链表头结点
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/24 4:56 下午
     */
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }

        // 定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        // 指向当前节点的下一个节点
        HeroNode next = null;
        //
        HeroNode reverseHead = new HeroNode(0, "", "");
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;

    }


    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            System.out.println("空链表  不能打印");
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}


class HeroNode {

    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }
}
