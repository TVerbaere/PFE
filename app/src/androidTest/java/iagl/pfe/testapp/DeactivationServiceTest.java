package iagl.pfe.testapp;

import android.support.v7.widget.Toolbar;
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


    @SmallTest
    public void testGuiFacade_ViewById_Found() throws InterruptedException {
        // Initially, the button and the checkbox are enabled :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

        // The tested script : we import "gui" and we try to disable the button thanks to viewById.
        String script = "var gui = importing(\"gui\"); gui.viewById(\"button\").setEnabled(false);";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(2000);

        // The button is disabled :
        assertFalse(mActivity.findViewById(R.id.button).isEnabled());

        //=================================================

        // Also, it's possible to access a view with "@+id/viewId" :
        script = "var gui = importing(\"gui\"); gui.viewById(\"@+id/box\").setEnabled(false);";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(2000);

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
        Thread.sleep(2000);

        // No change :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

    }


    @SmallTest
    public void testGuiFacade_OnActivity_ForegroundActivity() throws InterruptedException {
        // Initially, the button and the checkbox are enabled :
        assertTrue(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

        // The tested script : we import "gui" and we try to disable the button if the activity is "MyActivity" :
        String script = "var gui = importing(\"gui\"); gui.onActivity(\"MyActivity\", function() { gui.viewById(\"button\").setEnabled(false); } );";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(2000);

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
        Thread.sleep(2000);

        // The button is disabled :
        assertFalse(mActivity.findViewById(R.id.button).isEnabled());
        assertTrue(mActivity.findViewById(R.id.box).isEnabled());

    }

    @SmallTest
    public void testGuiFacade_menuItemById() throws InterruptedException {

        Toolbar toolbar = (Toolbar)mActivity.findViewById(R.id.toolbar);
        
        // Initially, the item is enabled
        assertTrue(toolbar.getMenu().findItem(R.id.action_settings).isEnabled());

        // The tested script : we import "gui" and we try to disable the item action_settings:
        String script = "var gui = importing(\"gui\"); var toolbar = gui.viewById(\"@+id/toolbar\"); gui.menuItemById(toolbar, \"@+id/action_settings\").setEnabled(false);";

        // Executing :
        mActivity.startServicewithScript(script);
        Thread.sleep(2000);

        // The item is disabled
        assertFalse(toolbar.getMenu().findItem(R.id.action_settings).isEnabled());

    }

}