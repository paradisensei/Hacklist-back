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
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author Aidar Shaifutdinov.
 */
@Component
public class ItEventsParser implements Parser {

    @Override
    public Collection<Hack> parse() throws IOException {
        String website = "http://it-events.com";
        Document doc = Jsoup.connect(website).get();
        // event blocks on main page
        Elements events = doc.getElementsByClass("vevent");
        Collection<Hack> hacks = new LinkedList<>();
        // stub url
        String stubUrl = "http://s.quickmeme.com/img/1f/1fbf5db595577db3330029140dd253acca1e874e5fb8eb50e3876417f1061870.jpg";
        for (Element e : events) {
            String link = e.select("a.summary").attr("href");
            // load event page
            doc = Jsoup.connect(website + link).get();
            Hack hack = new Hack();
            // get registration url
            Elements boxReg = doc.getElementsByClass("box_register");
            if (!CollectionUtils.isEmpty(boxReg)) {
                Elements regLink = boxReg.first().select("a");
                if (!CollectionUtils.isEmpty(regLink)) {
                    String url = regLink.attr("href");
                    if (url.length() < 250) {
                        hack.setUrl(url);
                    } else {
                        hack.setUrl(stubUrl);
                    }
                } else {
                    hack.setUrl(stubUrl);
                }
            } else {
                hack.setUrl(stubUrl);
            }
            // get event title
            String title = doc.select(".vevent .caption").first().text();
            hack.setTitle(title);
            // get event organizer
            Elements org = doc.select(".organizer span");
            if (!CollectionUtils.isEmpty(org)) {
                hack.setOrganizer(org.first().text());
            } else {
                continue;
            }
            // get event city and address
            Elements adrs = doc.select(".adr");
            if (!CollectionUtils.isEmpty(adrs)) {
                Element adr = adrs.first();
                Elements reg = adr.select(".region");
                if (!CollectionUtils.isEmpty(reg)) {
                    City city = City.getCity(reg.first().text().split(" , ")[0]);
                    if (city == null) {
                        continue;
                    }
                    hack.setCity(city);
                } else {
                    continue;
                }
                Elements str = adr.select(".street-adress");
                if (!CollectionUtils.isEmpty(str)) {
                    hack.setAddress(str.first().text());
                } else {
                    continue;
                }
            } else {
                continue;
            }
            // get event description
            Element anons = doc.select(".anons").first();
            String description = anons.select("p").first().text();
            hack.setDescription(description);
            // get event image
            Elements img = anons.select("img");
            if (!CollectionUtils.isEmpty(img)) {
                String imageUrl = website + img.first().attr("src");
                if (imageUrl.length() < 250) {
                    hack.setImageUrl(website + img.first().attr("src"));
                } else {
                    hack.setImageUrl(stubUrl);
                }
            } else {
                hack.setImageUrl(stubUrl);
            }
            hack.setDate(new Date());
            hack.setCategory(Category.COMMON);
            hacks.add(hack);
        }
        return hacks;
    }

}
