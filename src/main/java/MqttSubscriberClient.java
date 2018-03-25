import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MqttSubscriberClient {

    public static void main(String[] args) {

        MqttClient mqttClient = null;
        try {
            mqttClient = new MqttClient("tcp://broker.mqttdashboard.com:1883", "mls-client");

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection Lost: "+ cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    System.out.println("Topic: " + topic);
                    System.out.println(new String(message.getPayload()));
                    System.out.println("QoS: " + message.getQos());
                    System.out.println("Retained: " + message.isRetained());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    try {
                        System.out.println("Delivery message completer: "+token.getMessage());
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            });

            mqttClient.connect();
            mqttClient.subscribe("topic/mls", 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
