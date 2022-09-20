import java.util.Collections;

class Counter {

    public static boolean checkTheSameNumberOfTimes(int elem, List<Integer> list1, List<Integer> list2) {
        int lst1Nb = Collections.frequency(list1, elem);
        int lst2Nb = Collections.frequency(list2, elem);
    
        return lst1Nb == lst2Nb ? true : false;
    }
}
