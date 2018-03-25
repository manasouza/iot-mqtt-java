import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MqttPublisherClient {

    public static void main(String[] args) {
        try {
            MqttClient mqttClient = new MqttClient("tcp://broker.mqttdashboard.com:1883", "mls-client");
            mqttClient.connect();
            mqttClient.publish("topic/mls", "message hello".getBytes(), 0, false);
            mqttClient.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
