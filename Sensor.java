public class Sensor {
    String name;
    double temperature;

    void read(double t) {
        temperature = t;
        System.out.println(name + "读到温度:" + temperature + "°C");
    }

    boolean isOverheat() {
        return temperature > 33.0;
    }

    public static void main(String[] args) {

        Sensor[] sensors = new Sensor[3];

        sensors[0] = new Sensor();
        sensors[1] = new Sensor();
        sensors[2] = new Sensor();

        sensors[0].name = "客厅传感器";
        sensors[0].read(35.8);
        sensors[1].name = "机房传感器";
        sensors[1].read(23.1);
        sensors[2].name = "天台传感器";
        sensors[2].read(26.4);

        for (int i = 0; i < sensors.length; i++) {

            System.out.println(sensors[i].name + "温度" + sensors[i].temperature + "超温:" + sensors[i].isOverheat());
        }
    }
}