package problems.backtrack;

/**
 * 2021年06月07日12:50:35
 *
 * 给你一个整数数组 nums 和一个整数 target 。
 *
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 *
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/target-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TargetSum {
    int ans;
    int target;

    //回溯每一种可能的结果，如果结果符合条件，则次数+1
    public int findTargetSumWays(int[] nums, int target) {
        this.target = target;
        backtrack(0, nums, 0);
        return ans;
    }

    public void backtrack(int current, int[] nums, int index) {
        int value = nums[index];
        if(index == nums.length - 1) {
            //最后一位是0的话，正负都需要累加一次
            if(current + value == this.target) {
                this.ans++;
            }
            if(current - value == this.target) {
                this.ans++;
            }
            return;
        }
        backtrack(current-value, nums, index+1);
        backtrack(current+value, nums, index+1);
    }
}
