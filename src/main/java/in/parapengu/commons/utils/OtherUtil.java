package in.parapengu.commons.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OtherUtil {

	public static <T> int getIndex(List<T> objects, T object) {
		for(int i = 0; i < objects.size(); i++) {
			T t = objects.get(i);
			if(object.equals(t)) {
				return i;
			}
		}

		return -1;
	}

	public static <T> T getRandom(T... objects) {
		return objects[getRandom(0, objects.length - 1)];
	}

	public static <T> T getRandom(List<T> objects) {
		return objects.get(getRandom(0, objects.size() - 1));
	}

	public static <T> List<T> getRandom(List<T> objects, int size) {
		List<T> clone = Lists.newArrayList(objects);
		List<T> selected = new ArrayList<>();
		while(selected.size() < size && clone.size() > 0) {
			selected.add(clone.get(getRandom(0, clone.size() - 1)));
		}

		return selected;
	}

	public static int getRandom(int min, int max) {
		return new Random().nextInt((max - min) + 1) + min;
	}

	public static double getRandom(double min, double max) {
		return new Random().nextInt((int) (max - min) + 1) + min;
	}

	public static boolean randomBoolean() {
		return new Random().nextBoolean();
	}

	public static <N extends Number> N getHighest(List<N> doubles) {
		N lowest = doubles.get(0);
		for(int i = 1; i < doubles.size(); i++) {
			N value = doubles.get(i);
			if(lowest.doubleValue() < value.doubleValue()) {
				lowest = value;
			}
		}

		return lowest;
	}

	public static <T, N extends Number> List<T> getHighest(Map<T, N> map) {
		List<T> list = Lists.newArrayList(map.keySet());

		List<T> objects = Lists.newArrayList(list);
		N lowest = map.get(objects.get(0));
		for(int i = 1; i < map.size(); i++) {
			T key = list.get(i);
			N value = map.get(key);
			if(lowest.doubleValue() < value.doubleValue()) {
				objects = Lists.newArrayList(key);
				lowest = value;
			} else if(lowest.doubleValue() == value.doubleValue()) {
				objects.add(key);
			}
		}

		return objects;
	}

	public static <T, N extends Number> List<T> getOrderedHighest(Map<T, N> map) {
		return getOrderedHighest(map, true);
	}

	public static <T, N extends Number> List<T> getOrderedHighest(Map<T, N> map, boolean trim) {
		return getOrderedHighest(map, trim);
	}

	public static <T, N extends Number> List<T> getOrderedHighest(Map<T, N> map, int count) {
		return getOrderedHighest(map, -1, true);
	}

	public static <T, N extends Number> List<T> getOrderedHighest(Map<T, N> map, int count, boolean trim) {
		List<T> list = new ArrayList<>();
		if(count == 0) {
			return list;
		}

		Map<T, N> cloned = Maps.newHashMap(map);
		while(cloned.size() > 0 && (count < 0 || list.size() < count)) {
			list.addAll(getHighest(cloned));
		}

		if(trim && count > 0) {
			while(list.size() > count) {
				list.remove(list.size() - 1);
			}
		}

		return list;
	}

	/**
	 * Shorthand for listToEnglishCompound(list, "", "")
	 *
	 * @param list List of strings to compound.
	 * @return String version of the list of strings.
	 */
	public static String listToEnglishCompound(Collection<String> list) {
		return listToEnglishCompound(list, "", "");
	}

	/**
	 * Converts a list of strings to make a nice English list as a string.
	 *
	 * @param list List of strings to compound.
	 * @param prefix Prefix to add before each element in the resulting string.
	 * @param suffix Suffix to add after each element in the resulting string.
	 * @return String version of the list of strings.
	 */
	public static String listToEnglishCompound(Collection<?> list, String prefix, String suffix) {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		for(Object str : list) {
			if(i != 0) {
				if(i == list.size() - 1) {
					builder.append(" and ");
				} else {
					builder.append(", ");
				}
			}

			builder.append(prefix).append(str).append(suffix);
			i++;
		}

		return builder.toString();
	}

}
