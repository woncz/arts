package hwod._01_guestnumber;

public class _01 {
    public static void main(String[] args) {
        ISolution s = new Solution();
        int N = 100, target = 99;
        System.out.println(s.guest(N, target));
        target = 1;
        System.out.println(s.guest(N, target));

        for (int i = 0; i <= N; i++) {
            System.out.println(s.guest(N, i));
        }
    }

}

interface ISolution {
    int guest(int N, int target);
}

class Solution implements ISolution {
    public int guest(int N, int target) {
        int left = 0, right = N, mid = 0;
        while (left <= right) {
            mid = (left + right) >>> 1;
            if (mid == target) return mid;  // 找到目标值，返回索引
            else if (mid < target) left = mid + 1; // 目标值在右半部分
            else right = mid - 1; // 目标值在左半部分
        }
        return mid;
    }
}
