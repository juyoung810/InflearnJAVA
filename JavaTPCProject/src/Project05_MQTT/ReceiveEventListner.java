package Project05_MQTT;
import org.eclipse.paho.client.mqttv3.MqttMessage;
public interface ReceiveEventListner {
    public void recvMsg(String topic, MqttMessage msg);

}
