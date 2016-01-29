package iagl.pfe.testapp;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import iagl.pfe.deactivation.util.MenuOpened;

@Aspect
public class DeactivationAspect {

        @Pointcut("execution(* *.onCreate(..))")
        public void onCreateEntryPoint() {
        }

        @Pointcut("execution(* *.onCreateContextMenu(..))")
        public void onCreateContextMenuEntryPoint() {
        }

        @Pointcut("execution(* *.onCreateOptionsMenu(..))")
        public void onCreateOptionsMenuEntryPoint() {
        }

        @After("onCreateEntryPoint()")
        public void onCreateListener(JoinPoint joinPoint) {
            Activity a = (Activity)joinPoint.getThis();
            Intent intent = new Intent();
            intent.setAction("iagl.pfe.ON_CREATE");
            a.sendBroadcast(intent);
        }

        @AfterReturning(value= "onCreateContextMenuEntryPoint()")
        public void onCreateContextMenuListener(JoinPoint joinPoint) {
            Menu m = (Menu)joinPoint.getArgs()[0];

            if (m != null) {
                MenuOpened.menuOpened = m;
                Activity a = (Activity) joinPoint.getThis();
                Intent intent = new Intent();
                intent.setAction("iagl.pfe.ON_MENU_OPENED");
                a.sendBroadcast(intent);
            }
        }

        @AfterReturning(value= "onCreateOptionsMenuEntryPoint()")
        public void onCreateOptionsMenuListener(JoinPoint joinPoint) {
            Menu m = (Menu)joinPoint.getArgs()[0];

            if (m != null) {
                MenuOpened.menuOpened = m;
                Activity a = (Activity) joinPoint.getThis();
                Intent intent = new Intent();
                intent.setAction("iagl.pfe.ON_MENU_OPENED");
                a.sendBroadcast(intent);
            }
        }

}
