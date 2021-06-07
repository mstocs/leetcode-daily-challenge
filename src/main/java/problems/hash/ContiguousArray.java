package problems.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * 2021年06月03日13:25:51
 * 给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
 */
public class ContiguousArray {

    public int findMaxLength(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int counter = 0;
        int ans = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                counter++;
            } else {
                counter--;
            }
            if(map.containsKey(counter)) {
                ans = Math.max(ans, counter - map.get(counter));
            } else {
                map.put(counter, i);
            }
        }
        return ans;
    }

    /**
     * 超出时间限制
     */
    public static int findMaxLengTimeLimit (int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        sum[0] = nums[0];
        for(int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }

        if(sum[n-1]==0 || sum[n-1]== n) {
            return 0;
        }

        int predict_max_length = Math.min(sum[n], n-sum[n]+1) * 2;
        for(int i = predict_max_length; i > 2; i-=2) {
            for(int j = 0; j <= n - i; j++) {
                if(sum[j + i] - sum[j] == i / 2) {
                    return i;
                }
            }
        }
        return 2;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,0,0,0,1,1};
        int[] nums2 = {0,1,0,1};
        int[] nums3 = {1,1,1,1,1,1,1,0,0,0,0,1,1,0,1,0,0,1,1,1,1,1,1,1,1,1,0,0,0,0,1,0,0,0,0,1,0,1,0,0,0,1,1,0,0,0,0,1,0,0,1,1,1,1,1,0,0,1,0,1,1,0,0,0,1,0,0,0,1,1,1,0,1,1,0,1,0,0,1,1,0,1,0,0,1,1,1,0,0,1,0,1,1,1,0,0,1,0,1,1};
        int maxLength = new ContiguousArray().findMaxLength(nums3);
        System.out.println(maxLength);
    }
}
