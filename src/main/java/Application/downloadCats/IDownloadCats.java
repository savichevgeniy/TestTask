package Application.downloadCats;

import Application.domain.CatImage;

import java.io.IOException;
import java.util.List;

public interface IDownloadCats {
    void downloadCatImages(List<CatImage> catImages, int limit) throws IOException;
    void downloadCatImage(CatImage catImage) throws IOException;
}
