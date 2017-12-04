package lab4;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(){
        Endpoint.publish("http://localhost:8080/Lab4PIP_war_exploded/soap",new SoapClass());
    }
}
