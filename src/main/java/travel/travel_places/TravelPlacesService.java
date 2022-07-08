package travel.travel_places;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TravelPlacesService {
    public JSONObject getTravelPlaces(String link) {
        JSONObject TravelDatas = new JSONObject();
        try {
            String inline = "";
            URL url = new URL(link);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();

            conn.setRequestMethod("GET");
            conn.connect();
            
            //Get Response  
            if(conn.getResponseCode() == 200) {
                Scanner scan = new Scanner(url.openStream());
                while(scan.hasNext()) {
                    inline += scan.nextLine();
                }

                 //Close the scanner
                scan.close();

                JSONParser parser = new JSONParser();
                TravelDatas = (JSONObject) parser.parse(inline);
                // arr = (JSONArray) TravelDatas.get("data");
                // //Get the required data using its key
                // System.out.println(arr.size());
                // for (int i = 0; i < arr.size(); i++) {
                //     JSONObject TravelData = (JSONObject) arr.get(i);

                //     System.out.println("===========================================");
                //     System.out.println("No: " + (i+1));
                //     System.out.println("Nama: " + TravelData.get("nama"));
                //     System.out.println("Alamat: " + TravelData.get("alamat"));
                //     System.out.println("===========================================");
                // }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return TravelDatas;
    }
}
