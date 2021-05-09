package problems.cutback;

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
    public int minimumTImeRequired(int[] jobs, int k) {
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

    public boolean backtrack(int[] jobs, int[] workloads, int i, int limit) {
        if(i >= jobs.length) {
            return true;
        }
        int cur = jobs[i];
        for(int j = 0; j < workloads.length; ++j) {
            if (workloads[j] + cur <= limit){
                workloads[j] += cur;
                if(backtrack(jobs, workloads, i + 1, limit)) {
                    return true;
                }
                workloads[j] -= cur;
            }

            if(workloads[j] == 0 || workloads[j] + cur ==limit){
                break;
            }
        }
        return false;
    }

}
