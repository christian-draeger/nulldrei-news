package de.nulldrei.server.pnn;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.nulldrei.server.crawler.Crawler;

@Component
public class Pnn {

    @Autowired
    Crawler crawler;

    @Autowired
    PnnElement pnnElement;

    @Value("${crawler.pnn.url}")
    String pnnUrl;

    @Value("${crawler.pnn.headlines}")
    String headlinesSelector;

    @Value("${crawler.pnn.conclusions}")
    String conclusionsSelector;

    @Value("${crawler.pnn.pictures}")
    String picturesSelector;

    @Value("${crawler.pnn.articles}")
    String articlesSelector;

    public List<PnnElement> getPnn() {
        List<PnnElement> pnnElementList = new ArrayList<>();
        Document doc = crawler.getPage(pnnUrl + "/nulldrei/");
        Elements articles = doc.select(articlesSelector);

        if (articles.isEmpty()) {
            throw new IllegalArgumentException("invalid pnn response");
        }

        for (int index = 0; index < articles.size(); index++) {
            PnnElement pnnElement = new PnnElement();

            pnnElement.setTitle(articles.get(index).select(headlinesSelector).text());
            pnnElement.setConclusion(articles.get(index).select(conclusionsSelector).text());
            pnnElement.setLink(pnnUrl + articles.get(index).select(headlinesSelector).attr("href"));

            String pictureUrl= articles.get(index).select(picturesSelector).attr("src");
            if (pictureUrl.isEmpty()) {
                pnnElement.setPictureUrl("http://www.pnn.de/app/base/img/pnn_logo-web_2015.png");
            } else {
                pnnElement.setPictureUrl(articles.get(index).select(picturesSelector).attr("src"));
            }

            pnnElementList.add(pnnElement);
        }

        return pnnElementList;
    }
}
