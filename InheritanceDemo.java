class Device {
    String name;
    boolean running = false;

    void start() {
        running = true;
        System.out.println(name + "已启动");
    }

    void stop() {
        running = false;
        System.out.println(name + "已关闭");
    }
}

interface Alarmable {
    void alarm();
}

class Sensor extends Device implements Alarmable {

    double temperature;

    void read(double t) {
        temperature = t;
        System.out.println(name + "读到温度" + temperature + "°C");
    }

    boolean isOverheat() {
        return temperature > 33.0;
    }

    @Override
    void start() {
        super.start();
        System.out.println("(传感器开始采集)");
    }

    @Override
    public void alarm() {
        System.out.println(name + "报警！温度" + temperature + "°C 超温！");
    }
}

class Motor extends Device {
    int rpm;

    @Override
    void start() {
        super.start();
        System.out.println("(电机自检完成,转速归零)");
    }

    void spin(int speed) {
        rpm = speed;
        System.out.println(name + "转速" + rpm + "转/分");
    }
}

class Meter extends Device {
    double value;

    @Override
    void start() {
        super.start();
        System.out.println("(电表开始计量)");
    }

    void readValue(double v) {
        value = v;
        System.out.println(name + "读数:" + String.format("%.2f", value) + "kWh");
    }
}

public class InheritanceDemo {
    public static void main(String[] args) {

        Device[] devices = new Device[3];
        devices[0] = new Sensor();
        devices[1] = new Motor();
        devices[2] = new Meter();

        for (int i = 0; i < devices.length; i++) {
            devices[i].name = "设备" + (i + 1);
            devices[i].start();
        }

        Sensor s = new Sensor();
        s.name = "客厅传感器";
        s.read(35.8);
        s.alarm();

        Motor m = new Motor();
        m.name = "冷却电机";
        m.spin(1500);

        s.stop();
        m.stop();

        Meter mt = new Meter();
        mt.name = "总电表";
        for (int i = 0; i < 1; i++) {
            mt.readValue(Math.random() * 21 + 101);
        }
        mt.stop();
    }
}