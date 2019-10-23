/*
 * Copyright (C) 2014-2020 Nuhtech Technology(Beijing) Co.,Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary
 * information of Nuhtech Company of China.
 * ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement
 * you entered into with Nuhtech inc.
 *
 */

package _004_medianoftwosortedarrays;

/**
 * @author woncz
 * @date 8/5/2019.
 */
public class Main {
    public static void main(String[] args) {
        int[] nums1 = new int[] {2};
        int[] nums2 = new int[] {1,3,4};
        Solution s = new Solution();
        double median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 2.5");

        nums1 = new int[] {1, 3};
        nums2 = new int[] {4, 5, 6};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 4.0");

        nums1 = new int[] {1, 2};
        nums2 = new int[] {3, 4};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 2.5");

        nums1 = new int[] {1, 3};
        nums2 = new int[] {2, 4};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 2.5");

        nums1 = new int[] {1, 3};
        nums2 = new int[] {2};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 2.0");

        nums1 = new int[] {1};
        nums2 = new int[] {2,3,4};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 2.5");

        nums1 = new int[] {2};
        nums2 = new int[] {1,3,4,5,6};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 3.5");

        nums1 = new int[] {};
        nums2 = new int[] {1,3,4,5,6};
        median = s.findMedianSortedArrays(nums1, nums2);
        System.out.println(median + ", expect 4.0");
    }

}

interface ISolution {

    double findMedianSortedArrays(int[] nums1, int[] nums2);

}

class Solution implements ISolution {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        //
        int left = (len1 + len2 + 1) / 2;
        int right = (len1 + len2 + 2) / 2;

        return (findK(nums1, len1, 0, nums2, len2, 0, left) + findK(nums1, len1, 0, nums2, len2, 0, right)) * 0.5;

    }

    private int findK(int[] nums1, int len1, int offset1, int[] nums2, int len2, int offset2, int k) {
        if (k == 1) {
            return Math.min(offset1 < len1 ? nums1[offset1] : Integer.MAX_VALUE, offset2 < len2 ? nums2[offset2] : Integer.MAX_VALUE);
        }
        int pivot = k / 2;

        int p1 = len1 < offset1 + pivot ? Integer.MAX_VALUE : nums1[offset1 + pivot - 1];
        int p2 = len2 < offset2 + pivot ? Integer.MAX_VALUE : nums2[offset2 + pivot - 1];

        k = k - pivot;

        if (p1 < p2 ) {
            return findK(nums1, len1, offset1 + pivot, nums2, len2, offset2, k);
        } else {
            return findK(nums1, len1, offset1, nums2, len2, offset2 + pivot, k);
        }
    }
}

class Solution2 implements ISolution {

    @Override
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return 0.0;
    }

}
