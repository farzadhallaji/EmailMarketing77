package email_marketing.azad.hallaji.farzad.emailmarketing.internet;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {

	public static String getData(RequestPackage p) {
		
		BufferedReader reader = null;
		String uri = p.getUri();
		if (p.getMethod().equals("GET")) {
			uri += "?" + p.getEncodedParams();
		}
		
		try {
			URL url = new URL(uri);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod(p.getMethod());

			JSONObject jsonObject =new JSONObject(p.getParams());
			String params = jsonObject.toString();


			if (p.getMethod().equals("POST")) {
				con.setDoOutput(true);
				OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
                Log.i("Taaag",params);
				writer.write(params);
				writer.flush();
			}

			StringBuilder sb = new StringBuilder();
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		
	}



	
}
