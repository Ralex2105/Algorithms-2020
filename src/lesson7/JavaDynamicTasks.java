package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */

    //Трудоемкость: O(first.lenght * second.lenght)
    //Время: O(first.lenght * second.lenght)
    public static String longestCommonSubSequence(String first, String second) {
        int[][] matrix = new int[first.length() + 1][second.length() + 1];
        for (int height = 1; height < first.length() + 1; height++) {
            for (int widght = 1; widght < second.length() + 1; widght++) {
                if (first.charAt(height - 1) != second.charAt(widght - 1)) {
                    matrix[height][widght] = Math.max(matrix[height - 1][widght], matrix[height][widght - 1]);
                } else {
                    matrix[height][widght] = matrix[height - 1][widght - 1] + 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int lengthF = first.length();
        int lengthS = second.length();
        while (lengthF != 0 && lengthS != 0) {
            if (first.charAt(lengthF - 1) == second.charAt(lengthS - 1)) {
                sb.insert(0, first.charAt(lengthF - 1));
                lengthF--;
                lengthS--;
            } else {
                if (matrix[lengthF - 1][lengthS] >= matrix[lengthF][lengthS - 1]) {
                    lengthF--;
                } else {
                    lengthS--;
                }
            }
        }
        return sb.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */

    //Трудоемкость: O(N * N)
    //Время: O(N)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        if (list.isEmpty()) return list;
        List<Integer> result = new ArrayList<>();
        List<Integer> previous = new ArrayList<>();
        List<Integer> value = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            value.add(i,1);
            previous.add(i, -1);
            for (int j = 0; j < i; j++) {
                if (list.get(j) < list.get(i) && value.get(j) + 1 > value.get(i)) {
                    value.add(i, value.get(j) + 1);
                    previous.set(i, j);
                }
            }
        }
        int index = 0;
        int length = 0;
        for (int i = 0; i < list.size(); i++) {
            if (value.get(i) > length) {
                index = i;
                length = value.get(i);
            }
        }
        while (index != -1) {
            result.add(0, list.get(index));
            index = previous.get(index);
        }
        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
