package org.hacklist.parser.impl;

import org.hacklist.model.Hack;
import org.hacklist.model.enums.Category;
import org.hacklist.model.enums.City;
import org.hacklist.parser.Parser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
 */
@Component
public class ItEventsParser implements Parser {
    private static final String WEBSITE = "http://it-events.com";
    private static final String STUB_IMAGE = "https://i.ytimg.com/vi/tNJQY1tP5mY/maxresdefault.jpg";

    private Document mainPage;
    private Elements events;
    private Collection<Hack> result;

    public ItEventsParser() {
        try {
            mainPage = Jsoup.connect(WEBSITE).get();
        } catch (IOException ignored) {
        }
        // event blocks on the main page
        events = mainPage.getElementsByClass("vevent");
        result = new LinkedList<>();
    }

    @Override
    public Collection<Hack> parse() {
        for (Element eventBlock : events) {
            Document eventPage = goToEventPage(eventBlock);

            if (eventPage == null)
                continue;

            String eventRegistrationUrl = getRegistrationUrl(eventPage);

            if (eventRegistrationUrl == null)
                continue;

            String eventTitle = getEventTitle(eventPage);

            if (eventTitle == null)
                continue;

            String eventOrganizer = getEventOrganizer(eventPage);

            if (eventOrganizer == null)
                continue;

            // get event city and address
            Elements adrs = eventPage.select(".adr");
            if (CollectionUtils.isEmpty(adrs))
                continue;

            City eventCity = getEventCity(adrs);

            if (eventCity == null)
                continue;

            String eventAddress = getEventAddress(adrs);

            if (eventAddress == null)
                continue;

            String eventDescription = getEventDescription(eventPage);

            String eventImage = getEventImage(eventPage);

            Date eventDate = getEventDate(eventPage);

            if (eventDate == null)
                continue;

            // hack construction
            Hack hack = new Hack();
            hack.setUrl(eventRegistrationUrl);
            hack.setTitle(eventTitle);
            hack.setOrganizer(eventOrganizer);
            hack.setCity(eventCity);
            hack.setAddress(eventAddress);
            hack.setDescription(eventDescription);
            hack.setImageUrl(eventImage);
            hack.setDate(eventDate);
            hack.setCategory(Category.COMMON);

            result.add(hack);
        }
        return result;
    }

    private Document goToEventPage(Element eventBlock) {
        String link = eventBlock.select("a.summary").attr("href");
        Document eventPage = null;

        // load event page
        try {
            eventPage = Jsoup.connect(WEBSITE + link).get();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        return eventPage;
    }

    private String getRegistrationUrl(Document eventPage) {
        Elements boxReg = eventPage.getElementsByClass("box_register");
        if (!CollectionUtils.isEmpty(boxReg)) {
            Elements regLink = boxReg.first().select("a");
            if (!CollectionUtils.isEmpty(regLink)) {
                String url = regLink.attr("href");
                if (url.length() < 250) {
                    return url;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private String getEventTitle(Document eventPage) {
        String eventTitle = eventPage.select(".vevent .caption").first().text();

        if (eventTitle.length() > 150)
            return null;

        return eventTitle;
    }

    private String getEventOrganizer(Document eventPage) {
        Elements org = eventPage.select(".organizer span");

        if (CollectionUtils.isEmpty(org))
            return null;

        String organizer = org.first().text();
        if (organizer.length() > 100)
            return null;

        return organizer;
    }

    private City getEventCity(Elements adrs) {
        Element adr = adrs.first();
        Elements reg = adr.select(".region");

        if (CollectionUtils.isEmpty(reg))
            return null;

        City city = City.getCity(reg.first().text().split(" , ")[0]);
        if (city == null)
            return null;

        return city;
    }

    private String getEventAddress(Elements adrs) {
        Element adr = adrs.first();
        Elements str = adr.select(".street-adress");
        if (CollectionUtils.isEmpty(str))
            return null;

        String address = str.first().text();
        if (address.length() > 100)
            return null;

        return address;
    }

    private String getEventDescription(Document eventPage) {
        Element anons = eventPage.select(".anons").first();
        return anons.select("p").first().text();
    }

    private String getEventImage(Document eventPage) {
        Element anons = eventPage.select(".anons").first();
        Elements img = anons.select("img");

        if (CollectionUtils.isEmpty(img))
            return STUB_IMAGE;

        String imageUrl = WEBSITE + img.first().attr("src");

        if (imageUrl.length() >= 250)
            return STUB_IMAGE;

        return WEBSITE + img.first().attr("src");
    }

    private Date getEventDate(Document eventPage) {
        String eventDate = eventPage
                .select("#event_end_date")
                .first()
                .text()
                .replaceAll("\\(.+\\), ", "");

        DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy HH:mm", new Locale("ru"));
        Date date = null;

        try {
            date = dateFormat.parse(eventDate);
        } catch (ParseException ignored) {
        }

        return date;
    }
}
