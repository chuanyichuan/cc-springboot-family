package cc.kevinlu.order.holder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ResultHolder {

    private static Map<Class<?>, Map<String, String>> map = new ConcurrentHashMap<>();

    public static void setResult(Class<?> actionClass, String xid, String content) {
        Map<String, String> result = map.get(actionClass);

        if (result == null) {
            synchronized (ResultHolder.class) {
                if (result == null) {
                    result = new ConcurrentHashMap<>();
                    map.put(actionClass, result);
                }
            }
        }

        result.put(xid, content);
    }

    public static String getResult(Class<?> actionClass, String xid) {
        Map<String, String> result = map.get(actionClass);
        if (result != null) {
            return result.get(xid);
        }
        return null;
    }

    public static void removeResult(Class<?> actionClass, String xid) {
        Map<String, String> result = map.get(actionClass);
        if (result != null) {
            result.remove(xid);
        }
    }

}
