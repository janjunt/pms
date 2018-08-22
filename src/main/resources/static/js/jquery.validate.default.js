(function(factory) {
    factory(jQuery);
})(function($) {
    if ($.validator) {
        $.validator.setDefaults({
            showErrors: function () {
                $.each(this.successList, function (index, value) {
                    var $ele = $(value);
                    if (value.element) {
                        $ele = $(value.element);
                    }
                    $ele.tooltip('destroy');
                    $ele.parent().removeClass('has-error').addClass('has-success');
                });
                $.each(this.errorList, function (index, value) {

                    var $ele = $(value);
                    if (value.element) {
                        $ele = $(value.element);
                    }
                    $ele.attr('title', value.message)
                        .attr('data-original-title', value.message)
                        .tooltip({
                            placement: 'auto top',
                            trigger: 'manual',
                            delay: {show: 500, hide: 5000}
                        })
                        .tooltip('show');
                    $ele.parent().removeClass('has-success').addClass('has-error');
                });
            },
            invalidHandler: function (event, validator) {
                if (validator.errorList && validator.errorList.length > 0) {
                    $(validator.errorList[0].element).focus();
                }
            }
        });
    }
});
