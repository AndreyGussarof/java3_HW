package homework1;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int i;
        int j;
        String[] arrays = {"a", "b", "c", "d", "e"};

        ArrayChangeNumber arr = new ArrayChangeNumber();
        arr.arrayInfo(arrays);
        try {
            arr.arrayChangeNumber(arrays, 4, 1);
        } catch (ArrayIndexOutOfBoundsException e) {

            System.out.println("неправильно введён номер элемента массива");
        }
        arr.arraysChangeToList(arrays);

        BoxGen<Orange> boxOrange = new BoxGen<>();
        boxOrange.addFruit(new Orange());
        boxOrange.addFruit(new Orange());
        boxOrange.addFruit(new Orange());

        BoxGen<Orange> boxOrange2 = new BoxGen<>();
        boxOrange.giveAllFruit(boxOrange2);

        BoxGen<Apple> boxApple = new BoxGen<>();
        boxApple.addFruit(new Apple());
        boxApple.addFruit(new Apple());

        System.out.println("Масса коробки с апельсинами равна " + boxOrange.getWeight() );
        System.out.println("Масса коробки с яблоками равна " + boxApple.getWeight() );
        System.out.println("Масса коробки с апельсинами равна " + boxOrange2.getWeight() );
        System.out.println(boxApple.compare(boxOrange));
    }


}

