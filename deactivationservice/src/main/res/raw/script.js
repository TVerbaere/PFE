var gui = importing("gui");
var wifi = importing("wifi");

gui.onActivity('MainActivity2', function(){
       if (!wifi.isConnected()) {
              gui.viewById("@+id/button").setEnabled(false);  // same : gui.viewById("button").setEnabled(false);
       }
       else {
              gui.viewById("@+id/button").setEnabled(true);
       }
});