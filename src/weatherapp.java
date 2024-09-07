import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONObject;

public class weatherapp {
	private static final String API_KEY = "3b19d28f1619e5fe79098d196d375eb7";

	private static final String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

	public static String getWeatherData(String city) {
		String urlString = API_URL + city + "&appid=" + API_KEY;
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();
			connection.disconnect();
			return content.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// ... (same code as above)

	public static String parseWeatherData(String jsonResponse) {
        StringBuilder result = new StringBuilder();

		JSONObject jsono = new JSONObject(jsonResponse);
		JSONObject main = jsono.getJSONObject("main");
		float temperature = main.getFloat("temp") - 273;

		String weather = jsono.getJSONArray("weather").getJSONObject(0).getString("description");

		JSONObject wind = jsono.getJSONObject("wind");
		int windsp = wind.getInt("speed");

		System.out.println("Temperature: " + temperature + "°C");
		System.out.println("Weather: " + weather);
		System.out.println("speed: " +windsp);

		result.append("   ").append("temperature: "+temperature)
				.append("\n").append("   ").append("weather: "+weather)
				.append("\n").append("   ").append("windSpeed: "+windsp);
		return result.toString();
	}

	public static String weatherDiscp(String response){
		JSONObject jsono = new JSONObject(response);
		String weather = jsono.getJSONArray("weather").getJSONObject(0).getString("description");

		return weather;
	}

	public static void main(String[] args) {
		String city ; // Example city
			System.out.println("enter city: ");
			Scanner abc = new Scanner(System.in);
			city = abc.next();
			String response = getWeatherData(city);
			if (response != null) {
				parseWeatherData(response);
			}
	}
}
