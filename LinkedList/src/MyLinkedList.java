import java.util.Iterator;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MyLinkedList<T> implements Iterator{
    private Node<T> head;
    private Node<T> tail;
    private int size;

    LinkedList<T> list = new LinkedList<>();
    Iterator<T> iter = list.iterator();
    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T item, Node<T> next){
            this.item = item;
            this.next = next;
        }
    }
//    LinkedList<T> list = new LinkedList<T>();
//    Iterator<T> iter = list.iterator();

    public void show(){
        for(T data:list){
            System.out.println(data);
        }
    }


    public Node<T> get(int index){
       Node<T> n = head;
       for(int i=0;i<index;i++){
           n = n.next;
       }
       return n;
    }


    public boolean add(T value){
        Node<T> last = tail;
        Node<T> newNode = new Node<>(value,null);
        size++;
        tail=newNode;
        if(last==null){
            head=newNode;
        }else{
            last.next = newNode;
        }

        list.add(value);
        return true;
    }

    public T removeFirst() {
        if (head == null) {
            throw new IndexOutOfBoundsException();
        }
        T returnValue = head.item;

        Node<T> first = head.next;
        head.next = null;
        head.item = null;
        head = first;

        size--;

        if (head == null) {
            tail = null;
        }
        return returnValue;
    }
    public T removeLast(){
        return delete(size-1);
    }

    public T delete(int index){
        if(index<0 || index >= size){
            throw new IndexOutOfBoundsException();
        }

        if(index==0){
            return removeFirst();
        }
        Node <T> prev_node = get(index-1);

        Node <T> del_node = prev_node.next;
        Node <T> next_node = del_node.next;

        T returnValue = del_node.item;

        del_node.next=null;
        del_node.item=null;

        size--;
        prev_node.next= next_node;
        return returnValue;

    }


    @Override
    public boolean hasNext() {
        return iter.hasNext();
    }

    @Override
    public Object next() {
        return iter.next();
    }

    @Override
    public void remove() {
        iter.remove();
    }

    @Override
    public void forEachRemaining(Consumer action) {
        iter.forEachRemaining(action);
    }
}
