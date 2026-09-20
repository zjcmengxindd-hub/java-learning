public class CounterTest {
    static int total = 0;   // 公共的（所有实物共享一份）
    static int mine = 0;           // 私人的（每个实物各一份）

    public static void main(String[] args) {
        CounterTest a = new CounterTest();
        CounterTest b = new CounterTest();

        a.total = 5;                 // 改"公共的"
        System.out.println("b 看到的 total: " + b.total);   // → 5！（b 也变了 = 共享）

        a.mine = 7;                  // 改 a 私人的
        System.out.println("b 看到的 mine: " + b.mine);     // → 0（b 不受影响 = 私人）
    }
}