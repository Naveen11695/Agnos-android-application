package naveen.agnosbeta.agnos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> website = new ArrayList<String>();

        List<String> android = new ArrayList<String>();

        List<String> cryptocurrency = new ArrayList<String>();

        List<String> Blockchain_Software = new ArrayList<String>();

        List<String> iot_device = new ArrayList<String>();

        expandableListDetail.put("Website Development", website);
        expandableListDetail.put("Android Applications", android);
        expandableListDetail.put("Bitcoin (Cryptocurrency)", cryptocurrency);
        expandableListDetail.put("Blockchain Softwares", Blockchain_Software);
        expandableListDetail.put("IOT (Internet Of Things) Devices", iot_device);
        return expandableListDetail;
    }
}
