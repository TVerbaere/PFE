package iagl.pfe.testapp;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;


public class DeactivationServiceTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mActivity;

    public DeactivationServiceTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mActivity = getActivity();
    }

    @SmallTest
    public void testGoodInitialization() {
        assertNotNull(mActivity);
    }

    /*
    @SmallTest
    public void testGuiFacade_ViewById_Found() throws InterruptedException {
        // Initially, the button and the checkbox are enabled :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

        // The tested script : we import "gui" and we try to disable the button thanks to viewById.
        String script = "var gui = importing(\"gui\"); gui.viewById(\"button\").setEnabled(false);";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(500);

        // The button is disabled :
        assertFalse(mActivity.findViewById(R.id.button).isEnabled());

        //=================================================

        // Also, it's possible to access a view with "@+id/viewId" :
        script = "var gui = importing(\"gui\"); gui.viewById(\"@+id/box\").setEnabled(false);";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(500);

        // The button is disabled :
        assertFalse(mActivity.findViewById(R.id.box).isEnabled());
    }

    @SmallTest
    public void testGuiFacade_ViewById_NotFound() throws InterruptedException {
        // Initially, the button and the checkbox are enabled :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

        // The tested script : we import "gui" and we try to disable the button2 (not exists) thanks to viewById.
        String script = "var gui = importing(\"gui\"); gui.viewById(\"button2\").setEnabled(false);";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(500);

        // No change :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

    }
*/

    @SmallTest
    public void testGuiFacade_OnActivity_ForegroundActivity() throws InterruptedException {
        // Initially, the button and the checkbox are enabled :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

        // The tested script : we import "gui" and we try to disable the button if the activity is "MyActivity" :
        String script = "var gui = importing(\"gui\"); gui.onActivity(\"MyActivity\", function() { gui.viewById(\"button\").setEnabled(false); } );";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(1500);

        // No change :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

    }

    @SmallTest
    public void testGuiFacade_OnActivity_NotForegroundActivity() throws InterruptedException {
        // Initially, the button and the checkbox are enabled :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

        // The tested script : we import "gui" and we try to disable the button if the activity is "MainActivity" :
        String script = "var gui = importing(\"gui\"); gui.onActivity(\"MainActivity\", function() { gui.viewById(\"button\").setEnabled(false); } );";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(1500);

        // The button is disabled :
        assertFalse(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

    }

}