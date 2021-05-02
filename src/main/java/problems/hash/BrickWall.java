package problems.hash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2021年05月02日08:32:02
 * 你的面前有一堵矩形的、由 n 行砖块组成的砖墙。这些砖块高度相同（也就是一个单位高）但是宽度不同。每一行砖块的宽度之和应该相等。
 *
 * 你现在要画一条 自顶向下 的、穿过 最少 砖块的垂线。如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 *
 * 给你一个二维数组 wall ，该数组包含这堵墙的相关信息。其中，wall[i] 是一个代表从左至右每块砖的宽度的数组。你需要找出怎样画才能使这条线 穿过的砖块数量最少 ，并且返回 穿过的砖块数量 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/brick-wall
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BrickWall {
    //思路：哈希表
    public int leastBricks(List<List<Integer>> wall) {
        int height = wall.size();
        Map<Integer, Integer> rec = new HashMap<>();
        for(int i = 0; i < wall.size(); i++) {
            int temp = 0;
            //将每一行的缝隙位置保存到哈希表，记得不能保存最后的缝隙
            for(int j = 0; j < wall.get(i).size() - 1; j++) {
                temp += wall.get(i).get(j);
                rec.put(temp, (rec.getOrDefault(temp, 0) + 1));
            }
        }
        int max = 0;
        //遍历找出出现最多的数字
        for(Integer a : rec.keySet()) {
            max = Math.max(rec.get(a), max);
        }
        //返回高度-缝隙出现最多次数
        return height - max;
    }
}
