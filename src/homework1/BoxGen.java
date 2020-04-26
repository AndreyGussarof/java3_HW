package homework1;

import java.util.ArrayList;

public class BoxGen <T extends Fruit> {

    ArrayList<Fruit> list = new ArrayList<>();

    public int addFruit(T fruit) {
        list.add(fruit);
        System.out.println(fruit + " Добавлен в коробку ");
        int size = list.size();
        System.out.println("В коробке лежит " + size + " шт ");
        return size;
    }

    public float getWeight() {
        if(list.size() == 0){
            return 0 ;}
        else {
            return list.size() * list.get(0).getWeight();
        }
    }

    public boolean compare(BoxGen another){
        return Math.abs(this.getWeight()-another.getWeight())<0.0001;
    }

    public int giveAllFruit (BoxGen<T> box){
        box.list.addAll(list);
        list.clear();
        int size = box.list.size();
        return size;
    }
}



