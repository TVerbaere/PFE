var gui = importing("gui");
var wifi = importing("wifi");

gui.viewById("@+id/button").setEnabled(true);

gui.onActivity('MainActivity', function(){
       if (!wifi.isConnected()) {
              gui.viewById("@+id/button").setEnabled(false);  // same : gui.viewById("button").setEnabled(false);
       }
       else {
              gui.viewById("@+id/button").setEnabled(true);
       }
});