package in.parapengu.commons.utils;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffectType;

public class StringUtils {

    public static String phoneticStringArray(String[] a) {
        String s = "";
        int done = 0;
        for (String m : a) {
            done++;
            if (done == a.length && done != 1) {
                s = s + " and " + m;
            } else if (done == 1) {
                s = m;
            } else {
                s = s + ", " + m;
            }
        }
        return s;
    }

    public static DyeColor convertStringToDyeColor(String string) {
        if (string == null) {
            return null;
        }
        for (DyeColor dye : DyeColor.values()) {
            if (dye.name().replaceAll("_", " ").equalsIgnoreCase(string) || dye.name().equalsIgnoreCase(string)) {
                return dye;
            }
        }

        return null;
    }

    public static ChatColor convertDyeColorToChatColor(DyeColor dye) {
        switch (dye) {
            case WHITE:
                return ChatColor.WHITE;
            case ORANGE:
                return ChatColor.GOLD;
            case MAGENTA:
                return ChatColor.LIGHT_PURPLE;
            case LIGHT_BLUE:
                return ChatColor.AQUA;
            case YELLOW:
                return ChatColor.YELLOW;
            case LIME:
                return ChatColor.GREEN;
            case PINK:
                return ChatColor.LIGHT_PURPLE;
            case GRAY:
                return ChatColor.GRAY;
            case SILVER:
                return ChatColor.GRAY;
            case CYAN:
                return ChatColor.DARK_AQUA;
            case PURPLE:
                return ChatColor.DARK_PURPLE;
            case BLUE:
                return ChatColor.BLUE;
            case BROWN:
                return ChatColor.GOLD;
            case GREEN:
                return ChatColor.DARK_GREEN;
            case RED:
                return ChatColor.DARK_RED;
            case BLACK:
                return ChatColor.BLACK;
        }

        return ChatColor.WHITE;
    }

    public static ChatColor convertStringToChatColor(String string) {
        if (string == null) {
            return null;
        }
        for (ChatColor color : ChatColor.values()) {
            if (color.name().replaceAll("_", " ").equalsIgnoreCase(string) || color.name().equalsIgnoreCase(string)) {
                return color;
            }
        }

        return null;
    }

    public static String genPrefix(String prefix, ChatColor color) {
        return ChatColor.WHITE + "[" + color + prefix + ChatColor.WHITE + "] ";
    }

    public static Material convertStringToMaterial(String string) {
        if (string == null) {
            return null;
        }

        for (Material option : Material.values()) {
            if (option.name().replaceAll("_", " ").equalsIgnoreCase(string) || option.name().equalsIgnoreCase(string)) {
                return option;
            }
        }

        return Material.AIR;
    }

    public static Enchantment convertStringToEnchantment(String string) {
        if (string == null) {
            return null;
        }

        for (Enchantment option : Enchantment.values()) {
            if (option.getName().replaceAll("_", " ").equalsIgnoreCase(string) || option.getName().equalsIgnoreCase(string)) {
                return option;
            }
        }

        return null;
    }

    public static PotionEffectType convertStringToPotionEffectType(String string) {
        if (string == null) {
            return null;
        }

        for (PotionEffectType option : PotionEffectType.values()) {
            if (option != null && option.getName() != null && (option.getName().replaceAll("_",
                    " ").equalsIgnoreCase(string) || option.getName().equalsIgnoreCase(string))) {
                return option;
            }
        }

        return null;
    }

    public static Color convertHexStringToColor(String string) {
        if (string == null) {
            return null;
        }
        if (!string.substring(0, 1).equals("#")) {
            string = "#" + string;
        }
        return Color.fromRGB(Integer.valueOf(string.substring(1, 3), 16), Integer.valueOf(string.substring(3, 5), 16),
                Integer.valueOf(string.substring(5, 7), 16));
    }

    public static String formatTime(int originalTime) {
        int time = originalTime;
        int hours = (time - time % (60 * 60)) / 60 / 60;
        String hS = "" + hours;
        if (hours < 10) {
            hS = "0" + hours;
        }

        time = time - (hours * 60 * 60);
        int minutes = (time - time % 60) / 60;
        String mS = "" + minutes;
        if (minutes < 10) {
            mS = "0" + minutes;
        }

        time = time - (minutes * 60);
        int seconds = time;
        String sS = "" + seconds;
        if (seconds < 10) {
            sS = "0" + seconds;
        }

        String text = mS + ":" + sS;
        if (hours > 0) {
            text = hS + ":" + text;
        }

        return text;
    }

    public static String technicalName(String original) {
        return original.toUpperCase().replace(" ", "_");
    }

    public static String fromTechnicalName(String original) {
        return original.replace("_", " ").toLowerCase();
    }

    public static String colorize(String string) {
        return string.replace("&", "§").replace("`", "§");
    }

    public static String trim(String string, int length) {
        if (string.length() > length) {
            string = string.substring(0, length);
        }

        return string;
    }

    public static String[] trim(String string, int length, int sections) {
        String[] result = new String[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = "";
        }

        if (string.length() == 0) {
            return result;
        }

        int section = length / sections;
        if (string.length() <= section) {
            result[(result.length > 1 ? 1 : 0)] = string;
            return result;
        }

        int count = sections - 1;
        boolean empty = true;
        while (empty && count > 0) {
            if (string.length() > section * count) {
                for (int i = 0; i * section <= string.length(); i++) {
                    int start = i * section;
                    int end = (start + section > string.length() ? string.length() : start + section);
                    result[i] = string.substring(start, end);
                }
            }

            empty = allEmpty(result);
            count--;
        }

        return result;
    }

    private static boolean allEmpty(String[] strings) {
        for (String string : strings) {
            if (!string.equals("")) {
                return false;
            }
        }

        return true;
    }

}
