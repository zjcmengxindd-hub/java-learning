import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Device {
    String name;
    double value;
    double threshold;

    boolean running = false;

    boolean isOverLimit() {
        return value > threshold;
    }

    void start() {
        running = true;
        System.out.println(name + "启动");
    }

    void stop() {
        running = false;
        // System.out.println(name + "停止");
    }

    void collect() {
        System.out.println(name + "采集到" + value);
    }
}

interface Alarmable {
    void alarm();
}

class TemperatureSensor extends Device implements Alarmable {
    @Override
    void collect() {
        System.out.println(name + "采集到温度" + value + "°C");
    }

    @Override
    public void alarm() {
        System.out.println("警告 " + name + "温度超限! 当前" + value + "°C(阈值 " + threshold + ")");
    }
}

class VoltageSensor extends Device implements Alarmable {
    @Override
    void collect() {
        System.out.println(name + "检测到电压" + value + "V");
    }

    @Override
    public void alarm() {
        System.out.println("警告 " + name + " 电压超限！当前" + value + "V(阈值 " + threshold + ")");
    }
}

class HumiditySensor extends Device implements Alarmable {
    @Override
    void collect() {
        System.out.println(name + "采集到湿度" + value + "%");
    }

    @Override
    public void alarm() {
        System.out.println("警告 " + name + "湿度过高! 当前" + value + "%(阈值 " + threshold + ")");
    }
}

public class SensorMonitor {
    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("E:/桌面/JAVA喵/sensors.txt"));

        List<Device> devices = new ArrayList<>();
        Map<String, Device> sensorMap = new HashMap<>();

        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");

            String type = parts[0];
            String name = parts[1];
            double value = Double.parseDouble(parts[2]);
            double threshold = Double.parseDouble(parts[3]);

            if ("温度".equals(type)) {
                TemperatureSensor t = new TemperatureSensor();
                t.name = name;
                t.value = value;
                t.threshold = threshold;
                devices.add(t);
                sensorMap.put(name, t);
            } else if ("电压".equals(type)) {
                VoltageSensor v = new VoltageSensor();
                v.name = name;
                v.value = value;
                v.threshold = threshold;
                devices.add(v);
                sensorMap.put(name, v);
            } else if ("湿度".equals(type)) {
                HumiditySensor h = new HumiditySensor();
                h.name = name;
                h.value = value;
                h.threshold = threshold;
                devices.add(h);
                sensorMap.put(name, h);
            } else {
                System.out.println("警告，未知设备类型: " + type);
            }
        }

        int alarmCount = 0;

        for (int i = 0; i < devices.size(); i++) {
            Device d = devices.get(i);
            d.start();
            d.collect();
            if (d.isOverLimit()) {
                alarmCount++;
                if (d instanceof Alarmable) {
                    ((Alarmable) d).alarm();
                }
            }
            d.stop();
        }

        Device found = sensorMap.get("车间");
        System.out.println("查询[车间]:" + found.name + " → " + found.value);

        Device notfound = sensorMap.get("变电站");
        if (notfound == null) {
            System.out.println("没有找到[变电站]这个设备");
        } else {
            System.out.println(notfound.name + " → " + notfound.value);
        }

        System.out.println("====== 共 " + devices.size() + " 台设备，" + alarmCount + " 台报警 ======");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("输入需要查询的设备名称(输入 exit 后退出):");
            String input = sc.nextLine();
            if (input.equals("exit")) {
                break;
            }

            Device d = sensorMap.get(input);
            if (d == null) {
                System.out.println("没有找到叫[" + input + "]的设备喵");
            } else {
                System.out.println(d.name + "→ 当前值" + d.value + "(阈值" + d.threshold + ")");
            }
        }
        sc.close();
    }
}