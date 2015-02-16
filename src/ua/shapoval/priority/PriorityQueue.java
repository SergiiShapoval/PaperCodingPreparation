package ua.shapoval.priority;


import java.util.*;



public class PriorityQueue {

    private Integer[] storage = new Integer[16];
    private int size;

    public PriorityQueue() {
    }

    public PriorityQueue(int storageSize) {
        storage = new Integer[storageSize];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean offer(Integer value) {
        if (value == null) return false;
        if (storage.length <= size()) resizeStorage();
        storage[size] = value;
        getUp(size);
        size++;
        return true;
    }

    private void resizeStorage() {
        storage = Arrays.copyOf(storage, storage.length * 2);
    }

    private void getUp(int pos) {
        if (pos == 0) return;
        int parentPos = pos >> 1;
        if (storage[parentPos].compareTo(storage[pos]) >= 0)
            return;
        change(parentPos, pos);
        getUp(parentPos);
    }

    private void change(int pos1, int pos2) {
        Integer buffer = storage[pos1];
        storage[pos1] = storage[pos2];
        storage[pos2] = buffer;
    }

    public Integer peek() {
        return isEmpty() ? null : storage[0];
    }

    public Integer poll() {
        if (isEmpty()) return null;
        Integer result = storage[0];
        size--;
        change(0, size);
        getDown(0);
        return result;
    }

    private void getDown(int pos) {
        int childPos1 = pos * 2 + 1;
        int childPos2 = childPos1++;

        if (childPos2 >= size) return;
        int maxChildPos = getMaxChildPos(childPos1, childPos2);

        if (storage[maxChildPos].compareTo(storage[pos]) <= 0) return;

        change(maxChildPos, pos);
        getDown(maxChildPos);
    }

    private int getMaxChildPos(int childPos1, int childPos2) {
        if (childPos1 >= size) return childPos2;
        if (childPos2 >= size) return childPos1;
        if (storage[childPos1].compareTo(storage[childPos2]) > 0) return childPos1;
        return childPos2;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();

        boolean isFirst = true;
        for (int i = 0; i < size(); i++) {
            if (!isFirst) stringBuilder.append(", ");
            else isFirst = false;
            stringBuilder.append(storage[i]);
        }

        return "PriorityQueue{" +
                  stringBuilder.toString() +
                '}';
    }
}