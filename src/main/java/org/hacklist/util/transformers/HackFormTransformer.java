package org.hacklist.util.transformers;

import org.hacklist.model.Hack;
import org.hacklist.util.forms.HackForm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Neil Alishev
 */
public class HackFormTransformer {

    private static final DateFormat hackDateFormat;
    private static final DateFormat hackFormDateFormat;

    static {
        hackDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        hackFormDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    }

    public static Hack toHack(HackForm hackForm) {
        Hack hack = new Hack();
        hack.setId(hackForm.getId());
        hack.setTitle(hackForm.getTitle());
        hack.setCity(hackForm.getCity());
        hack.setAddress(hackForm.getAddress());
        hack.setDate(toHackDate(hackForm.getDate()));
        hack.setDescription(hackForm.getDescription());
        return hack;
    }

    public static HackForm toHackForm(Hack hack) {
        HackForm hackForm = new HackForm();
        hackForm.setId(hack.getId());
        hackForm.setTitle(hack.getTitle());
        hackForm.setCity(hack.getCity());
        hackForm.setAddress(hack.getAddress());
        hackForm.setDate(toHackFormDate(hack.getDate()));
        hackForm.setDescription(hack.getDescription());
        return hackForm;
    }

    private static Date toHackDate(String date) {
        try {
            String targetDate = hackDateFormat.format(hackFormDateFormat.parse(date));
            return hackDateFormat.parse(targetDate);
        } catch (ParseException ignored) {
            return null;
        }
    }

    private static String toHackFormDate(Date date) {
        return hackFormDateFormat.format(date);
    }

}
