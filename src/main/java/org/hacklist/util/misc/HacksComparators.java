package org.hacklist.util.misc;

import org.hacklist.model.Hack;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Neil Alishev
 */
public class HacksComparators {
    public static Comparator<Hack> withoutLocationComparator() {
        return (o1, o2) -> Integer.valueOf(o1.getCity().getPriority()).compareTo(o2.getCity().getPriority());
    }

    public static Comparator<Hack> withLocationComparator(String location) {
        return (o1, o2) -> {
            boolean o1InUserLocation = hackInUserLocation(o1, location);
            boolean o2InUserLocation = hackInUserLocation(o2, location);

            if (o1InUserLocation && o2InUserLocation) {
                return 0;
            } else if (o1InUserLocation) {
                return -1;
            } else if (o2InUserLocation) {
                return 1;
            } else {
                return Integer.valueOf(o1.getCity().getPriority()).compareTo(o2.getCity().getPriority());
            }
        };
    }

    private static boolean hackInUserLocation(Hack hack, String location) {
        return hack.getCity().getName().equals(location)
                || Arrays.asList(hack.getCity().getSynonyms()).contains(location);
    }
}
