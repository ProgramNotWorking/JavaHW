package com.example.javahw;

public class QuickSort {

    /**
     * <p><b>Пример использования:</b></p>
     * <pre>{@code
     * int[] arr = {10, 7, 8, 9, 1, 5};
     * QuickSort.quickSort(arr, 0, arr.length - 1);
     * }</pre>
     *
     * @param arr  массив для сортировки
     * @param low  начальный индекс подмассива (включительно)
     * @param high конечный индекс подмассива (включительно)
     * @throws IllegalArgumentException если {@code low} или {@code high} некорректны
     * @see #partition(int[], int, int)
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Разделяет подмассив на элементы меньше и больше опорного элемента
     * @param arr  массив для разделения
     * @param low  начальный индекс подмассива
     * @param high конечный индекс подмассива
     * @throws ArrayIndexOutOfBoundsException если индексы выходят за границы массива
     * @see #swap(int[], int, int)
     * */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве
     *
     * @param arr массив, в котором производится замена
     * @param i   индекс первого элемента
     * @param j   индекс второго элемента
     * @throws ArrayIndexOutOfBoundsException если индексы выходят за пределы массива
     * */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
