package hexlet.code.formatter;

import hexlet.code.DefinedStatus;
import hexlet.code.ItemData;

import java.util.Map;

public class StylishConverter {
    public static String convertToStylish(Map<String, ItemData> map) throws Exception {
        StringBuilder mapAsString = new StringBuilder("{\n");
        for (Map.Entry<String, ItemData> entry : map.entrySet()) {
            DefinedStatus status = entry.getValue().getStatus();
            switch (status) {
                case CHANGED:
                    mapAsString.append("  - ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getFistFileValue()).append("\n");
                    mapAsString.append("  + ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getSecondFileValue()).append("\n");
                    break;
                case UNCHANGED:
                    mapAsString.append("    ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getFistFileValue()).append("\n");
                    break;
                case DELETED:
                    mapAsString.append("  - ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getFistFileValue()).append("\n");
                    break;
                case ADDED:
                    mapAsString.append("  + ").append(entry.getKey()).append(": ")
                            .append(entry.getValue().getSecondFileValue()).append("\n");
                    break;
                default:
                    throw new Exception("Wrong status:" + status);
            }
        }
        mapAsString.append("}");
        mapAsString.delete(mapAsString.length() - 1, mapAsString.length()).append("}");
        return mapAsString.toString();
    }
}
