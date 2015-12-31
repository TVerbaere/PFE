var gui = importing("gui");
var wifi = importing("wifi");

gui.onActivity('MainActivity', function(){
       if (!wifi.isConnected()) {
              gui.disabledViewwithId('button');
       }
       else {
              gui.enabledViewwithId('button');
       }
});