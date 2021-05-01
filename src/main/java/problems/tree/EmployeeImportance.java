package problems.tree;

import java.util.*;

/**
 * 2021年05月01日17:06:34
 * 给定一个保存员工信息的数据结构，它包含了员工 唯一的 id ，重要度 和 直系下属的 id 。
 *
 * 比如，员工 1 是员工 2 的领导，员工 2 是员工 3 的领导。他们相应的重要度为 15 , 10 , 5 。那么员工 1 的数据结构是 [1, 15, [2]] ，员工 2的 数据结构是 [2, 10, [3]] ，员工 3 的数据结构是 [3, 5, []] 。注意虽然员工 3 也是员工 1 的一个下属，但是由于 并不是直系 下属，因此没有体现在员工 1 的数据结构中。
 *
 * 现在输入一个公司的所有员工信息，以及单个员工 id ，返回这个员工和他所有下属的重要度之和。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/employee-importance
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class EmployeeImportance {
    public int getImportanceByDFS(List<Employee> list, int id) {
        Map<Integer, Employee> map = buildMap(list);
        return dfs(id, map);
    }


    public int getImportanceByBFS(List<Employee> list, int id) {
        Map<Integer, Employee> map = buildMap(list);
        int total = 0;
        // bfs通常使用队列实现
        Queue<Integer> queue = new LinkedList<Integer>();
        // 将根元素添加进入队列
        queue.offer(id);
        // bfs终止条件为队列为空
        while (!queue.isEmpty()) {
            //取出队列头元素，将重要性累加
            int currId = queue.poll();
            Employee employee = map.get(currId);
            total += employee.importance;
            //添加头元素的下属元素到队列中
            for(int subId : employee.subordinates) {
                queue.offer(subId);
            }
        }
        return total;
    }

    private int dfs(int id, Map<Integer, Employee> map) {
        Employee employee = map.get(id);
        //默认值为当前重要性
        int result =  employee.importance;
        //累加下属的重要性
        for(int i : employee.subordinates) {
            result += dfs(i, map);
        }
        return result;
    }

    //构建id:Employee的哈希表
    private Map<Integer, Employee> buildMap(List<Employee> list) {
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee employee :list) {
            map.put( employee.id, employee);
        }
        return map;
    }
}

class Employee {
    public int id;
    public int importance;
    public List<Integer> subordinates;
};
