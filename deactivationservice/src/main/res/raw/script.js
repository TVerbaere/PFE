gui.onActivity('MainActivity', function(){
       if (!gui.isNetworkConnected()) {
              gui.disabledMenuItemwithId('menu_save_page');
       }
       else {
              gui.disabledMenuItemwithId('menu_save_page');
       }

});