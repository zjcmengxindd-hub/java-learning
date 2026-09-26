import java.util.*;


public class SortDemo {

    public static void main(String[] args) {
        double[] temps = {35.8, 26.1, 31.5, 28.9};
        Arrays.sort(temps);
        System.out.println("数组升序: " + Arrays.toString(temps));

        List<Double> list = new ArrayList<>();
        list.add(35.8); list.add(26.1); list.add(31.5);

        Collections.sort(list);
        System.out.println("List升序: " + list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("List降序: " + list);

        System.out.println("最大: " + Collections.max(list));
        System.out.println("最小: " + Collections.min(list));
    }
}