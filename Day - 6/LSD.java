/**
 * @author SaiKotesh0102
 */

public class LSD {
	private static final int T_FS = 256;
	
	public LSD() {

	}

	public static String[] sort(String[] a, int w) {
        int n = a.length;
        int R = T_FS;   // extend ASCII alphabet size
        String[] aux = new String[n];

        for (int d = w-1; d >= 0; d--) {
            // sort by key-indexed counting on dth character

            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < n; i++) {
				count[a[i].charAt(d) + 1]++;
			}
            // compute cumulates
            for (int r = 0; r < R; r++) {
				count[r+1] += count[r];
			}  
            // move data
            for (int i = 0; i < n; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];
			}
            // copy back
            for (int i = 0; i < n; i++) {
				a[i] = aux[i];
			}
		}
		return a;
    }
}