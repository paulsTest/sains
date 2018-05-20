package serverTask.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import serverTask.domain.GroceryDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ScrapeService {

    @Autowired
    DisplayService displayService;

    private final String BASEURL = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/";
    private final String HOME = "webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";



    public void getInfo() {

        List<GroceryDto> groceryDtos = new ArrayList<>();
        Document doc;
        try {
            doc = Jsoup.connect(BASEURL + HOME).get();
            Elements links = doc.select("h3");

            for(Element element : links) {

                GroceryDto groceryDto = new GroceryDto();
                String url = createLinkUrl(element);
                Document link = Jsoup.connect(url).get();
                groceryDto.setTitle(scrapeTitle(link));

                groceryDto.setDescription(
                        link.select(".productText").select("p").get(0).text());

                groceryDto.setKcalPer100g(scrapeKcal(link));

                groceryDto.setUnitPrice(
                        link.select(".pricePerUnit").first().childNodes().get(0).toString());


                groceryDtos.add(groceryDto);
            }

            displayService.printResults(groceryDtos);
        }
        catch (IOException i) {

        }
    }

    public String createLinkUrl(Element element) {
        return BASEURL + element.children()
                .first()
                .attributes()
                .get("href")
                .replace("../../../../../../","");
    }

    public String scrapeTitle(Element element) {
        String fullTitle = element.select("title").text();
        return fullTitle.substring(0, fullTitle.indexOf("|") -1);
    }

    public String scrapeKcal(Element element) {
        String kcal = null;
        Elements nutritionTable = element.select(".nutritionTable");
        if(!nutritionTable.isEmpty()) {
            kcal = nutritionTable.select("tbody")
                    .select("tr")
                    .get(1)
                    .select("td")
                    .first()
                    .text();
        }
        return kcal;
    }

}
