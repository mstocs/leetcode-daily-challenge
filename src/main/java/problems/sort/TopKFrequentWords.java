package problems.sort;

import java.util.*;

/**
 * 2021年05月20日23:03:44
 *
 * 给一非空的单词列表，返回前 k 个出现次数最多的单词。
 *
 * 返回的答案应该按单词出现频率由高到低排序。如果不同的单词有相同出现频率，按字母顺序排序。
 */
public class TopKFrequentWords {

        public List<String> topKFrequentBySort(String[] words, int k) {
            Map<String, Integer> map = new HashMap<>();
            for(String word : words) {
                map.put(word, map.getOrDefault(word, 0) + 1);
            }

            List<String> result = new ArrayList<>();
            for(String word: map.keySet()) {
                result.add(word);
            }
            result.sort(new Comparator<String>() {
                //从大到小排序的话，o2需要大于o1
                @Override
                public int compare(String o1, String o2) {
                    return map.get(o2) - map.get(o1) == 0 ? o1.compareTo(o2) : map.get(o2) - map.get(o1);
                }
            });
            return result.subList(0,k-1);
        }
}
