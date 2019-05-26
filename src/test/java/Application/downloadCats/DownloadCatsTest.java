package Application.downloadCats;

import Application.domain.CatImage;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DownloadCatsTest {


    DownloadCats downloadCats = new DownloadCats();

    @Test
    void downloadCatImageAddsPathToCat() throws IOException {
        String url = "https://cdn2.thecatapi.com/images/cbh.jpg";
        CatImage catImage = new CatImage();
        catImage.url = url;
        downloadCats.downloadCatImage(catImage);
        String expectedPath = DownloadCats.filePathFromUrl(url);
        String actualFilePath = catImage.path;
        assertEquals(actualFilePath, expectedPath, "Filepath isn't equals to expected filepath");
    }

    @Test
    void filePathFromUrlTest() throws IOException {
        String url = "https://cdn2.thecatapi.com/images/cbh.jpg";
        String currentDirPath = new File(".").getCanonicalPath();
        String expectedPath = currentDirPath + "\\cbh.jpg";
        String actualFilePath = DownloadCats.filePathFromUrl(url);
        assertEquals(actualFilePath, expectedPath, "Filepath isn't equals to expected filepath");
    }
}