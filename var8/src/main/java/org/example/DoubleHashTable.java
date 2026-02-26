package org.example;

public class DoubleHashTable<Key, Value> {
    private Node<Key, Value>[] table1;
    private Node<Key, Value>[] table2;
    private final int MAX_LOOP;
    int count1, count2;

    private static class Node<Key, Value> {
        Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public DoubleHashTable(int maxLoop) {
        table1 = new Node[16];
        table2 = new Node[16];
        count1 = 0;
        count2 = 0;
        MAX_LOOP = maxLoop;
    }

    private int hash1(Key key) {
        return (key.hashCode() & 0x7FFFFFFF) % 16;
    }

    private int hash2(Key key) {
        return ((key.hashCode()* 31) & 0x7FFFFFFF) % 16;
    }

    public void put(Key key, Value value) {
        if((double) count1 / table1.length > 0.5 || (double) count2 / table2.length > 0.5) rehash(table1.length*2);

        int hash = hash1(key);
        if (table1[hash] == null) {
            table1[hash] = new Node<>(key, value);
            count1++;
            return;
        }

        Node<Key, Value> temp = table1[hash];
        table1[hash] = new Node<>(key, value);

        hash = hash2(temp.key);
        for (int i = 0; i < MAX_LOOP; i++) {
            if (table2[hash] == null) {
                table2[hash] = temp;
                count2++;
                return;
            }

            Node<Key, Value> temp2 = table2[hash];
            table2[hash] = temp;

            hash = hash1(temp2.key);
            if (table1[hash] == null) {
                table1[hash] = temp2;
                count1++;
                return;
            }

            temp = table1[hash];
            table1[hash] = temp2;

            hash = hash2(temp.key);
        }

        rehash(table1.length*2);
        put(key, value);
    }

    public Value get(Key key) {
        Node<Key, Value> temp = table1[hash1(key)];
        if(temp != null && temp.key.equals(key)) return temp.value;
        temp = table2[hash2(key)];
        if(temp != null && temp.key.equals(key)) return temp.value;
        return null;
    }

    public void remove(Key key) {
        int h1 = hash1(key);
        if (table1[h1] != null && table1[h1].key.equals(key)) {
            table1[h1] = null;
            count1--;
            if((double) count1 / table1.length < 0.3 || (double) count2 / table2.length < 0.3) rehash(table1.length/2);
            return;
        }

        int h2 = hash2(key);
        if (table2[h2] != null && table2[h2].key.equals(key)) {
            table2[h2] = null;
            count2--;
            if((double) count1 / table1.length < 0.3 || (double) count2 / table2.length < 0.3) rehash(table1.length/2);
        }
    }
    private void rehash(int newCapacity) {
        Node<Key, Value>[] oldTable1 = table1;
        Node<Key, Value>[] oldTable2 = table2;


        table1 = new Node[newCapacity];
        table2 = new Node[newCapacity];

        count1 = 0;
        count2 = 0;

        for (Node<Key, Value> node : oldTable1)
            if (node != null) put(node.key, node.value);

        for (Node<Key, Value> node : oldTable2)
            if (node != null) put(node.key, node.value);
    }
}