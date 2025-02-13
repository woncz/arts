package _k1742_maximumnumberofballsinabox;

public class _k1742 {
    public static void main(String[] args) {
        ISolution s = new Solution();
        int l = 1, h = 10;
        System.out.println(s.countBalls(l, h));
    }
}

interface ISolution {
    int countBalls(int lowLimit, int highLimit);
}

class Solution implements ISolution {
    public int countBalls(int lowLimit, int highLimit) {
        int ans = 0;
        int[] cache = new int[46];
        for (int i = lowLimit; i<= highLimit; i++) {
            ans = Math.max(++cache[no(i)], ans);
        }
        return ans;
    }

    int no(int no) {
        int ans = 0;
        while (no > 0) {
            ans += no % 10;
            no /= 10;
        }
        return ans;
    }
}
