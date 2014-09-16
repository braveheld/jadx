package jadx.gui.update;

public class VersionComparator {

	public static int checkAndCompare(String str1, String str2) {
		try {
			return compare(clean(str1), clean(str2));
		} catch (NumberFormatException e) {
			return -2;
		}
	}

	private static String clean(String str) {
		String result = str.trim().toLowerCase();
		if (result.charAt(0) == 'v') {
			result = result.substring(1);
		}
		return result;
	}

	public static int compare(String str1, String str2) {
		String[] s1 = str1.split("\\.");
		int l1 = s1.length;
		String[] s2 = str2.split("\\.");
		int l2 = s2.length;

		int i = 0;
		// skip equals parts
		while (i < l1 && i < l2) {
			if (!s1[i].equals(s2[i])) {
				break;
			}
			i++;
		}
		// compare first non-equal ordinal number
		if (i < l1 && i < l2) {
			return Integer.valueOf(s1[i]).compareTo(Integer.valueOf(s2[i]));
		}
		boolean checkFirst = l1 > l2;
		boolean zeroTail = isZeroTail(checkFirst ? s1 : s2, i);
		if (zeroTail) {
			return 0;
		}
		return checkFirst ? 1 : -1;
	}

	private static boolean isZeroTail(String[] arr, int pos) {
		for (int i = pos; i < arr.length; i++) {
			String s = arr[i];
			if (Integer.valueOf(s) != 0) {
				return false;
			}
		}
		return true;
	}
}
