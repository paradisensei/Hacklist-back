package org.hacklist.parser.impl;

import org.hacklist.model.Hack;
import org.hacklist.model.enums.Category;
import org.hacklist.model.enums.City;
import org.hacklist.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

/**
 * @author Aidar Shaifutdinov.
 *
 * Doesn`t work, because the website has been updated
 * TODO update
 */
@Component
public class ItEventsParser implements Parser {

    @Value("${itevents.url}")
    private String websiteUrl;

    @Value("${stub_image}")
    private String stubImage;

    @Override
    public Collection<Hack> parse() throws IOException {
        Document mainPage = Jsoup.connect(websiteUrl).get();
        // event blocks on the main page
        Elements events = mainPage.getElementsByClass("vevent");
        Collection<Hack> result = new LinkedList<>();
        for (Element eventBlock : events) {
            Document eventPage = getEventPage(eventBlock);

            if (eventPage == null) {
                continue;
            }

            String eventRegistrationUrl = getRegistrationUrl(eventPage);

            if (eventRegistrationUrl == null) {
                continue;
            }

            String eventTitle = getEventTitle(eventPage);

            if (eventTitle == null) {
                continue;
            }

            String eventOrganizer = getEventOrganizer(eventPage);

            if (eventOrganizer == null) {
                continue;
            }

            // get event city and address
            Elements adr = eventPage.select(".adr");
            if (CollectionUtils.isEmpty(adr)) {
                continue;
            }

            City eventCity = getEventCity(adr);

            if (eventCity == null) {
                continue;
            }

            String eventAddress = getEventAddress(adr);

            if (eventAddress == null) {
                eventAddress = "-";
            }

            String eventDescription = getEventDescription(eventPage);

            String eventImage = getEventImage(eventPage);

            Date eventDate = getEventDate(eventPage);

            if (eventDate == null) {
                continue;
            }

            Hack hack = new Hack();
            hack.setTitle(eventTitle);
            hack.setCity(eventCity);
            hack.setCategory(Category.COMMON);
            hack.setAddress(eventAddress);
            hack.setOrganizer(eventOrganizer);
            hack.setDate(eventDate);
            hack.setDescription(eventDescription);
            hack.setImageUrl(eventImage);
            hack.setUrl(eventRegistrationUrl);

            result.add(hack);
        }
        return result;
    }

    private Document getEventPage(Element eventBlock) {
        String link = eventBlock.select("a.summary").attr("href");
        try {
            return Jsoup.connect(websiteUrl + link).get();
        } catch (IOException ignored) {
            return null;
        }
    }

    private String getRegistrationUrl(Document eventPage) {
        Elements boxReg = eventPage.getElementsByClass("box_register");
        if (!CollectionUtils.isEmpty(boxReg)) {
            Elements regLink = boxReg.first().select("a");
            if (!CollectionUtils.isEmpty(regLink)) {
                String url = regLink.attr("href");
                if (url.length() < 250) {
                    return url;
                }
            }
        }
        return null;
    }

    private String getEventTitle(Document eventPage) {
        String eventTitle = eventPage.select(".vevent .caption").first().text();
        return eventTitle.length() <= 150 ? eventTitle : null;
    }

    private String getEventOrganizer(Document eventPage) {
        Elements org = eventPage.select(".organizer span");
        if (!CollectionUtils.isEmpty(org)) {
            String organizer = org.first().text();
            return organizer.length() <= 100 ? organizer : null;
        }
        return null;
    }

    private City getEventCity(Elements adr) {
        Elements reg = adr.first().select(".region");
        if (!CollectionUtils.isEmpty(reg)) {
            return City.getCity(reg.first().text().split(" , ")[0]);
        }
        return null;
    }

    private String getEventAddress(Elements adr) {
        Elements str = adr.first().select(".street-adress");
        if (!CollectionUtils.isEmpty(str)) {
            String address = str.first().text();
            return address.length() <= 100 ? address : null;
        }
        return null;
    }

    private String getEventDescription(Document eventPage) {
        Element anons = eventPage.select(".anons").first();
        String description = anons.select("p").first().text();
        return description != null ? description : "-";
    }

    private String getEventImage(Document eventPage) {
        Element anons = eventPage.select(".anons").first();
        Elements img = anons.select("img");

        if (!CollectionUtils.isEmpty(img)) {
            String imageUrl = websiteUrl + img.first().attr("src");
            return imageUrl.length() <= 250 ? imageUrl : stubImage;
        }
        return stubImage;
    }

    private Date getEventDate(Document eventPage) {
        String eventDate = eventPage.select("#event_end_date").first().text()
                .replaceAll("\\(.+\\), ", "");

        DateFormat dateFormat = new SimpleDateFormat(
                "dd MMMM yyyy HH:mm", new Locale("ru"));

        try {
            return dateFormat.parse(eventDate);
        } catch (ParseException ignored) {
            return null;
        }
    }

}
