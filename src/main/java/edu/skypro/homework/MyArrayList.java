package edu.skypro.homework;

import edu.skypro.homework.exceptions.BadIndexException;
import edu.skypro.homework.exceptions.ElementNotFoundException;
import edu.skypro.homework.exceptions.StorageIsFullException;

import java.util.Arrays;
import java.util.Objects;

public class MyArrayList {
    private String[] arr;
    private final int DEFAULT_CAPACITY = 3;
    private int size = 0;

    public MyArrayList(int initialCapacity) {
        this.arr = new String[initialCapacity];
    }

    public MyArrayList() {
        this.arr = new String[DEFAULT_CAPACITY];
    }

    public int size() {
        return this.size;
    }

    public String add(String string) {
        checkElement(string);

        if (size < arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    arr[i] = string;
                    size++;
                    break;
                }
            }
        } else {
            throw new StorageIsFullException("Хранилище переполненно");
        }

        return string;
    }

    public String add(int index, String string) {
        checkElement(string);
        checkIndex(index);
        arr[index] = string;
        return string;
    }

    public String set(int index, String string) {
        add(index, string);
        return string;
    }

    public String remove(String string) {
        checkElement(string);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(string)) {
                arr[i] = null;
                return string;
            }
        }
        throw new ElementNotFoundException(
                "Элемент не найден " + string
        );
    }

    public String remove(int index) {
        checkIndex(index);
        String removedElement = arr[index];
        arr[index] = null;
        return removedElement;
    }

    public boolean contains(String string) {
        checkElement(string);
        for (String s : arr) {
            if (s != null && s.equals(string)) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(String string) {
        checkElement(string);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals(string)) {
                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(String string) {
        checkElement(string);
        for (int length = arr.length - 1; length > 0; length--) {
            if (arr[length] != null && arr[length].equals(string)) {
                return length;
            }
        }
        return -1;
    }

    public String get(int index) {
        checkIndex(index);
        return arr[index];
    }

    public boolean equals(MyArrayList myArrayList) {
        if (myArrayList == null) {
            throw new IllegalArgumentException("Для сравнения передан null");
        }
        if (this == myArrayList) {
            return true;
        }
        if (getClass() != myArrayList.getClass()) {
            return false;
        }
        String[] otherArr = myArrayList.toArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && !arr[i].equals(otherArr[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        int counter = 0;
        for (String s : arr) {
            if (s != null) {
                counter++;
            }
        }
        return counter == 0;
    }

    public void clear() {
        Arrays.fill(arr, null);
    }

    public String[] toArray() {
        String[] newArr = new String[size];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                newArr[i] = arr[i];
            }
        }
        return newArr;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new BadIndexException(
                    "Индекс не может быть отрицательным"
            );
        }
        if (index >= arr.length) {
            throw new BadIndexException(
                    "указан не существующий индекс " + index
            );
        }
    }

    private void checkElement(String string) {
        if (string == null) {
            throw new IllegalArgumentException(
                    "Хранилище не может содержать null"
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyArrayList that = (MyArrayList) o;
        return size == that.size && Arrays.equals(arr, that.arr);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(arr);
        return result;
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
