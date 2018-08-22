+(function (factory) {
    'use strict';

    
    factory(window, jQuery);
})(function (global, $) {
    'use strict';

    
    function _handleICheck() {
        if(!$.fn.iCheck){
            return;
        }

        $('.icheck').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue'
        });
    }


    global.App = {
        init: function () {
            _handleICheck();
        }
    };


    $(function () {
       App.init();
    });
});