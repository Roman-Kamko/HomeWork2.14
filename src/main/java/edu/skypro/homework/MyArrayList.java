package edu.skypro.homework;

import edu.skypro.homework.exceptions.StorageIsFullException;
import edu.skypro.homework.exceptions.BadIndexException;

import java.util.Arrays;

public class MyArrayList {
    private String[] arr;
    private final int DEFAULT_CAPACITY = 3;
    private int initialCapacity;
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
        if (string == null) {
            throw new IllegalArgumentException(
                    "Хранилище не может содержать " + string
            );
        }

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
        if (index < 0) {
            throw new BadIndexException(
                    "Индекс не может быть отрицательным"
            );
        }

        if (index < arr.length) {
            arr[index] = string;
        } else {
            throw new BadIndexException(
                    "указан несуществующий индекс " + index
            );
        }

        return string;
    }

    public String[] getAll() {
        String[] newArr = new String[size];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                newArr[i] = arr[i];
            }
        }
        return newArr;
    }

    @Override
    public String toString() {
        return Arrays.toString(getAll());
    }
}
