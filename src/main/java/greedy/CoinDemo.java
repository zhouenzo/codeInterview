package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author enzo
 * @date 2019/4/5 20:53
 * 腾讯2019年春招笔试第1题：
 * 有不同面值的硬币，由输入给出，每种硬币有无数多个，给出m，出门带硬币，怎样带硬币最少，又能够凑出1-m所有面值
 * 这是典型的贪心算法解题，涉及到最大最小等问题时，基本上都用贪心算法解决，典型的有最短路径算法。
 * 既然是贪心，那么为了使带的硬币最少，故每次往钱包里加硬币时，加入比需要凑出的数值小，但最靠近面值的硬币
 * 这里解题的关键点在于一个容器，记录钱包中加入新的硬币后可以凑出的所有数值，这个容器就是贪心算法的关键。
 * 而容器的选择，要根据具体的推导过程，最短路径用的是一个二位数组保存每一步的值。而这里因为如果可以凑出1-i的所有数值
 * 当加入一个面值为j的硬币后，就可以凑出1-i+j的所有数值，这里只需要记录最大的i+j值就可以了，因此只需要用一个int作为容器
 * 记录就行。
 **/

public class CoinDemo {

    public static int getMaxValue(int[] values,int target){
        if(target < values[0]){
            return -1;
        }else if(target >= values[values.length - 1] ){
            return values.length - 1;
        }else{
            for (int i = 0; i < values.length; i++) {
                if(values[i] > target){
                    return i - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[] values = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

       scanner.close();

        Arrays.sort(values);
        ArrayList<Integer> wallet = new ArrayList<>();
        int currentM = 0;
        for (int i = 1; i <= m; i++) {
            if(i <= currentM){
                continue;
            }else{
               while(currentM < i){
                   int addValue = getMaxValue(values,i);
                   if(addValue == -1){
                       System.out.println(-1);
                       return;
                   }
                   wallet.add(values[addValue]);
                   currentM += values[addValue];
               }
               if(currentM >= m){
                   System.out.println(wallet.size());
                   return;
               }
            }

        }
        System.out.println(wallet.size());

    }
}
