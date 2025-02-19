package _624_maximumdistanceinarrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _624 {
    public static void main(String[] args) {
        ISolution s = new Solution();
        List<List<Integer>> data = new ArrayList<>();
        data.add(Arrays.asList(1,4));
        data.add(Arrays.asList(0,5));
        System.out.println(s.maxDistance(data));

        data.clear();
        data.add(Arrays.asList(1, 2, 3));
        data.add(Arrays.asList(4,5));
        data.add(Arrays.asList(1, 2, 3));
        System.out.println(s.maxDistance(data));
    }
}

interface ISolution {
    int maxDistance(List<List<Integer>> arrays);
}

class Solution implements ISolution {
    @Override
    public int maxDistance(List<List<Integer>> arrays) {
        int ans = 0;
        int lo = Integer.MAX_VALUE / 2, hi = Integer.MIN_VALUE / 2;
        for (List<Integer> a : arrays) {
            int l = a.get(0);
            int h = a.get(a.size() - 1);
            ans = Math.max(ans, Math.max(h - lo, hi - l));
            lo = Math.min(lo, l);
            hi = Math.max(hi, h);
        }
        return ans;
    }
}

