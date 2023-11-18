package aspect;

import cache.Cache;
import cache.ICache;
import entity.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Optional;

@Aspect
public class CasheAspect {

    private final ICache cache = new Cache().getCache();

    @Around("execution(* dao.impl.UserDao.get(..))")
    public Optional<User> aroundGet(ProceedingJoinPoint joinPoint) throws Throwable {
        Optional<User> user = cache.get(joinPoint.getArgs()[0]);
        Object[] args = joinPoint.getArgs();
        if (user.isEmpty()) {
            user = (Optional<User>) joinPoint.proceed(joinPoint.getArgs());
            if (user.isPresent())
                cache.put(user.get().getId(), user.get());
        }
        return user;
    }

    @Around("execution(* dao.impl.UserDao.save(..))")
    public void aroundSave(ProceedingJoinPoint joinPoint) throws Throwable {
         joinPoint.proceed(joinPoint.getArgs());
         User user = (User) joinPoint.getArgs()[0];
         cache.put(user.getId(), user);

    }

    @Around("execution(* dao.impl.UserDao.delete(..))")
    public void aroundDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        joinPoint.proceed(joinPoint.getArgs());
        cache.pop(joinPoint.getArgs()[0]);
        System.out.println("its delete");
    }

}
