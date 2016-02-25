package iagl.pfe.testapp;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import iagl.pfe.deactivation.util.Tools;

@Aspect
public class DeactivationAspect {

        @Pointcut("execution(void *.onCreate(android.os.Bundle))")
        public void onCreateEntryPoint() {
        }

        @Pointcut("call(void *.inflate(..))")
        public void inflateMenuEntryPoint() {
        }

        @AfterReturning(value= "inflateMenuEntryPoint() || onCreateEntryPoint()")
        public void startService(JoinPoint joinPoint) {
            Tools.treatJoinPoint(joinPoint);
        }

}
