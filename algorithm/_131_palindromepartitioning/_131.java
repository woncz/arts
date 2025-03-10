package _131_palindromepartitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _131 {
    public static void main(String[] args) {
        String data = "aab";
        ISolution s = new Solution();
        System.out.println(s.partition(data));

        s = new Solution2();
        System.out.println(s.partition(data));

        s = new Solution3();
        System.out.println(s.partition(data));
    }
}

interface ISolution {
    List<List<String>> partition(String s);
}

// 回溯三问{当前操作->; 子问题-> ;下一层子问题->}
// 注意：每个子串都是回文串
class Solution implements ISolution {
    private List<List<String>> ans = new ArrayList<>();
    private List<String> path = new ArrayList<>();
    private String s = null;

    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    void dfs(int i) {
        // terminator
        if (i == s.length()) {
            System.out.println("valid ans : " + path.toString());
            ans.add(new ArrayList<>(path));
            return;
        }

        // drill down
        for (int j = i; j < s.length(); j++) {
            System.out.println("current string : " + s.substring(i, j + 1) + ", prefix " + path.toString());
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs(j + 1);
                path.remove(path.size() - 1);
            }
        }

    }

    // 相向双指针 判断是否回文串
    boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

class Solution2 implements ISolution {
    private List<List<String>> ans = new ArrayList<>();
    private List<String> path = new ArrayList<>();
    private String s = null;

    @Override
    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0, 0);
        return ans;
    }

    void dfs(int i, int start) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (i < s.length() - 1) {
            dfs(i + 1, start);
        }

        if (isPalindrome(start, i)) {
            path.add(s.substring(start, i + 1));
            dfs(i + 1, i + 1);
            path.remove(path.size() - 1);
        }

    }

    boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}

class Solution3 implements ISolution {
    private final List<List<String>> ans = new ArrayList<>();
    private final List<String> path = new ArrayList<>();
    private String s;

    public List<List<String>> partition(String s) {
        this.s = s;
        dfs(0);
        return ans;
    }

    private void dfs(int i) {
        if (i == s.length()) {
            ans.add(new ArrayList<>(path)); // 复制 path
            return;
        }
        for (int j = i; j < s.length(); j++) { // 枚举子串的结束位置
            if (isPalindrome(i, j)) {
                path.add(s.substring(i, j + 1));
                dfs(j + 1);
                path.remove(path.size() - 1); // 恢复现场
            }
        }
    }

    private boolean isPalindrome(int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}