package com.example.developerandroidx.ui.java.dataStructure.dataStructureClass;

import com.example.developerandroidx.projectInterface.DataStructureInterface;

/**
 * Date: 2020/5/29 11:49
 * 参考:
 * 描述: 单向链表实现
 */
public class SingleLinkedList<T> implements DataStructureInterface<T> {

    //方便索引增加头节点属性
    private LinkedListNode headNode;
    //节点个数属性，默认为0
    private int size = 0;

    /**
     * 定义链表节点
     */
    private class LinkedListNode {

        private T data;
        private LinkedListNode nextNode;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public LinkedListNode getNextNode() {
            return nextNode;
        }

        public void setNextNode(LinkedListNode nextNode) {
            this.nextNode = nextNode;
        }

        public LinkedListNode(T data) {
            this.data = data;
        }


    }

    @Override
    public void add(int index, T value) {
        size++;
        //限定index的范围
        if (index < 0 || index >= size) {
            return;
        }
        //如果链表是空的
        if (headNode == null) {
            headNode = new LinkedListNode(value);
            return;
        }
        if (index == 0) { //在表头添加节点
            LinkedListNode node = new LinkedListNode(value);
            node.setNextNode(headNode);
            headNode = node;
        } else {//在除了表头表尾的其他地方添加节点
            //要插入的节点
            LinkedListNode node = new LinkedListNode(value);
            int i = 0;
            LinkedListNode insertWhere = headNode;
            while (i < index) {
                //找到插入的位置
                insertWhere = insertWhere.getNextNode();
                i++;
            }
            //设置插入节点的下一个节点
            node.setNextNode(insertWhere.getNextNode());
            //插入节点位置的前一个节点的下一个节点为插入的节点
            insertWhere.setNextNode(node);
        }
    }

    @Override
    public void remove(int index) {
        //限定index的范围
        if (index < 0 || index >= size) {
            return;
        }
        if (headNode == null) {
            return;
        }
        //删除头节点
        if (index == 0) {
            headNode = headNode.getNextNode();
        } else {
            int i = 0;
            LinkedListNode node = headNode;
            while (i < index) {
                //找到要删除的节点的前一个节点
                node = node.getNextNode();
                i++;
            }
            //要删除的节点
            LinkedListNode removeNode = node.nextNode;
            //删除节点
            node.setNextNode(removeNode.getNextNode());
            //释放资源
            removeNode = null;
        }
        size--;
    }

    @Override
    public void set(int index, T value) {
        if (size == 0) {
            return;
        }
        //限定index的范围
        if (index < 0 || index >= size) {
            return;
        }
        if (headNode == null) {
            return;
        }
        if (index == 0) {
            headNode.setData(value);
        } else {
            int i = 0;
            LinkedListNode setNode = headNode;
            while (i < index) {
                setNode = setNode.nextNode;
                i++;
            }

            setNode.setData(value);
        }

    }

    @Override
    public T get(int index) {
        //限定index的范围
        if (index < 0 || index >= size) {
            throw new RuntimeException("index角标越界");
        }
        T value;
        if (index == 0) {
            value = headNode.getData();
        } else {
            LinkedListNode node = headNode;
            int i = 0;
            while (i < index) {
                node = node.getNextNode();
                i++;
            }
            value = node.getData();
        }
        return value;
    }

    /**
     * 获取节点个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 清空链表数据
     */
    public void clear() {
        if (headNode == null) {
            return;
        }
        LinkedListNode node = headNode;
        while (node.getNextNode() != null) {
            node = headNode.getNextNode();
            headNode = null;
            headNode = node;
        }
    }


    /**
     * 获取倒数第n个节点的数据
     *
     * @param n 倒数第n
     * @return 节点数据
     */
    public T getTheLast_N(int n) {
        LinkedListNode answerNode = headNode;//最终要找的节点
        LinkedListNode tempNode = headNode;//遍历链表使用
        int i = 0;
        while (tempNode.getNextNode() != null) {
            if (i >= n - 1) {
                answerNode = answerNode.getNextNode();
            }
            tempNode = tempNode.getNextNode();
            i++;
        }
        return answerNode.getData();
    }

    /**
     * 逆置链表
     */
    public void reversalLinkedList() {

        LinkedListNode temp = null;
        LinkedListNode next = null;
        while (headNode != null) {
            next = headNode.getNextNode();
            headNode.setNextNode(temp);
            temp = headNode;
            headNode = next;
        }
        headNode = temp;
        temp = null;
    }
}
