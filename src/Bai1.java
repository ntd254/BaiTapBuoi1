import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Bai1 {
    public static void main(String[] args) {
        Set<Integer> set1 = getSet();
        Set<Integer> set2 = getSetBaseOnOldSet(set1);
        System.out.println(set2.size());
        Set<Integer> tapHop = timHop(set1, set2);
        Set<Integer> tapGiao = timGiao(set1, set2, tapHop);
        System.out.println(tapGiao.size());
    }

    public static Set<Integer> getSet() {
        Random random = new Random();
        Set<Integer> setOfInt = new HashSet<>();
        while (setOfInt.size() != 2000000) {
            setOfInt.add(random.nextInt());
        }
        return setOfInt;
    }

    public static Set<Integer> getSetBaseOnOldSet(Set<Integer> oldSet) {
        Random random = new Random();
        Set<Integer> setOfInt = new HashSet<>();
        while (setOfInt.size() != 1900000) { // 1900000 = 95% * 2000000
            int randomNumber = random.nextInt();
            if (!oldSet.contains(randomNumber)) setOfInt.add(randomNumber);
        }
        for (int num : oldSet) {
            setOfInt.add(num);
            if (setOfInt.size() == 2000000) break;
        }
        return setOfInt;
    }

    public static Set<Integer> timHop(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> tapHop = new HashSet<>();
        tapHop.addAll(set1);
        tapHop.addAll(set2);
        return tapHop;
    }

    public static Set<Integer> timGiao(Set<Integer> set1, Set<Integer> set2, Set<Integer> tapHop) {
        Set<Integer> tapGiao = new HashSet<>();
        for (int num : tapHop) {
            if (set1.contains(num) && set2.contains(num)) tapGiao.add(num);
        }
        return tapGiao;
    }
}



