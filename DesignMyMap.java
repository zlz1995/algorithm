import java.util.*;
public class DesignMyMap{
    public static void main(String[] args){
        MyMap map = new MyMap();
        map.put("1","张三");
        System.out.println(map.get("1"));
        map.put("1","李四");
        System.out.println(map.get("1"));
        map.put("2","王五");
        System.out.println(map.get("2"));
        System.out.println(map.size());
        map.remove("1");
        System.out.println(map.size());
        System.out.println(map.get("1"));
    }
}

class Node{
    Entry val;
    Node next;

    public Node(Entry val,Node next) {
        this.val = val;
        this.next = next;
    }
}

class Entry{
    String k;
    String v;

    public Entry(String k, String v) {
        this.k = k;
        this.v = v;
    }
}

class MyMap{

    int size = 0;
    int arrlen = 16;
    Entry nullEntry;
    Node[] arr = new Node[arrlen];

    public MyMap(){
        for(int i = 0; i < arrlen; i++){
            arr[i] = new Node(null,null);
        }
    }
    public void put(String k,String v){
        if(k == null){
            nullEntry = new Entry(null,v);
        }else{
            int index = k.hashCode()%arrlen;
            Node head = arr[index];
            boolean contains = false;
            Node cur = head.next;
            while(cur != null){
                if(cur.val.k.equals(k)){
                    contains = true;
                    cur.val.v = v;
                }
                cur = cur.next;
            }
            if(!contains){
                Entry entry = new Entry(k,v);
                Node node = new Node(entry,head.next);
                head.next = node;
                size++;
            }
        }
    }

    public String get(String k){
        if(k == null){
            return nullEntry.v;
        }
        int index = k.hashCode()%arrlen;
        Node head = arr[index];
        Node cur = head.next;
        while(cur != null){
            if(cur.val.k.equals(k)){
                return cur.val.v;
            }
            cur = cur.next;
        }
        return null;
    }

    public int size(){
        return size;
    }

    public void remove(String k){
        if(k == null){
            nullEntry = null;
        }else{
            int index = k.hashCode()%arrlen;
            Node head = arr[index];
            Node cur = head.next;
            while(cur != null){
                if(cur.val.k.equals(k)){
                    if(cur.next != null){
                        cur.val = cur.next.val;
                        cur.next = cur.next.next;
                    }else{
                        cur = null;
                    }
                    size--;
                    return;
                }
                cur = cur.next;
            }
        }
    }
}
