package Application.searchCat;

import Application.catUrl.CatURL;
import Application.catUrl.ICatURL;
import Application.domain.CatImage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component("searchCat")
public class SearchCat implements ISearchCat {
    private static final String USER_AGENT = "Mozilla/5.0";

    ICatURL catURL = new CatURL();

    @Override
    public List<CatImage> searchImages(String args) throws IOException {
        List<CatImage> result = new ArrayList<CatImage>();
        URL url = catURL.CatUrlForSearchImage(args);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = connection.getResponseCode();

        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append((inputLine));
            }
            result = searchResponseParse(response.toString());
            in.close();
        }
        else {
            System.out.println("GET request not worked");
        }
        return result;
    }

    private List<CatImage> searchResponseParse(String rawJson) throws JSONException {
        final  JSONArray images = new JSONArray(rawJson);
        List<CatImage> result = new ArrayList<CatImage>();
        for (Object catJson : images) {
            result.add(CatImage.fromJson((JSONObject)catJson));
        }
        return result;
    }
}