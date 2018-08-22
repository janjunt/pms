(function (window, $) {
    'use strict';


    $.extend($.fn.datepicker.defaults, {
        autoclose: true,
        todayBtn: 'linked',
        todayHighlight: true,
        language: 'zh-CN',
        format: 'yyyy-mm-dd'
    });
    $.extend($.fn.datepicker.dates['zh-CN'], {
        format: 'yyyy-mm-dd'
    });
})(window, jQuery);