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

package algo4.week3;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;

public class ElemsMoreThanNDivTenTimes {

    private class Element{//辅助空间元素定义，用来记录元素值及其出现次数
        public int element;
        public int count;
        public Element(int e,int c){
            this.element = e;
            this.count = c;
        }
    };
    private Element[] elems = new Element[9]; //申请9个辅助空间


    public ArrayList<Integer> findElements(int[] arrays){
        int n = arrays.length;
        for(int k=0;k<9;k++){
            elems[k] = new Element(0,0); //辅助空间初始化
        }
        for(int i=0;i<n;i++){
            int index = findIndex(arrays[i]);
            if(index >= 0)
                elems[index].count ++;
            else
                addToElems(arrays[i]);
        }
        return verifyElems(arrays);
    }

    private int findIndex(int e){
        for(int k = 0; k<9;k++){
            if(elems[k].element == e)
                return k;
            else if(elems[k].count == 0){
                elems[k].element = e;
                return k;
            }
        }
        return -1;
    }
    private void addToElems(int e){
        boolean insertFlag = false;
        while(!insertFlag){
            for(int k=0; k<9;k++){
                elems[k].count --;
                if(elems[k].count <= 0){
                    elems[k].element = e;
                    elems[k].count = 1;
                    insertFlag = true;
                    break;
                }
            }
        }
    }
    private ArrayList<Integer> verifyElems(int[] arrays){
        int n = arrays.length;
        for(int k = 0; k< 9; k++){
            elems[k].count = 0;
            for(int i = 0; i< n;i++){
                if(arrays[i]==elems[k].element)
                    elems[k].count++;
            }
        }
        ArrayList<Integer> elemList = new ArrayList<Integer>();
        for(int k = 0; k< 9; k++){
            if(elems[k].count > n/10)
                elemList.add(elems[k].element);
        }
        return elemList;
    }

    public static void main(String[] args){
        int n = 20;
        int[] array = new int[n];
        for(int i=0;i<n;i++){
            array[i] = StdRandom.uniform(n);
        }
        System.out.println(Arrays.toString(array));
        ElemsMoreThanNDivTenTimes elems = new ElemsMoreThanNDivTenTimes();
        ArrayList<Integer> elemList =  elems.findElements(array);
        System.out.println(elemList.toString());

        n = 100;
        array = new int[n];
        int step = 11;
        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < step; j++) {
                array[(i - 1) * step + j] = i;
            }
        }
        array[n - 1] = 31;

        System.out.println(Arrays.toString(array));
        elems = new ElemsMoreThanNDivTenTimes();
        elemList =  elems.findElements(array);
        System.out.println(elemList.toString());
    }
}