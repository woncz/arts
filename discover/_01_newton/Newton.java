/*
 * Copyright [2017]
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

package _01_newton;

/**
 * 牛顿迭代法
 */
public class Newton {

    public static void main(String[] args) {
        int num = 7;
        double precision =  0.00000001;

        double x = 0.5;
        int cnt = 0;

        // 求平方根
        while (Math.abs(x * x - num) > precision) {
            x = (x + 7 / x) / 2;
            System.out.println("cnt=" + ++cnt + ", x=" + x);
        }

        System.out.println("final x=" + x);

        // 求立方根
        x = 0.5;
        cnt = 0;
        while (Math.abs(x * x * x - num) > precision) {
            x = 1.0 / 3 * (2 * x + num / (x * x));
            System.out.println("cnt=" + ++cnt + ", x=" + x);
        }
        System.out.println("final x=" + x);
    }

}
