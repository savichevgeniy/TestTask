package Application.searchCat;

import Application.domain.CatImage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface ISearchCat {
    public List<CatImage> searchImages(String args) throws MalformedURLException, IOException;
}