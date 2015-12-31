import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import iagl.pfe.deactivation.DeactivationService;

public class DeactivationServiceTest extends ServiceTestCase<DeactivationService> {

    public DeactivationServiceTest() {
        super(DeactivationService.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    /**
     * Test basic startup/shutdown of Service
     */
    @SmallTest
    public void testStartable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), DeactivationService.class);
        startService(startIntent);
    }

    /**
     * Test binding to service
     */
    @MediumTest
    public void testBindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), DeactivationService.class);
        IBinder service = bindService(startIntent);
    }

}
