package homework1;

import java.util.Arrays;
import java.util.List;

public class ArrayChangeNumber {

    public void arrayInfo(String[] arrays) {

        for (int i = 0; i < arrays.length; i++) {
            System.out.printf(arrays[i] + "  ");
        }
        System.out.println();
    }
// Метод , меняющий местами элементы в массиве.
    public void arrayChangeNumber(String[] arrays, int i, int j) {

        String a1 = arrays[i];
        String a2 = arrays[j];
        arrays[i] = a2;
        arrays[j] = a1;
        for (int h = 0; h < arrays.length; h++) {
            System.out.printf(arrays[h] + "  ");
        }
        System.out.println();
    }
// Метод преобразования массива в List.
    public void arraysChangeToList(String[] arrays) {
        List<String> list = Arrays.asList(arrays);
        System.out.println(list);
    }
}
