package com.practice.boredomo.utils;

import com.practice.boredomo.R;

/**
 * Util Class for the application
 * @author Aaron Alba
 */
public final class Utils {

    /**
     * Converts a given type chip id to the String name of the chip.
     * @param id The id of the chip.
     * @return The name of the chip.
     */
    public static String typeChipIdConverter(int id) {
        if (id == R.id.education_chip) {
            return "education";
        } else if (id == R.id.recreational_chip) {
            return "recreational";
        } else if (id == R.id.social_chip) {
            return "social";
        } else if (id == R.id.diy_chip) {
            return "diy";
        } else if (id == R.id.charity_chip) {
            return "charity";
        } else if (id == R.id.cooking_chip) {
            return "cooking";
        } else if (id == R.id.relaxation_chip) {
            return "relaxation";
        } else if (id == R.id.music_chip) {
            return "music";
        } else if (id == R.id.busywork_chip) {
            return "busywork";
        } else {
            return "";
        }
    }


    /**
     * Converts a participant chip id to its name.
     * @param id The id of the participant chip.
     * @return the name of the chip.
     */
    public static String participantIdConverter(int id) {
        if (id == R.id.participant_one) {
            return "one";
        } else if (id == R.id.participant_two) {
            return "two";
        } else if (id == R.id.participant_many) {
            return "many";
        } else {
            return "";
        }
    }
}
