package Application.controllers;

import Application.domain.CatImage;
import Application.downloadCats.IDownloadCats;
import Application.searchCat.ISearchCat;
import Application.services.ICatServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
public class ImagesController {
    ISearchCat searchCat;
    IDownloadCats downloadCats;
    ICatServices catServices;

    @Autowired
    public void setCatServices(ICatServices catServices) {
        this.catServices = catServices;
    }

    @Autowired
    public void setDownloadCats(IDownloadCats downloadCats) {
        this.downloadCats = downloadCats;
    }

    @Autowired
    public void setSearchCat(ISearchCat searchCat) {
        this.searchCat = searchCat;
    }

    @RequestMapping("/")
    public String redirectToSearch(){
        return "redirect:/images/search";
    }

    @RequestMapping(value = "/images/search", produces = "application/json")
    @ResponseBody
    public List<CatImage> search (@RequestParam int limit, HttpServletRequest request ) throws IOException {

        String args = request.getQueryString();
        List<CatImage> catImages = searchCat.searchImages(args);
        downloadCats.downloadCatImages(catImages, limit);
        catServices.saveAll(catImages);
        return catImages;
    }

    @RequestMapping(value = "/images", produces = "application/json")
    @ResponseBody
    public List<CatImage> AllImages(HttpServletRequest request) throws IOException {
        List<CatImage> result = catServices.allCats();
        return result;
    }
}
