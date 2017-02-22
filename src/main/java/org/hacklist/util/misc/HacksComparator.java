package org.hacklist.util.misc;

import org.hacklist.model.Hack;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Neil Alishev
 */
public class HacksComparator {

    public static Comparator<Hack> comparator() {
        return (h1, h2) -> {
            int res = h1.getCity().getPriority().compareTo(h2.getCity().getPriority());
            if (res == 0) {
                res = h1.getDate().compareTo(h2.getDate());
            }
            return res;
        };
    }

    public static Comparator<Hack> comparator(String location) {
        return (h1, h2) -> {
            boolean h1InUserLocation = hackInUserLocation(h1, location);
            boolean h2InUserLocation = hackInUserLocation(h2, location);

            if (h1InUserLocation && h2InUserLocation) {
                return h1.getDate().compareTo(h2.getDate());
            } else if (h1InUserLocation) {
                return -1;
            } else if (h2InUserLocation) {
                return 1;
            }
            return comparator().compare(h1, h2);
        };
    }

    private static boolean hackInUserLocation(Hack hack, String location) {
        return hack.getCity().getName().equals(location)
                || Arrays.asList(hack.getCity().getSynonyms()).contains(location);
    }

}
