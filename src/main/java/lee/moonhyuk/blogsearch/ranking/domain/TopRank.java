package lee.moonhyuk.blogsearch.ranking.domain;

import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

@Component
public class TopRank {
    private final ConcurrentSkipListMap<Pair, String> sortedMap;
    private final ConcurrentHashMap<String, Long> hashMap;

    private static class Pair implements Comparable<Pair> {
        Long value;
        String key;

        Pair(String key, Long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Pair o) {
            int valueComparison = o.value.compareTo(value);
            return valueComparison != 0 ? valueComparison : key.compareTo(o.key);
        }
    }

    public TopRank() {
        this.sortedMap = new ConcurrentSkipListMap<>();
        this.hashMap = new ConcurrentHashMap<>();
    }

    public void insert(String key, Long value) {
        if(hashMap.containsKey(key)) {
            Pair oldPair = new Pair(key, hashMap.get(key));
            sortedMap.remove(oldPair);
        }

        hashMap.put(key, value);
        sortedMap.put(new Pair(key, value), key);
    }

    public List<Map.Entry<String, Long>> topNEntries(int n) {
        List<Map.Entry<String, Long>> topN = new ArrayList<>();
        Iterator<Map.Entry<Pair, String>> iterator = sortedMap.entrySet().iterator();

        for (int i = 0; i < n && iterator.hasNext(); i++) {
            Map.Entry<Pair, String> entry = iterator.next();
            topN.add(new AbstractMap.SimpleEntry<>(entry.getValue(), entry.getKey().value));
        }

        return topN;
    }
}