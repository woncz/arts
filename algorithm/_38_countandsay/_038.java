package _38_countandsay;

public class _038 {
    public static void main(String[] args) {
        Solution s = new Solution();
        int n = 4;
        System.out.println(s.countAndSay(n));
    }
}

interface ISolution {
    String countAndSay(int n);
}

class Solution implements ISolution {
    @Override
    public String countAndSay(int n) {
        String ans = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int start = 0;
            int pos = 0;
            while (pos < ans.length()) {
                while (pos < ans.length() && ans.charAt(pos) == ans.charAt(start)) {
                    pos++;
                }
                sb.append(pos - start).append(ans.charAt(start));
                start = pos;
            }
            ans = sb.toString();
        }
        return ans;
    }
}