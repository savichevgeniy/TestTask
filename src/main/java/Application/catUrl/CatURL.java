package Application.catUrl;

import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component("catURL")
public class CatURL implements ICatURL {
    private static String apiDomain = "https://api.thecatapi.com";
    private static String apiVersion = "v1";


    private String getUrlForResourceMethod(String resourceName, String methodName, String args) {
        String result = "";
        result = String.format("%s/%s/%s/%s", apiDomain, apiVersion, resourceName, methodName);
        if (!args.isEmpty()) result += String.format("?%s",args);
        return result;
    }

    public URL CatUrlForSearchImage(String args) throws MalformedURLException {
        URL url = new URL(getUrlForResourceMethod("images", "search", args));
        return url;
    }

}
