/*
@author Airat Ishimbaev
Даны 2 отсортированных массива. Найти все элементы в 1м массиве, которых нет во 2-м.
Пример:
Массив 1- [1, 2, 3, 4], Массив 2- [2, 4, 6, 8]
Ответ- 1, 3
*/

import java.util.Scanner;

public class Day1Task2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите размер первого массива:");
        int n1 = in.nextInt();
        int[] a1 = new int[n1];

        System.out.println("Введите элементы первого массива, разделенные пробелом:");
        for (int i = 0; i < n1; i++) {
            a1[i] = in.nextInt();
        }
        System.out.println("Введите размер второго массива:");
        int n2 = in.nextInt();
        int[] a2 = new int[n2];

        System.out.println("Введите элементы второго массива, разделенные пробелом:");
        for (int i = 0; i < n2; i++) {
            a2[i] = in.nextInt();
        }
        System.out.println("Ответ:");
        for (int i = 0; i < n1; i++) {
            if (binarySearch(a2, 0, n2 - 1, a1[i]) < 0)
                System.out.print(a1[i] + " ");
        }

    }
    private static int binarySearch(int[] a, int left, int right, int key) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (key < a[mid]) right = mid - 1;
            else if (key > a[mid]) left = mid + 1;
            else return mid;
        }
        return -1;
    }

}
