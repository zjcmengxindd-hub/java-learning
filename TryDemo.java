public class TryDemo {
    public static void main(String[] args) {
        try {
            int result = 114514/0;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("出错了:" + e.getMessage());
        }

        System.out.println("沃趣，还活着！");
    }
}
