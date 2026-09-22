import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String,String> phoneBook = new HashMap<>();

        phoneBook.put("仙贝", "114514");
        phoneBook.put("香蕉君", "1919");
        phoneBook.put("母猪跳水","810");

        System.out.println("仙贝的电话:" + phoneBook.get("仙贝"));
        System.out.println("仙人牌位数" + phoneBook.size());
        System.out.println("有没有曾小贤" + phoneBook.containsKey("曾小贤"));


        for(String name : phoneBook.keySet()){
            System.out.println(name + " → " + phoneBook.get(name));
        }
    }
}
