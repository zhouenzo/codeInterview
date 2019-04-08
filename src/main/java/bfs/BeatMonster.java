package bfs;

/**
 * @author enzo
 * @date 2019/4/7 15:23
 * 腾讯2019年春招正式批笔试题第三题，考bfs算法。bfs是深度优先遍历
 * 题目：
 * 小q要经过峡谷，但会遇到n个怪兽，怪兽有武力值和可以贿赂所需的金币，如果贿赂了怪兽就能得到它的保护
 * 如果保护自己的怪兽武力值之和小于现在的怪兽，如果不贿赂就会招到攻击。问安全穿过峡谷最少交多少钱。
 *
 * 这里每经过一个关卡：遇到的怪兽武力值如果大于保护怪兽的武力总和，只能老老实实的交钱，如果大于等于武力值可以选择雇佣
 * 或者跳过，最后要使所花费的最少。
 * 这种情况不能用贪心算法，因为找不到可以贪心的策略。
 *
 * 一般出现这种选择的情况，都使用深度优先遍历，就是找出所有的选择情况，比较花费最少的金币。
 * 关键点是确定每一步可以有的选择，和每一步需要保存的值。
 * 这个题目中，需要知道在这个关卡的武力值总和、关卡数量（相当于遍历的结尾），当前关卡的位置
 *
 * 这种类型也可以用bfs算法来解决，bfs算法的关键点在于使用队列保存每一步的所有可能，一直推到最后的结果。
 *
 * 一般dfs使用递归或者栈的方式实现，bfs使用队列的方式实现。注意dfs使用栈的方式实现时，要先push右子树，因为栈是
 * 后进先出的。
 **/

public class BeatMonster {
    public static int[] d = {1,2,4,8};
    public static int[] p = {1,2,1,2};
    public int getMinCostByBfs(int cursor,int sum,int len){
        int res = 0;
        if(cursor == len){
            if(sum < d[cursor]){
                return p[cursor];
            }
            return 0;
        }
        if(sum < d[cursor]){
            res += p[cursor] + getMinCostByBfs(cursor + 1,sum + d[cursor],len);
        }else{
            res += p[cursor] + Math.min(getMinCostByBfs(cursor + 1,sum + d[cursor],len),
                     getMinCostByBfs(cursor + 1,sum,len));
        }
        return res;
    }

    public static void main(String[] args) {
        BeatMonster b = new BeatMonster();
        System.out.println(b.getMinCostByBfs(0,0,d.length - 1));
    }
}
