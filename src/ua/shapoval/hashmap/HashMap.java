package ua.shapoval.hashmap;





public class HashMap<K, V>{

    private Node<K, V>[] storage = (Node<K, V>[]) new Node[16];
    
    private int size;

    private class Node<K, V>{
        final int hash;
        K key;
        V value;
        Node next;
        private Node (int hash, K key, V value, Node node){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = node;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }


    public HashMap(){}

    public int size(){ return size;}

    public boolean isEmpty(){ return size == 0;}

    public void put (K key, V value){
        if  (size >= storage.length) increaseStorage();

        Node newNode = new Node(hash(key), key, value, null);
        int cellAddress = newNode.hash & (storage.length - 1);

        if  (storage[cellAddress] == null){
            storage[cellAddress] = newNode;
            size++;
            return;
        }

        Node lastNodeOrCurrentKeyNode = findLastNodeOrCurrentKeyNode( storage[cellAddress], key);

        if (equalKeys(lastNodeOrCurrentKeyNode.key, key)){
            lastNodeOrCurrentKeyNode.value = value;
            size++;
            return;
        }


        lastNodeOrCurrentKeyNode.next = newNode;
        size++;

    }

    private int hash(K key){
        int h;
        return key == null ? 0 : (( h = key.hashCode())^(h >>> 16));
    }

    private boolean equalKeys (Object key1, Object key2){
        return key1 == key2 || (key1 != null && key1.equals(key2));
    }

    private Node findLastNodeOrCurrentKeyNode (Node node, K key){
        if (node == null) return null;
        while(node.next != null  ){
            if (equalKeys(node.key, key)) break;
            node = node.next;
        }
        return node;
    }

    private void increaseStorage(){
        Node[] newStorage = new Node[storage.length << 2];

        for(Node node: storage){
            while(node != null) {
                int cellAddress = node.hash & (newStorage.length - 1);
                if(newStorage[cellAddress] == null) {
                    newStorage[cellAddress] = node;
                    node=node.next;
                    newStorage[cellAddress].next = null;
                    continue;
                }

                Node lastNode = findLastNode(newStorage[cellAddress]);
                lastNode.next = node;
                node = node.next;
                lastNode.next.next = null;
            }
        }

        storage = newStorage;
    }

    private Node findLastNode(Node node){
        while(node.next != null)
            node = node.next;
        return node;
    }

    public V remove(K key){
        int cellAddress = hash(key) & (storage.length - 1);

        Node current = findLastNodeOrCurrentKeyNode(storage[cellAddress], key);
        if (equalKeys(current.key, key)) {
            V result = (V) current.value;
            storage[cellAddress] = null;
            size--;
            return result;
        }
        if  (current.next == null )
            return null;
        V result = (V) current.next.value;
        current.next = current.next.next;
        size--;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(size * 2 + 1 );

        stringBuilder.append("{");

        boolean isFirst = true;

        for (Node entry: storage){

            if (!isFirst) {
                stringBuilder.append(", ");
            } else {
                isFirst = false;
            }

            while ( entry != null ) {
                stringBuilder.append(entry);
                entry = entry.next;
            }
        }

        stringBuilder.append("}");

        return stringBuilder.toString();
    }

}



