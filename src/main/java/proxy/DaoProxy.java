package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DaoProxy implements InvocationHandler {

        private final Object target;

        public DaoProxy(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            // проверяем кэш
            // объект присутствует -> достаем из кэша
            //если отсутствует, вызываем метод дао

            result = method.invoke(target, args);

            return result;
        }
}
