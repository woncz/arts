package _k1730_minimumlimitofballsinabag;

public class _k1730 {

	public static void main(String[] args) {
		int[] nums = {9};
		int maxOperations = 2;
        ISolution solution = new Solution();
        System.out.println(solution.minimumSize(nums, maxOperations));

        nums = new int[]{2,4,8,2};
        maxOperations = 4;
        System.out.println(solution.minimumSize(nums, maxOperations));

	}
}

interface ISolution {
    int minimumSize(int[] nums, int maxOperations);
}

class Solution implements ISolution {
    public int minimumSize(int[] nums, int maxOperations) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(x, max);
        }
        int left = 0, right = max;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(nums, maxOperations, mid)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    boolean check(int[] nums, int mo, int each) {
        // 操作次数
        long cnt = 0;
        for (int x : nums) {
            cnt += (x - 1) / each;
        }
        // 操作次数 与 最多次数 比较
        return cnt <= mo;
    }
}
