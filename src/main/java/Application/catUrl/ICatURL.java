package Application.catUrl;

import java.net.MalformedURLException;
import java.net.URL;

public interface ICatURL {
    public URL CatUrlForSearchImage(String args) throws MalformedURLException;
}
