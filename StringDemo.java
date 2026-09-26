public class StringDemo {
    public static void main(String[] args) {
        String s = "  Hello,Java world!  ";

        System.out.println("原长度" + s.length());
        System.out.println("去掉空格:[" + s.trim() + "]");
        System.out.println("大写" + s.toUpperCase());
        System.out.println("是否含有Java" + s.contains("Java"));
        System.out.println("Java在哪" + s.indexOf("Java"));
        System.out.println("前五个" + s.trim().substring(0, 5));
        System.out.println("替换" + s.replace("Java", "C#"));


        String[] words = s.trim().split(",");
        System.out.println("切出几段" + words.length);


        String name = "tom";
        name.toUpperCase();
        System.out.println("返回值" + name);

        name = name.toUpperCase();
        System.out.println("返回值" + name);

    }
}
