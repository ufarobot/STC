package day1;

import java.util.Arrays;
import java.util.Scanner;

/*
@author Airat Ishimbaev

Дан массив целых положительных чисел. Дано целое положительное число.
Посчитать количество пар элементов в массиве, которые в сумме дадут входное число.
        Пример:
        Массив - [1, 2, 3, 4], входное число- 5
        Ответ- 2 пары дают в сумме 5 (1+4 и 2+3)*/
public class Day1Task1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите размер массива:");
        int n = in.nextInt();
        int[] a = new int[n];

        System.out.println("Введите элементы массива, разделенные пробелом:");
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        Arrays.sort(a);
        System.out.println("Введите входное число:");
        int sum = in.nextInt();

        int count = 0;
        for (int i = 0; i < n - 1; i++) {
            int secondNum = sum - a[i];
            int index = binarySearch(a, i + 1, n - 1, secondNum);
            if (index > 0) {
                count++;
                int j = index - 1;
                while (j > i && a[j--] == secondNum) count++;
                j = index + 1;
                while (j < n && a[j++] == secondNum) count++;
            }
        }
        System.out.println("Ответ: " + count);
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
