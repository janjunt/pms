// select2 默认配置
(function( factory ) {
    'use strict';

    factory(jQuery);
})(function ($) {
    'use strict';

    if($.fn.select2){
        $.fn.select2.defaults.set("theme", "bootstrap");
        $.fn.select2.defaults.set("language", "zh-CN");
    }
});

