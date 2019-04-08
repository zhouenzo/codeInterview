package array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author enzo
 * @date 2019/4/3 20:34
 * 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中所有重复出现的数。
 *
 * 分析：
 * 1、首先想到的是可以使用一个hashmap来保存数字和其出现的次数，最后输出即可。这种情况是以牺牲O（n）的空间
 * 来得到的时间算法复杂度O（n）
 * 2、对于这种找重复数的题，可以先排序，然后遍历数组，如果一个数重复出现，那么紧跟着它的数一定与它相同O（nlogn）
 * 3、
 * 这里的而关键点在于在一个长度为n的数组里的所有数字都在0到n-1的范围内。因此其实如果没有重复的数，则每个数字
 * 排好序后，它们的下标与值相等，也就是找到那些下标与其值不相等的时候就要做一个判断，不需要进行排序，具体过程代码演示。
 **/

public class FindDupCountInArray {

    //找到所有重复的数字
    public Set<Integer> findDupCount(int[] array) throws Exception {
        Set<Integer> result = new HashSet<Integer>();
        int cursor = 0;
        while (cursor < array.length){
            if(array[cursor] >= array.length || array[cursor] < 0){
                throw new Exception("input error");
            }
            //下标与值相同则跳过
            if(array[cursor] == cursor){
                cursor++;
            }else{
                //不同时进行交换准备，如果交换时发现相等则是重复出现，并跳过
                if(array[cursor] == array[array[cursor]]){
                    result.add(array[cursor]);
                    cursor++;
                }else{
                    //不等，进行交换，将对应值放到对应的坐标位置，游标位置不动，对新值进行判断
                    int temp = array[cursor];
                    array[cursor] = array[array[cursor]];
                    array[temp] = temp;
                }
            }
        }
        return result;
    }

    /**
     * 找到任意重复的数字，这里要求不能改变数组，这样就智能遍历数组，关键点任然是
     * 在一个长度为n的数组里的所有数字都在1到n-1的范围内，这样不管怎么写都会有一个数重复。这里用到了二分搜索的方式，因为如果没有重复的数
     * 每个数如果排序都会等于它的下标，这里选择数组中间的数m如果1-m的数目在数组中出现的次数大于m，那么重复的数一定在1-m这个区间，一次次的缩小范围，
     * 这是没有使用辅助空间的。二分搜索，只是多了一个在数组中搜索的步骤。
     * 反之则在另外一个区间
     */
    public int findAnyDupCount(int[] array){
        int start = 0;int end = array.length - 1;
        while(start <= end){
            int middle = (start + end) / 2;
            int count = getCountRange(array,start,middle);
            if(start == end){
                if(count > 1){
                    return start;
                }else{
                    break;
                }
            }
            if(count > middle){
                end = middle;
            }else{
                start = middle + 1;
            }
        }
        return -1;
    }

    private int getCountRange(int[] array, int start, int end) {
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] >= start && array[i] <= end){
                count++;
            }
        }
        return count;
    }
}
