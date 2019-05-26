package Application.downloadCats;

import Application.domain.CatImage;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component("downloadCats")
public class DownloadCats implements IDownloadCats{
    public void downloadCatImages(List<CatImage> catImages, int limit) throws IOException {
        catImages = filterDownloadableImagesByLimit(catImages, limit);
        for (CatImage catImage : catImages) {
            downloadCatImage(catImage);
        }
    }

    public void downloadCatImage(CatImage catImage) throws IOException {

        String filepath = filePathFromUrl(catImage.url);
        downloadAndWriteImageIntoLocalFile(catImage.url, filepath);
        catImage.path = filepath;
    }

    public static String filePathFromUrl(String url) throws IOException {
        String homePath = new File(".").getCanonicalPath();
        String filePath = Paths.get(new URL(url).getPath()).getFileName().toString();
        String result = homePath + "\\" + filePath;
        return result;
    }

    private static void downloadAndWriteImageIntoLocalFile(String imageUrl, String filepath) throws IOException {
        URL url = new URL(imageUrl);
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        FileOutputStream fileOutputStream = new FileOutputStream(filepath);
        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
    }

    private static List<CatImage> filterDownloadableImagesByLimit(List<CatImage> catImages, int limit) {
        List<CatImage> result = catImages.stream().limit(limit).collect(Collectors.toList());
        return result;
    }
}