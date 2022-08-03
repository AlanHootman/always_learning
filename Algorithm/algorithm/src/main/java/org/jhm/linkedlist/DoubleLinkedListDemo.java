package org.jhm.linkedlist;

/**
 * @Description: 双向链表相关操作
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/24 10:39 下午
 * @Version: 1.0.0
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {

        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");
        HeroNode2 heroNode5 = new HeroNode2(5, "鲁智深", "花和尚");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

//        doubleLinkedList.add(heroNode1);
//        doubleLinkedList.add(heroNode2);
//        doubleLinkedList.add(heroNode3);
//        doubleLinkedList.add(heroNode4);
//        doubleLinkedList.add(heroNode5);
//        System.out.println("初始化双向链表并输出：");
//        doubleLinkedList.list();

        System.out.println("不按顺序加入并输出：");
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode2);
        doubleLinkedList.addByOrder(heroNode5);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.list();

        System.out.println("修改节点并输出");
        doubleLinkedList.update(new HeroNode2(5, "鲁智深", "光头强"));
        doubleLinkedList.list();

        System.out.println("删除节点并输出");
        doubleLinkedList.delete(5);
        doubleLinkedList.list();


    }
}

/**
 * @Description: 创建一个双向链表的类
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/24 10:46 下午
 * @Version 1.0.0
 */
class DoubleLinkedList {

    private final HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    /**
     * @Description: 遍历双向链表
     * @Param:
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/24 10:45 下午
     */
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空");
        }
        HeroNode2 cur = head.next;
        while (cur != null) {
            System.out.println(cur);
            cur = cur.next;
        }
    }

    /**
     * @Description: 添加到双向链表的末尾
     * @Param: newHeroNode2
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/24 10:49 下午
     */
    public void add(HeroNode2 newHeroNode2) {
        if (newHeroNode2 == null) {
            System.out.println("节点为空，不添加");
        }
        HeroNode2 cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newHeroNode2;
        newHeroNode2.pre = cur;
    }

    /**
     * @Description: 按照no顺序添加，原节点增序
     * @Param: heroNode2
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/24 11:19 下午
     */
    public void addByOrder(HeroNode2 heroNode2) {
        if (heroNode2 == null) {
            System.out.println("要添加的节点为空，不添加");
        }
        int no = heroNode2.no;
        // 是否有相同no的节点
        boolean flag = false;
        HeroNode2 cur = head;
        while (true) {
            if (cur.next == null) {
                break;
            }
            if (cur.next.no > no) {
                break;
            } else if (cur.next.no == no) {
                flag = true;
                System.out.println("不能添加no相同的节点");
            }
            cur = cur.next;
        }
        heroNode2.next = cur.next;
        heroNode2.pre = cur;
        if (cur.next != null) {
            cur.next.pre = heroNode2;
        }
        cur.next = heroNode2;

    }

    /**
     * @Description: 修改节点
     * @Param: heroNode2 节点新的值，根据节点内部no找到对应得节点
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/24 10:54 下午
     */
    public void update(HeroNode2 heroNode2) {
        if (heroNode2 == null || head.next == null) {
            System.out.println("要修改的节点为空，不需要修改");
        }
        HeroNode2 cur = head.next;
        int no = heroNode2.no;
        // 是否找到需要修改的节点
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.no = heroNode2.no;
            cur.name = heroNode2.name;
            cur.nickName = heroNode2.nickName;
        } else {
            System.out.println("未找到要修改的节点");
        }
    }

    /**
     * @Description: 按照节点内部no 来删除节点
     * @Param: no 编号
     * @Return: void
     * @Author: jianghm40441 <jianghm40441@hundsun.com>
     * @Date: 2021/7/24 11:04 下午
     */
    public void delete(int no) {
        if (head.next == null) {
            System.out.println("链表为空，某的感情");
        }
        HeroNode2 cur = head.next;
        boolean flag = false;
        while (cur != null) {
            if (cur.no == no) {
                flag = true;
                break;
            }
            cur = cur.next;
        }
        if (flag) {
            cur.pre.next = cur.next;
            // cur.next.pre = cur.pre 此语句不是最后一个节点时正常，但是最后一个节点时  cur.next == null ,所以
            if (cur.next != null) {
                cur.next.pre = cur.pre;
            }
            // 前后指针置为null 方便垃圾回收
            cur.next = null;
            cur.pre = null;
        } else {
            System.out.println("未找到要删除的节点");
        }
    }


}

/**
 * @Description: 链表节点类
 * @Author: jianghm40441 <jianghm40441@hundsun.com>
 * @Date: 2021/7/24 10:47 下午
 * @Version 1.0.0
 */
class HeroNode2 {

    public int no;
    public String name;
    public String nickName;
    public HeroNode2 pre;
    public HeroNode2 next;

    public HeroNode2(int no, String name, String nickName) {
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

