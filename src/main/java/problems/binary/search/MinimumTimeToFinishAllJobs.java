package problems.binary.search;

import java.util.Arrays;

/**
 * 2021年05月08日12:57:33
 * <p>
 * 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。
 * <p>
 * 请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。
 * <p>
 * 返回分配方案中尽可能 最小 的 最大工作时间 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MinimumTimeToFinishAllJobs {
    /**
     * 二分查找，找到满足条件的值
     */
    public int minimumTImeRequired(int[] jobs, int k) {
        //获得从大到小排序的数组
        Arrays.sort(jobs);
        int low = 0;
        int high = jobs.length - 1;
        while (low < high) {
            int temp = jobs[low];
            jobs[low] = jobs[high];
            jobs[high] = temp;
            low++;
            high--;
        }

        //二分查找区间范围为工时最大值和工时之和
        int l = jobs[0];
        int r = Arrays.stream(jobs).sum();
        while(l < r) {
            int mid = (l + r) >> 1;
            if(check(jobs, k, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public boolean check(int[] jobs, int k, int limit) {
        int[] workloads = new int[k];
        return backtrack(jobs,  workloads, 0, limit);
    }

    /**
     * 在limit时间内，k个工人是否可以完成前i个任务
     * @param jobs
     * @param workloads
     * @param i
     * @param limit
     * @return
     */
    public boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if(i >= jobs.length) {
            return true;
        }
        int cur = jobs[i];
        for(int j = 0; j < workloads.length; ++j) {
            if (workloads[j] + cur <= limit){
                workloads[j] += cur;
                //循环内递归，会分叉，遍历所有情况
                if(backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                //上述递归结果为false，代表给j工人分配cur任务行不通，就不分配给他
                workloads[j] -= cur;
            }

            //当前工人没有分配到任务，则后面也不会分配到任务了，即workloads[j]==0
            //当前工人分配的任务恰好是limit，代表不能再给当前工人继续加了，跳出循环
            if(workloads[j] == 0 || workloads[j] + cur ==limit){
                break;
            }
        }
        return false;
    }

}
