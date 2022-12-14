package hexlet.code;



import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import static hexlet.code.DefinedStatus.UNCHANGED;
import static hexlet.code.DefinedStatus.CHANGED;
import static hexlet.code.DefinedStatus.DELETED;
import static hexlet.code.DefinedStatus.ADDED;

public class DiffBuilder {
    public static Map<String, ItemData> genDiff(Map<String, Object> firstMap,
                                                Map<String, Object> secondMap) {

        Map<String, ItemData> diffs = new TreeMap<>();

        Set<String> firstKeys = firstMap.keySet();
        Set<String> secondKeys = secondMap.keySet();
        TreeSet<String> unionKeys = new TreeSet<>(firstKeys);
        unionKeys.addAll(secondKeys);

        for (String key : unionKeys) {
            boolean isFirstMapHasKey = firstMap.containsKey(key);
            boolean isSecondMapHasKey = secondMap.containsKey(key);
            String firstMapKey = String.valueOf(firstMap.get(key));
            String secondMapKey = String.valueOf(secondMap.get(key));
            if (isFirstMapHasKey && isSecondMapHasKey
                    && firstMapKey.equals(secondMapKey)) {
                diffs.put(key, new ItemData(firstMap.get(key), secondMap.get(key), UNCHANGED));
            } else if (isFirstMapHasKey && isSecondMapHasKey
                    && !firstMapKey.equals(secondMapKey)) {
                diffs.put(key, new ItemData(firstMap.get(key), secondMap.get(key), CHANGED));
            } else if (isFirstMapHasKey && !isSecondMapHasKey) {
                diffs.put(key, new ItemData(firstMap.get(key), secondMap.get(key), DELETED));
            } else {
                diffs.put(key, new ItemData(firstMap.get(key), secondMap.get(key), ADDED));
            }
        }

        return diffs;
    }
}
