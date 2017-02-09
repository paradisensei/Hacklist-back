package org.hacklist.util.misc;

import org.hacklist.model.Hack;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Neil Alishev
 */
public class HacksComparator {

    public static Comparator<Hack> comparator() {
        return (o1, o2) -> {
            int res = o1.getCity().getPriority().compareTo(o2.getCity().getPriority());
            if (res == 0) {
                res = o1.getDate().compareTo(o2.getDate());
            }
            return res;
        };
    }

    public static Comparator<Hack> comparator(String location) {
        return (o1, o2) -> {
            boolean o1InUserLocation = hackInUserLocation(o1, location);
            boolean o2InUserLocation = hackInUserLocation(o2, location);

            if (o1InUserLocation && o2InUserLocation) {
                return o1.getDate().compareTo(o2.getDate());
            } else if (o1InUserLocation) {
                return -1;
            } else if (o2InUserLocation) {
                return 1;
            }
            return comparator().compare(o1, o2);
        };
    }

    private static boolean hackInUserLocation(Hack hack, String location) {
        return hack.getCity().getName().equals(location)
                || Arrays.asList(hack.getCity().getSynonyms()).contains(location);
    }

}
