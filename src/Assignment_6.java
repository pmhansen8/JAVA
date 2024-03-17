

import java.util.Random;
class Node {
    int mValue;
    Node mNext;

    public Node(int value) {
        mValue = value;

    }

    public void print() {
        System.out.print(mValue + "->");
        }
    }

class LinkedList {
    Node mHead;
    public LinkedList(){
        mHead = null;
    }
    public Node insert(int value){
        Node newNode = new Node(value);
        if (mHead == null || mHead.mValue >= newNode.mValue) {
            newNode.mNext = mHead;
            mHead = newNode;
        } else {
            Node current = mHead;
            while (current.mNext != null && current.mNext.mValue < newNode.mValue) {
                current = current.mNext;
            }
            newNode.mNext = current.mNext;
            current.mNext = newNode;
        }
        return newNode;
    }
    public void remove(int value){
        Node temp = mHead, prev = null;
        if (temp != null && temp.mValue == value) {
            mHead = temp.mNext;
            return;
        }
        while (temp != null && temp.mValue != value) {
            prev = temp;
            temp = temp.mNext;
        }
        if (temp == null) return;
        prev.mNext = temp.mNext;
    }
    public int count(){
        Node temp = mHead;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.mNext;
        }
        return count;
    }
    public void print(){
        Node temp = mHead;
        while (temp != null) {
            temp.print();
            temp = temp.mNext;
            if (temp == null){
                System.out.print("\n");
            }
        }
    }
}
/**
 *
 * @author neslisah
 */
public class Assignment_6 {
    public static Node computeTheMergeNode(LinkedList lst1, LinkedList lst2){
        Node head1 = lst1.mHead;
        Node head2 = lst2.mHead;
        while (head1 != head2) {
            if (head1 == null) {
                head1 = lst2.mHead;
            } else {
                head1 = head1.mNext;
            }
            if (head2 == null) {
                head2 = lst1.mHead;
            } else {
                head2 = head2.mNext;
            }
        }
        return head1;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
// TODO code application logic here
        Random random = new Random();
        int szList1UntilMerge = random.nextInt(100) + 1;
        int szList2UntilMerge = random.nextInt(100) + 1;
        int szListAfterMerge = random.nextInt(100) + 1;
        LinkedList list1 = new LinkedList();
        LinkedList list2 = new LinkedList();
        for (int i=0;i<szList1UntilMerge;i++){
            int value = random.nextInt(1000);
            list1.insert(value);
        }
        if (list1.count() != szList1UntilMerge){
            System.out.print("There is an error in the insert function!");
        }
        Node mergeNode = list1.insert(2000+random.nextInt(100));
        for (int i=0;i<szListAfterMerge;i++){
            int value = mergeNode.mValue + 1 + random.nextInt(1000);
            list1.insert(value);
        }
        if (list1.count() != szList1UntilMerge+szListAfterMerge+1){
            System.out.print("There is an error in the insert function!");
        }
        for (int i=0;i<szList2UntilMerge;i++){
            int value = random.nextInt(1000);
            list2.insert(value);
        }
        if (list2.count() != szList2UntilMerge){
            System.out.print("There is an error in the insert function!");
        }
        Node curr = list2.mHead;
        Node prev = null;
        while (curr != null){
            prev = curr;
            curr = curr.mNext;
        }
        prev.mNext = mergeNode;
        if (list2.count() != szList2UntilMerge+szListAfterMerge+1){
            System.out.print("There is an error in the merge function!");
        }
        System.out.println("List 1:");
        list1.print();
        System.out.println("List 2:");
        list2.print();
        Node mergeNode2 = computeTheMergeNode(list1, list2);
        if (mergeNode == mergeNode2){
            System.out.println("MergeNode correctly detected");
            System.out.println(mergeNode2.mValue);
        }
        else{
            System.out.println("Please check your algorithm!");
        }
    }
}
