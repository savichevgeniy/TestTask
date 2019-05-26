package Application.controllers;

import Application.domain.CatImage;
import Application.downloadCats.DownloadCats;
import Application.searchCat.SearchCat;
import Application.services.CatServices;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ImagesController.class)
class ImagesControllerTest {

    @MockBean
    CatServices catServices;

    @MockBean
    DownloadCats downloadCats;

    @MockBean
    SearchCat searchCat;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void redirectToSearch() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("/");
        mockMvc.perform(builder)
                .andExpect(MockMvcResultMatchers.redirectedUrl("/images/search"));
    }

    @Test
    void searchWithLimit() throws Exception {
        int limit = 3;
        String args = String.format("limit=%d", limit);
        List<CatImage> cats = new ArrayList<>();
        cats.add(new CatImage());
        cats.add(new CatImage());

        when(searchCat.searchImages(args)).thenReturn(cats);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/images/search?%s", args))
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        verify(catServices).saveAll(cats);
        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");

        JSONArray array = new JSONArray(result.getResponse().getContentAsString());
        assertTrue(array.length() <= limit, "Incorrect Items Count");
    }

    @Test
    void allImages() throws Exception {
        List<CatImage> cats = new ArrayList<>();
        cats.add(new CatImage());
        cats.add(new CatImage());

        when(catServices.allCats()).thenReturn(cats);


        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/images")
                .accept(MediaType.APPLICATION_JSON_UTF8)).andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status, "Incorrect Response Status");
        verify(catServices).allCats();

        int expectedJsonItemsCount = cats.size();
        JSONArray array = new JSONArray(result.getResponse().getContentAsString());
        assertEquals(array.length(), expectedJsonItemsCount, "Incorrect Items Count");
    }
}