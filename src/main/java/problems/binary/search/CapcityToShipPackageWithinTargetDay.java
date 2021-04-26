package problems.binary.search;

import java.util.Arrays;

/**
 * 2021年04月26日22:39:26
 *
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 *
 * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
 *
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：weights = [1,2,3,4,5,6,7,8,9,10], D = 5
 * 输出：15
 * 解释：
 * 船舶最低载重 15 就能够在 5 天内送达所有包裹，如下所示：
 * 第 1 天：1, 2, 3, 4, 5
 * 第 2 天：6, 7
 * 第 3 天：8
 * 第 4 天：9
 * 第 5 天：10
 *
 * 请注意，货物必须按照给定的顺序装运，因此使用载重能力为 14 的船舶并将包装分成 (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) 是不允许的。
 * 示例 2：
 *
 * 输入：weights = [3,2,2,4,1,4], D = 3
 * 输出：6
 * 解释：
 * 船舶最低载重 6 就能够在 3 天内送达所有包裹，如下所示：
 * 第 1 天：3, 2
 * 第 2 天：2, 4
 * 第 3 天：1, 4
 * 示例 3：
 *
 * 输入：weights = [1,2,3,1,1], D = 4
 * 输出：3
 * 解释：
 * 第 1 天：1
 * 第 2 天：2
 * 第 3 天：3
 * 第 4 天：1, 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CapcityToShipPackageWithinTargetDay {
    public int shipWithinDays(int[] weights, int D) {
        //二分查找左边界为数组的最大元素
        int left = Arrays.stream(weights).max().getAsInt();
        //二分查找右边界为数组所有元素的和
        int right = Arrays.stream(weights).sum();

        while(left<right) {
            int mid = (left + right) / 2;

            // need代表需要分多少批
            int need = 1;
            // cur代表当前批的累积重量
            int cur = 0;
            for(int weight : weights) {
                //当累积重量大于单批上限时
                if(cur + weight > mid) {
                    //将批数加一
                    ++need;
                    //开启新一批，将当前批重量清零
                    cur = 0;
                }
                //添加当前遍历货物的重量
                cur += weight;
            }

            //当前遍历需要次数小于D时，目标数字在左半区
            if(need <= D) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {

    }
}
