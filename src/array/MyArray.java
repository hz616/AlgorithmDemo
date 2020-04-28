package array;

public class MyArray {

    private int[] array;
    int size;

    public MyArray(int capacity) {
        this.array = new int[capacity];
        size = 0;
    }


    public int get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("size is " + size + " and index is " + index);
        }
        return array[index];
    }

    public void insert(int index, int value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("size is " + size + " and index is " + index);
        }

        if (size >= array.length) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = value;
        size++;
    }

    public int delete(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("size is " + size + " and index is " + index);
        }

        int deleteValue = array[index];

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return deleteValue;

    }

    public void resize() {
        int[] resizeArray = new int[array.length * 2];
        System.arraycopy(array, 0, resizeArray, 0, array.length);
        array = resizeArray;
    }

    public void outPut() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }


    public static void main(String[] args) {
        System.out.println("=================insert=====================");
        MyArray array = new MyArray(5);
        array.insert(0, 0);
        array.insert(1, 1);
        array.insert(2, 2);
        array.insert(3, 3);
        array.insert(4, 4);
        array.insert(5, 5);
        array.insert(6, 6);
        array.outPut();

        System.out.println("=================get=====================");
        System.out.println("get value " + array.get(4));

        System.out.println("=================delete=====================");
        System.out.println("delete value is " + array.delete(1));
        array.outPut();

        System.out.println("==============after delete then insert=============");

        array.insert(1,1);
        array.outPut();
    }
}
