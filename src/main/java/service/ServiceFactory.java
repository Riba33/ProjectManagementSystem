package service;

import model.BaseEntity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory {
    private final static Map<String,BaseService> SERVICEMAP = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseEntity<ID>, ID> BaseService<E, ID> of(Class<E> modelClass) {
        final String modelName = modelClass.getName();
        if (!SERVICEMAP.containsKey(modelName)) {
            SERVICEMAP.put(modelName, new CrudServiceImpl(modelClass));
        }
        return SERVICEMAP.get(modelName);
    }
}
