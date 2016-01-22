var gui = importing("gui");
var wifi = importing("wifi");

gui.onActivity('MainActivity', function(){

       var toolbar = gui.viewById("@+id/toolbar");
       gui.menuItemById(toolbar, "@+id/action_settings").setEnabled(false);

       if (!wifi.isConnected()) {
              gui.viewById("@+id/button").setEnabled(false);  // same : gui.viewById("button").setEnabled(false);
       }
       else {
              gui.viewById("@+id/button").setEnabled(true);
       }
});