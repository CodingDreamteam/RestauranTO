package jsonreader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;

public class CJsonReader extends SelectorComposer<Component> {

	private static final long serialVersionUID = 3078745997595341184L;



	public static String getStreetName( ){
			// making url request
			try {
				URL url = new URL("https://connect.squareup.com/v1/me/employees");
				// making connection
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Authorization", "Bearer sq0atp-3Dr5hBRkfws1_Nv5PV7zxQ");
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}

				// Reading data's from url
			   BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				String out="";
				System.out.println("Output from Server .... \n");
				while ((output = br.readLine()) != null) {
					System.out.println(output);
					out=out+output;
				}
				// Converting Json formatted string into JSON object
				//JSONObject json = (JSONObject) JSONSerializer.toJSON(out);
				JSONArray results=(JSONArray) JSONSerializer.toJSON(out);
				JSONObject rec = results.getJSONObject(0);
				JSONArray address_components=rec.getJSONArray("ID de personal");
				for(int i=0;i<address_components.size();i++){
				JSONObject rec1 = address_components.getJSONObject(i);
				//trace(rec1.getString("long_name"));
				JSONArray types=rec1.getJSONArray("types");
				String comp=types.getString(0);

				if(comp.equals("ID de personal")){
					System.out.println("city ————-"+rec1.getString("long_name"));
				}
				else if(comp.equals("Nombre de personal")){
					System.out.println("country ———-"+rec1.getString("long_name"));
				}
				}
				String formatted_address = rec.getString("formatted_address");
				conn.disconnect();
				return formatted_address;		
				
			} catch (MalformedURLException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			return "Error fetching Street Name";
   }

}