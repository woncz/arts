/*
 * Copyright [2020]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package algo4.week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Objects;

public class DutchNationalFlag {

    private int red = 0, white = 1, blue = 2;

    private int[] buckets;

    private int cap;

    private int colorCnt;

    private int swapCnt;

    public DutchNationalFlag(int[] buckets) {
        Objects.requireNonNull(buckets);
        this.buckets = buckets;
        cap = buckets.length;
    }

    private void swap(int left, int right) {
        swapCnt++;
        int t = buckets[right];
        buckets[right] = buckets[left];
        buckets[left] = t;
    }

    private int color(int i) {
        colorCnt++;
        return buckets[i];
    }

    public void quickSort3Way() {
        int lt = 0;
        int gt = cap - 1;
        int current = 0;
        while (current <= gt) {
            int color = color(current);
            if (color < white) swap(lt++, current++);
            else if (color > white) swap(gt--, current);
            else current++;
        }
    }

    public static void main(String[] args) {
        int n = 10;
        int[] buckets = new int[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = StdRandom.uniform(3);
        }
        System.out.println(Arrays.toString(buckets));
        DutchNationalFlag dnf = new DutchNationalFlag(buckets);
        dnf.quickSort3Way();
        System.out.println("after sort call color+" + dnf.colorCnt + "times, call swap=" + dnf.swapCnt + "times");
        System.out.println(Arrays.toString(buckets));
    }
}
