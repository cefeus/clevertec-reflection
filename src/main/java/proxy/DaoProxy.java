package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class DaoProxy implements InvocationHandler {

    private Object target;
    private final Map<String, Method> methods = new HashMap<>();

        public DaoProxy(Object target) {
            this.target = target;
            for(Method method: target.getClass().getDeclaredMethods()) {
                this.methods.put(method.getName(), method);
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // проверяем кэш
            // объект присутствует -> достаем из кэша
            //если отсутствует, вызываем метод дао
            Object result = methods.get(method.getName()).invoke(target, args);

            return result;
        }
}
