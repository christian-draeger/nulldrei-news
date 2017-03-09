package de.nulldrei.server.officialpage;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import de.nulldrei.server.crawler.Crawler;
import de.nulldrei.server.crawler.NewsItem;

@Component
public class OfficialPage {

    @Autowired
    Crawler crawler;

    @Autowired
    NewsItem newsItem;

    @Value("${crawler.official.url}")
    String officialUrl;

    @Value("${crawler.official.headlines}")
    String officialHeadlinesSelector;

    @Value("${crawler.official.conclusions}")
    String officialConclusionsSelector;

    @Value("${crawler.official.pictures}")
    String officialPicturesSelector;

    @Value("${crawler.official.articles}")
    String officialArticlesSelector;

    @Value("${crawler.official.dates}")
    String officialDatesSelector;

    // pnn
    @Value("${crawler.pnn.url}")
    String pnnUrl;

    @Value("${crawler.pnn.headlines}")
    String pnnHeadlinesSelector;

    @Value("${crawler.pnn.conclusions}")
    String pnnConclusionsSelector;

    @Value("${crawler.pnn.pictures}")
    String pnnPicturesSelector;

    @Value("${crawler.pnn.articles}")
    String pnnArticlesSelector;

    @Value("${crawler.pnn.dates}")
    String pnnDatesSelector;


    public List<NewsItem> getItems() {
        List<NewsItem> newsItemList = new ArrayList<>();

        // official page
        Document doc = crawler.getPage(officialUrl);
        Elements articles = doc.select(officialArticlesSelector);

        if (articles.isEmpty()) {
            throw new IllegalArgumentException("invalid response");
        }

        for (int index = 0; index < articles.size(); index++) {
            NewsItem newsItem = new NewsItem();

            newsItem.setAuthor("babelsberg03.de");

            newsItem.setTitle(articles.get(index).select(officialHeadlinesSelector).attr("title"));
            newsItem.setConclusion(StringUtils.abbreviate(articles.get(index).select(officialConclusionsSelector).text(), 250));

            String href = articles.get(index).select(officialHeadlinesSelector).attr("href");
            newsItem.setLink(href);
            newsItem.setDate(articles.get(index).select(officialDatesSelector).text());

            String pictureUrl= articles.get(index).select(officialPicturesSelector).attr("src");
            if (pictureUrl.isEmpty()) {
                newsItem.setPictureUrl("https://upload.wikimedia.org/wikipedia/de/thumb/e/ee/SV_Babelsberg_03.svg/209px-SV_Babelsberg_03.svg.png");
            } else {
                newsItem.setPictureUrl(articles.get(index).select(officialPicturesSelector).attr("src"));
            }

            newsItemList.add(newsItem);
        }


        // pnn
        Document pnnDoc = crawler.getPage(pnnUrl + "/nulldrei/");
        Elements pnnArticles = pnnDoc.select(pnnArticlesSelector);

        if (pnnArticles.isEmpty()) {
            throw new IllegalArgumentException("invalid pnn response");
        }

        for (int index = 0; index < pnnArticles.size(); index++) {
            NewsItem newsItem = new NewsItem();

            newsItem.setAuthor("pnn.de");

            newsItem.setTitle(pnnArticles.get(index).select(pnnHeadlinesSelector).text());
            newsItem.setConclusion(StringUtils.abbreviate(pnnArticles.get(index).select(pnnConclusionsSelector).text(), 250));

            String href = pnnUrl + pnnArticles.get(index).select(pnnHeadlinesSelector).attr("href");
            newsItem.setLink(href);
            // pnnElement.setDate(getDate(href));

            String pictureUrl= pnnArticles.get(index).select(pnnPicturesSelector).attr("src");
            if (pictureUrl.isEmpty()) {
                newsItem.setPictureUrl("http://www.pnn.de/app/base/img/pnn_logo-web_2015.png");
            } else {
                newsItem.setPictureUrl(pnnArticles.get(index).select(pnnPicturesSelector).attr("src"));
            }

            newsItemList.add(newsItem);
        }

        return newsItemList;
    }
}
