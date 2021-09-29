package S191220119.task3.src;

import java.util.Random;

public interface Sorter {

    public void load(int[] elements);
    public void sort();
    public String getPlan();

    public static int[] randomArrange(int[] a) {
        int[] r = new int[a.length];
        System.arraycopy(a, 0, r, 0, a.length);
        Random random = new Random();
        for(int i = 0; i < a.length; i++) {
            int p = random.nextInt(a.length);
            int tmp = r[i];
            r[i] = r[p];
            r[p] = tmp;
        }
        random = null;
        return r;
    }
    
}
