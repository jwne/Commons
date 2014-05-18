package in.parapengu.commons.nms;

import org.bukkit.Bukkit;

public class NMSUtil {

	public static Class<?> getClass(String string) {
		try {
			return Class.forName(string);
		} catch(ClassNotFoundException ex) { /* nothing */ }
		return null;
	}

	public static Class<?> getNMS(String string) {
		return getClass("net.minecraft.server." + getVersion() + (string.startsWith(".") ? string : "." + string));
	}

	public static Class<?> getCraft(String string) {
		return getClass("org.bukkit.craftbukkit." + getVersion() + (string.startsWith(".") ? string : "." + string));
	}

	public static String getVersion() {
		return Bukkit.getServer().getClass().getPackage().getName().replace(".", "," + "" + "").split(",")[3];
	}

}
