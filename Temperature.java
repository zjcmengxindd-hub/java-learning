import java.nio.file.Files;      // 导入"文件工具箱"：读文件要用它
import java.nio.file.Path;       // 导入"路径"：告诉程序文件放在哪
import java.util.List;           // 导入"清单"：用来装读到的每一行
import java.io.IOException;      // 导入"输入输出异常"：读文件可能失败，Java 要求先声明

public class Temperature {       // 一张图纸，名叫 Temperature（文件名必须跟它一模一样！）

    static double round1(double x) {            // 墙上的公共工具：不用造实物就能用；吐一个小数；吃一个小数 x
        return Math.round(x * 10) / 10.0;       // 把 x 乘 10 →四舍五入 →再除以 10.0
    }                                           // ⚠️ 必须写 10.0！写 10 就变成整数除法（29.6 会变 29）

    public static void main(String[] args) throws IOException {   // 程序入口（= 上电启动）；throws = 读文件万一失败就往上抛

        List<String> lines = Files.readAllLines(Path.of("E:/桌面/JAVA喵/temperatures.txt"));   // 把 txt 的每一行读进来，装进"清单 lines"
                                                                                              // ⚠️ 路径里的斜杠用 /，不要用 \

        double[] temps = new double[lines.size()];   // 造一排"抽屉"（数组），抽屉数量 = 行数（这里 24 个）

        for (int i = 0; i < lines.size(); i++) {     // 循环：i 从 0 开始，只要 i 小于行数就继续，每圈 i 加 1
            temps[i] = Double.parseDouble(lines.get(i).trim());   // 第 i 行文字 → 翻译成小数 → 放进第 i 个抽屉
        }                                                        // get(i)=取第 i 行；trim()=去掉两头空格；parseDouble=文字变数字

        double maxtemp = temps[0];      // 先让"第 0 格"当最高温（0 号选手先当班长）
        int maxHour = 0;                // 顺便记住它是第 0 点

        for (int i = 1; i < temps.length; i++) {     // 从第 1 格开始一个个比（0 号已经当过班长了，不用再跟自己比）
            if (temps[i] > maxtemp) {                // 如果这个温度 比 现任班长还高
                maxtemp = temps[i];                  // 班长换人：记下新的最高温
                maxHour = i;                         // 同时记住它是几点
            }
        }                                            // 循环结束

        double mintemp = temps[0];      // 找最低温：同样的套路，先让第 0 格当前最低温
        for (int i = 1; i < temps.length; i++) {     // 从头一个个比
            if (temps[i] < mintemp) {                // 如果有更低的
                mintemp = temps[i];                  // 就更新最低温
            }
        }

        double sum = 0;                              // 准备一个"累加器"，从 0 开始
                                                     // ⚠️ 是 0 不是 1！从 1 开始总和会多 1，平均值就假了
        for (int i = 0; i < temps.length; i++) {     // 把每个温度都过一遍
            sum = sum + temps[i];                    // 把当前温度加进累加器
        }

        double avg = sum / temps.length;             // 平均值 = 总和 ÷ 个数（用 length 拿个数，不写死 24）

        double maxtemp2 = round1(maxtemp);           // 喊一下公共工具：把最高温圆整到 1 位小数
        double mintemp2 = round1(mintemp);           // 同上：最低温
        double avg2 = round1(avg);                   // 同上：平均温

        System.out.println("==24 小时温度报告");                              // 打印标题（println = 打印一行）
        System.out.println("最高温:" + maxtemp2 + "°C,出现在" + maxHour + "点");   // 打印：这里的加号是"拼接"（把内容串起来），不是算数
        System.out.println("最低温:" + mintemp2 + "°C");                     // 打印最低温
        System.out.println("平均温:" + avg2 + "°C");                         // 打印平均温

        for (int i = 0; i < temps.length; i++) {     // 最后一段：挨个小时检查
            if (temps[i] > 33.0) {                   // 如果这个小时的温度 > 33°C
                                                     // ⚠️ 33.0 是"阈值"（红线）——改这个数字就能调灵敏度，别的都不用动
                System.out.println("高温警告:" + i + "点超温预警！温度为:" + round1(temps[i]) + "°C");   // 当场报警：i 是几点，temps[i] 是当时的温度
            }
        }
    }
}
