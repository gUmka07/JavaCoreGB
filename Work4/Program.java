package hw;

import hw.exception.MyArrayDataException;
import hw.exception.MyArraySizeException;

public class Program {
    public static void main(String[] args) {
        String[][] array = {
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "a", "12"},
                {"13", "14", "15", "16"}
        };

        try {
            int result = arraySum(array);
            System.out.println("Сумма элементов массива: " + result);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static int arraySum(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Размер массива должен быть 4x4");
        }

        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Неверные данные в ячейке [" + i + "][" + j + "]");
                }
            }
        }
        return sum;
    }
}


// 1 Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4. При
// подаче массива другого размера необходимо бросить исключение MyArraySizeException.
// 2 Далее метод должен пройтись по всем элементам массива, преобразовать в int и
// просуммировать. Если в каком-то элементе массива преобразование не удалось (например, в
// ячейке лежит символ или текст вместо числа), должно быть брошено исключение
// MyArrayDataException с детализацией, в какой именно ячейке лежат неверные данные.
// 3 В методе main() вызвать полученный метод, обработать возможные исключения
// MyArraySizeException и MyArrayDataException и вывести результат расчета.
