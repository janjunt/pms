// utils方法
(function( factory ) {
    'use strict';

    factory(window, jQuery);
})(function (global, $) {
    'use strict';

    //获取指定参数的原始类型
    function getOriginalType(v) {
        return Object.prototype.toString.call(v);
    }

    global.TypeUtil = {
        isUndefined: function (v) {
            return v === undefined;
        },
        isNull: function (v) {
            return v === null || v === undefined;
        },
        isArray: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object Array]';
        },
        isNumber: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object Number]' && isFinite(v);
        },
        isString: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object String]';
        },
        isDate: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object Date]';
        },
        isBoolean: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object Boolean]';
        },
        isObject: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object Object]';
        },
        isFunction: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object Function]';
        },
        isNullOrEmpty: function (v) {
            return this.isNull(v) ||
                (this.isArray(v) && v.length === 0) ||
                (this.isString(v) && v === '');
        },
        isEvent: function (v) {
            return !this.isNull(v) && getOriginalType(v) === '[object MouseEvent]';
        },
        isDOM: (typeof HTMLElement === 'object') ?
            function (obj) {
                return obj instanceof HTMLElement;
            } :
            function (obj) {
                return obj && typeof obj === 'object' && obj.nodeType === 1 && typeof obj.nodeName === 'string';
            },
        isJqueryObject: function (obj) {
            return obj instanceof jQuery;
        }
    };

    global.CompareUtil = {
        equalsIgnoreCaseWithString: function (v1, v2) {
            if (TypeUtil.isNull(v1) || TypeUtil.isNull(v2)) {
                return false;
            }

            return v1.toString().toLocaleLowerCase() === v2.toString().toLocaleLowerCase();
        }
    };

    global.StringValidator = {
        isNullOrWhiteSpace: function(value) {
            return TypeUtil.isNullOrEmpty(value) || TypeUtil.isString(value) && TypeUtil.isNullOrEmpty($.trim(value));
        }
    };


    global.Serializer = {
        toQueryStringByObject: function (obj) {
            return this.toQueryParametersByObject(obj).join('&');
        },
        toQueryParametersByObject: function (obj, prefix) {
            var params = [];
            prefix = prefix || '';
            var childPrefix;
            if (TypeUtil.isObject(obj)) {
                var propValue;
                for (var propName in obj) {
                    childPrefix = prefix+ propName;
                    if (obj.hasOwnProperty(propName)) {
                        propValue = obj[propName];
                        if(TypeUtil.isNull(propValue)){
                            propValue = '';
                        }
                        if(TypeUtil.isObject(propValue)){
                            params = params.concat(this.toQueryParametersByObject(propValue, childPrefix +'.'));
                        }else if(TypeUtil.isArray(propValue)){
                            params = params.concat(this.toQueryParametersByObject(propValue, childPrefix));
                        }else{
                            params.push(childPrefix + '=' + propValue);
                        }
                    }
                }
            }else if(TypeUtil.isArray(obj)){
                if(StringValidator.isNullOrWhiteSpace(prefix)){
                    return params;
                }

                var itemValue;
                for(var i = 0; i < obj.length; i++){
                    itemValue = obj[i];
                    if(TypeUtil.isNull(itemValue)){
                        itemValue = '';
                    }
                    childPrefix = prefix+ '[' + i +']';
                    if(TypeUtil.isObject(itemValue)){
                        params = params.concat(this.toQueryParametersByObject(itemValue, childPrefix +'.'));
                    }else if(TypeUtil.isArray(itemValue)){
                        params = params.concat(this.toQueryParametersByObject(itemValue, childPrefix));
                    }else{
                        params.push(childPrefix + '=' + itemValue);
                    }
                }
            }

            return params;
        }
    };

    global.AsyncUtil = {
        execute: function (action, timeout) {
            if (TypeUtil.isFunction(action)) {
                setTimeout(action, timeout || 0);
            }
        }
    };

    global.ObjectUtil = {
        ensureObject: function(value) {
            if (TypeUtil.isNull(value)) {
                return {};
            }

            return value;
        }

    };

    global.ArrayUtil = {
        ensureArray: function(value) {
            if (TypeUtil.isNull(value)) {
                return [];
            }

            return value;
        },
        treeToList: function (tree, childNameOrAccessor) {
            function treeToListInternal(tree, list, childNameOrAccessor) {
                _.forEach(tree, function (item) {
                    list.push(item);
                    var childs = TypeUtil.isFunction(childNameOrAccessor) ? childNameOrAccessor(item) : item[childNameOrAccessor];
                    treeToListInternal(childs, list, childNameOrAccessor);
                });

                return list;
            }

            childNameOrAccessor = childNameOrAccessor || 'childs';

            return treeToListInternal(tree, [], childNameOrAccessor);
        }
    };

    global.EventUtil = {
        //是否为回车事件
        isEnterEvent:function(evt) {
            if (TypeUtil.isNull(evt) || TypeUtil.isUndefined(evt.keyCode)) {
                return false;
            }

            return evt.keyCode === 13;
        }
    };

    global.DateTimeUtil = {
        defaults: {
            dateFormat: 'YYYY-MM-DD',
            dateTimeFormat: 'YYYY-MM-DD HH:mm:ss',
            timeFormat: 'HH:mm:ss'
        },
        parse: function (dateValue, format) {
            if (TypeUtil.isNullOrEmpty(dateValue)) {
                return null;
            }
            if (TypeUtil.isDate(dateValue)) {
                return dateValue;
            }

            var result = null;
            if (TypeUtil.isString(dateValue)) {
                var m = moment(dateValue);
                if (!m.isValid()) {
                    if (StringValidator.isNullOrWhiteSpace(format)) {
                        format = this.defaults.dateFormat;
                    }
                    m = moment(dateValue, format);
                }
                if (m.isValid()) {
                    result = m.toDate();
                }
            } else if (TypeUtil.isNumber(dateValue)) {
                result = moment(dateValue).toDate();
            }

            return result;
        },
        toString: function (dateValue, format, defaultValue) {
            if (TypeUtil.isUndefined(defaultValue)) {
                defaultValue = '';
            }
            if (TypeUtil.isNullOrEmpty(dateValue)) {
                return defaultValue;
            }

            var date = this.parse(dateValue, format);

            if (!TypeUtil.isNull(date)) {
                if (TypeUtil.isNullOrEmpty(format)) {
                    format = this.defaults.dateFormat;
                }
                return moment(date).format(format);
            }

            return dateValue.toString();
        }
    };

    global.AjaxResultUtil = {
        parse: function (jsonString) {
            var jsonResult = jsonString;
            if(TypeUtil.isString(jsonString)){
                try{
                    jsonResult = JSON.parse(jsonString);
                }catch (err){
                    return null;
                }
            }

            if(this.isAjaxResult(jsonResult)){
                return jsonResult;
            }

            return null;
        },
        isAjaxResult: function(obj){
            if(TypeUtil.isNull(obj)){
                return false;
            }

            return obj.hasOwnProperty('success')
                && obj.hasOwnProperty('message');
        }
    };

    global.PathUtil = {
        combine: function () {
            var path = null;
            _.forEach(arguments, function (pathItem) {
                if(TypeUtil.isNullOrEmpty(path)){
                    path = pathItem;
                }else{
                    if(!TypeUtil.isNullOrEmpty(pathItem)){
                        if(!_.endsWith(path, '/')){
                            path += '/';
                        }
                        if(_.startsWith(pathItem, '/')){
                            pathItem = pathItem.substring(1);
                        }
                        path += pathItem;
                    }
                }
            });

            return path;
        }
    };

    global.StringUtil = {
        format: function(format, args) {
            var result = format;
            if (arguments.length > 1) {
                if (arguments.length === 2 && TypeUtil.isObject(args)) {
                    for (var key in args) {
                        if(!TypeUtil.isUndefined(args[key])){
                            var reg = new RegExp("(\\\{" + key + "\\\})", "g");
                            result = result.replace(reg, args[key]);
                        }
                    }
                }
                else {
                    for (var i = 1; i < arguments.length; i++) {
                        if (!TypeUtil.isUndefined(arguments[i])) {
                            var reg= new RegExp("(\\\{" + (i - 1) + "\\\})", "g");
                            result = result.replace(reg, arguments[i]);
                        }
                    }
                }
            }

            return result;
        }
    };
});

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
        },
        mapPath: function (url) {
            return url;
        }
    };


    $(function () {
        App.init();
    });
});

// jQuery 通用扩展
(function( factory ) {
    'use strict';

    factory(window, jQuery);
})(function (global, $) {
    'use strict';

    //表单序列化为Json
    $.fn.serializeJson = function () {
        var serializeObj = {};
        $(this.serializeArray()).each(function () {
            if (!TypeUtil.isUndefined(serializeObj[this.name])) {
                if (!TypeUtil.isArray(serializeObj[this.name])) {
                    serializeObj[this.name] = [serializeObj[this.name]];
                }
                serializeObj[this.name].push(this.value || '');
            } else {
                serializeObj[this.name] = this.value || '';
            }
        });
        return serializeObj;
    };

    // 设置ajax默认请求参数
    $(global.document).ajaxStart(function(){
        Html.blockUI();
    }).ajaxStop(function(){
        Html.unblockUI();
    }).ajaxError(function(e, xhr, opt, exc){
        if (exc === 'timeout') {
            Html.alert('请求超时，请重试');
        } else {
            var ajaxResult = null;
            try{
                ajaxResult = AjaxResultUtil.parse(xhr.responseText);
            }catch(err) {}
            if(!TypeUtil.isNull(ajaxResult)){
                if(!ajaxResult.success){
                    var alertMsg = Html.replaceLineSeparator(ajaxResult.message);
                    if(!TypeUtil.isNullOrEmpty(alertMsg)){
                        Html.alert(alertMsg);
                    }
                }
            }else{
                Html.alert(xhr.responseText);
            }
        }
    });
    $.ajaxSetup({
        global: true,
        dataFilter: function (data, dataType) {
            if(TypeUtil.isNull(dataType) || dataType === 'json'){
                var ajaxResult = null;
                try{
                    ajaxResult = AjaxResultUtil.parse(data);
                }catch(err) {
                    return data;
                }

                if(!TypeUtil.isNull(ajaxResult)){
                    if(!ajaxResult.success){
                        var alertMsg = Html.replaceLineSeparator(ajaxResult.message);
                        if(!TypeUtil.isNullOrEmpty(alertMsg)){
                            Html.alert(alertMsg);
                        }
                    }
                }
            }

            return data;
        }
    });
});


// 通用对话框
(function( factory ) {
    'use strict';

    factory(window, jQuery);
})(function (global, $) {
    'use strict';


    var commonDialogPrototype = null;

    function commonDialog(options) {
        var defaultOptions = {
            size: 'md',
            body: '请设置内容...',
            title: '',
            header: {
                visible: true,
                buttons: 'closeIcon'
            },
            footer: {
                buttons: 'close'
            },
            onOpen: $.noop,
            onClose: $.noop,

            classNames: {
                modalSize: {
                    sm: 'modal-sm',
                    md: '',
                    lg: 'modal-lg'
                }
            },
            template: '<div class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">' +
            '<div class="modal-dialog {{=it.classNames.modalSize[it.size]||""}}">' +
            '<div class="modal-content">' +
            '{{? !TypeUtil.isNullOrEmpty(it.header)&&it.header.visible===true }}' +
            '<div class="modal-header font-yellow-casablanca">' +
            '{{ for(var bn in it.header.buttons){ }}{{=it.renderButton(this, it.header.buttons[bn])}}{{ } }}' +
            '<h4 class="modal-title bold">{{=it.header.text||"&nbsp;"}}</h4>' +
            '</div>' +
            '{{?}}' +
            '<div class="modal-body">{{? !TypeUtil.isNull(it.ajax)}}{{="页面加载中..."}}{{??}}{{=it.body}}{{?}}</div>' +
            '{{? !TypeUtil.isNullOrEmpty(it.footer) }}<div class="modal-footer">{{for(var bn in it.footer.buttons) { }}{{=it.renderButton(this, it.footer.buttons[bn])}}{{ } }}</div>{{?}}' +
            '</div>' +
            '</div>' +
            '</div>',
            buttons: {
                default: '<button type="button" class="btn btn-default {{=it.selector}}">{{=it.text}}</button>',
                close: {
                    text: '关闭',
                    template: '<button type="button" class="btn btn-default {{=it.selector}}" data-dismiss="modal">{{=it.text}}</button>'
                },
                ok: {
                    text: '确定',
                    preventPropagateClose: true,
                    template: '<button type="button" class="btn btn-primary {{=it.selector}}">{{=it.text}}</button>'
                },
                closeIcon: '<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>'
            },
            selectors: {
                modalBody: '.modal-body'
            }
        };
        var $modals = [];

        function addModal($modal) {
            $modals.push($modal);
        }
        function removeCurrentModal() {
            var $modal = $modals.pop();
            if(!TypeUtil.isNull($modal)){
                $modal.remove();
            }
        }
        function getCurrentModal() {
            if($modals.length === 0){
                return null;
            }

            return $modals[$modals.length - 1];
        }

        function getOptions(options) {
            return $.extend(true, {}, defaultOptions, options);
        }

        function getShowOptions(defaultOptions, currentOptions) {
            if (TypeUtil.isString(currentOptions)) {
                currentOptions = { body: currentOptions };
            }
            var showOptions = $.extend(true, {}, defaultOptions, currentOptions);

            if (TypeUtil.isBoolean(showOptions.header)) {
                showOptions.header = { visible: showOptions.header };
            }
            if(!TypeUtil.isNullOrEmpty(showOptions.title)){
                showOptions.header = showOptions.header || {visible: true};
                showOptions.header.text = showOptions.header.text || showOptions.title;
            }
            if(TypeUtil.isObject(showOptions.header)){
                showOptions.header.buttons = processButtons(showOptions.buttons, showOptions.header.buttons);
            }

            if(TypeUtil.isObject(showOptions.footer)){
                showOptions.footer.buttons = processButtons(showOptions.buttons, showOptions.footer.buttons);
            }
            if(CompareUtil.equalsIgnoreCaseWithString(showOptions.size, 'auto')){
                if(!TypeUtil.isNull(showOptions.ajax)){
                    showOptions.size = '';
                }else if(TypeUtil.isNullOrEmpty(showOptions.body)){
                    showOptions.size = 'sm';
                }else{
                    var bodyLen = showOptions.body.toString().length;
                    if(bodyLen < 100){
                        showOptions.size = 'sm';
                    }else if(bodyLen < 1000){
                        showOptions.size = '';
                    }else{
                        showOptions.size = 'lg';
                    }
                }
            }

            showOptions.renderButton = function (dialog, button) {
                return doT.template(button.template).call(dialog, button);
            };

            return showOptions;
        }

        function processButtons(defaultButtons, currentButtons) {
            if (TypeUtil.isNullOrEmpty(currentButtons)) {
                return currentButtons;
            }

            var buttons = currentButtons;
            if (TypeUtil.isString(buttons)) {
                buttons = buttons.split('|');
            }

            if (TypeUtil.isArray(buttons)) {
                var buttonsObj = {};
                for (var i = 0; i < buttons.length; i++) {
                    var buttonName = buttons[i];
                    if (!TypeUtil.isNullOrEmpty(buttonName) && TypeUtil.isString(buttonName)) {
                        buttonsObj[buttonName] = {};
                    }
                }

                buttons = buttonsObj;
            }

            if (TypeUtil.isObject(buttons)) {
                for (var prop in buttons) {
                    if(!buttons.hasOwnProperty(prop)){
                        continue;
                    }

                    var defaultButtonItem = defaultButtons[prop] || defaultButtons.default;
                    if(TypeUtil.isString(defaultButtonItem)){
                        defaultButtonItem = {
                            template: defaultButtonItem
                        }
                    }

                    var buttonItem = buttons[prop];
                    if(TypeUtil.isString(buttonItem)) {
                        buttonItem = {
                            template: buttonItem
                        }
                    }

                    buttonItem = $.extend({}, defaultButtonItem, buttonItem);
                    buttonItem.selector = buttonItem.selector || 'button-' + prop + '-selector';

                    buttons[prop] = buttonItem;
                }
            }

            return buttons;
        }

        function bindEvents(dialog, options) {
            var $element = getCurrentModal();
            if (TypeUtil.isFunction(options.onOpen)) {
                $element.on('shown.bs.modal', function () {
                    AsyncUtil.execute(function () {
                        options.onOpen.call($element);
                    });
                });
            }
            $element.on('hide.bs.modal', function (event) {
                if(event.target === this){
                    removeCurrentModal();
                    AsyncUtil.execute(function () {
                        if(!dialog.preventPropagateClose){
                            options.onClose.call($element);
                        }
                    });
                }
            });
            if (!TypeUtil.isNullOrEmpty(options.footer) && TypeUtil.isObject(options.footer.buttons)) {
                for (var prop in options.footer.buttons) {
                    (function(prop) {
                        if (TypeUtil.isFunction(options.footer.buttons[prop].click)) {
                            $element.find('.' + options.footer.buttons[prop].selector).click(function() {
                                var buttonSelf = this;
                                try {
                                    dialog.preventPropagateClose = options.footer.buttons[prop].preventPropagateClose;
                                    AsyncUtil.execute(function () {
                                        options.footer.buttons[prop].click.call(buttonSelf);
                                    });
                                } catch (e) {
                                    dialog.hide();
                                }
                            });
                        }
                    })(prop);
                }
            }
        }

        function setContent(html) {
            var $modal = getCurrentModal();
            if(!TypeUtil.isNull($modal)){
                $modal.find(o.selectors.modalBody).html(html);
            }
        }

        function requestContent(dialog, ajaxOptions) {
            var externalSuccess = ajaxOptions.success;
            delete ajaxOptions.success;

            $.ajax($.extend({
                method: 'get',
                url: '',
                cache: false,
                dataType: 'html',
                global: false,
                success: function (html) {
                    setContent(html);
                    if (TypeUtil.isFunction(externalSuccess)) {
                        AsyncUtil.execute(function () {
                            externalSuccess.call(dialog);
                        }, 300);
                    }
                },
                error: function(xhr, exc){
                    if (exc === 'timeout') {
                        setContent('请求超时，请重试');
                    } else {
                        setContent(xhr.responseText);
                    }
                }
            }, ajaxOptions));
        }

        var o = options = getOptions(options);
        var render = doT.template(o.template);

        if(commonDialogPrototype === null){
            commonDialogPrototype = {
                show: function (options) {
                    var showOptions = this.getShowOptions(options);
                    var html = render.call(this, showOptions);
                    var $modal = $(html);
                    addModal($modal);
                    $modal.modal({
                        backdrop: 'static',
                        show: true,
                        keyboard: false
                    });
                    if(!TypeUtil.isNull(showOptions.ajax)){
                        requestContent(this, showOptions.ajax);
                    }
                    bindEvents(this, showOptions);
                },
                hide: function () {
                    if(!TypeUtil.isNull(getCurrentModal())){
                        getCurrentModal().modal('hide');
                    }
                }
            };
        }

        var dialog = Object.create(commonDialogPrototype);
        dialog.getShowOptions = function (options) {
            return getShowOptions(o, options);
        };

        return dialog;
    }

    global.commonDialog = commonDialog;
});

// Html相关方法
(function( factory ) {
    'use strict';

    factory(window, jQuery);
})(function (global, $) {
    'use strict';


    var commonAlert = commonDialog({
        size: 'auto',
        title: '提示信息'
    });
    var commonConfirm = commonDialog({
        size: 'auto',
        title: '网页确认框'
    });
    global.Html = {
        EmptyOption: '<option value="">--请选择--</option>',
        buildOptionsHtml: function (list, valueNameOrAccessor, textNameOrAccessor, addEmpty) {
            var optionsHtml = '';
            if(TypeUtil.isUndefined(addEmpty)){
                addEmpty = false;
            }
            if(TypeUtil.isBoolean(valueNameOrAccessor)) {
                addEmpty = valueNameOrAccessor;
                valueNameOrAccessor = 'value';
                textNameOrAccessor = 'text';
            }
            if(addEmpty){
                optionsHtml += this.EmptyOption;
            }
            if (!TypeUtil.isNullOrEmpty(list)) {
                valueNameOrAccessor = valueNameOrAccessor || 'value';
                textNameOrAccessor = textNameOrAccessor || 'text';
                for (var i = 0; i < list.length; i++) {
                    var ele = list[i];
                    optionsHtml += '<option value="' + (TypeUtil.isFunction(valueNameOrAccessor) ? valueNameOrAccessor(ele) : ele[valueNameOrAccessor]) + '">' +
                        (TypeUtil.isFunction(textNameOrAccessor) ? textNameOrAccessor(ele) : ele[textNameOrAccessor]) +
                        '</option>';
                }
            }

            return optionsHtml;
        },
        buildOptionsHtmlByObject: function(obj, addEmpty){
            var kvList = [];
            if(TypeUtil.isObject(obj)){
                for(var propName in obj){
                    if(obj.hasOwnProperty(propName)){
                        kvList.push({key: propName, value: obj[propName]});
                    }
                }
            }

            return this.buildOptionsHtml(kvList, 'key', 'value', addEmpty);
        },
        getSelectText: function (selector) {
            return $(selector).find('option:selected').text();
        },
        alert: function (message, callback) {
            commonAlert.show({
                body: message,
                onClose: callback
            });
        },
        confirm: function (option, ok, cancel) {
            var d = $.Deferred();
            var options = option;
            if(TypeUtil.isString(option)){
                options = {
                    message: option,
                    ok: ok,
                    cancel: cancel
                }
            }

            commonConfirm.show({
                title: options.title || undefined,
                body: options.message || '确认继续操作吗？',
                onClose: function () {
                    d.reject();
                    if(TypeUtil.isFunction(options.cancel)){
                        options.cancel();
                    }
                },
                footer: {
                    buttons: {
                        close: {
                            text: '取消'
                        },
                        ok: {
                            click: function () {
                                commonConfirm.hide();
                                d.resolve();
                                if(TypeUtil.isFunction(options.ok)){
                                    options.ok();
                                }
                            }
                        }
                    }
                }
            });

            return d.promise();
        },
        notific: function (msg, callback) {
            if(TypeUtil.isNullOrEmpty(msg)){
                return;
            }

            $.notific8(msg, {
                theme: 'materialish',
                color: 'lilrobot',
                closeText: '关闭',
                sticky: false,
                horizontalEdge: 'bottom',
                verticalEdge: 'right',
                life: 2000,
                zindex: 11500,
                onClose: callback || $.noop
            });
        },
        blockUI: function (options) {
            options = $.extend(true, {
                message: '<img src="' + App.mapPath('/img/loading/loading-spinner-grey.gif') + '" />&nbsp;&nbsp;正在请求中，请稍候...',
                css: {
                    border: '0',
                    padding: '5px 10px',
                    width: 'auto'
                },
                overlayCSS: {
                    backgroundColor: '#555',
                    opacity: 0.1
                }
            }, options);

            if (options.target) {
                var el = $(options.target);
                el.block(options);
            } else {
                $.blockUI(options);
            }
        },
        unblockUI: function (target) {
            if (target) {
                $(target).unblock();
            } else {
                $.unblockUI();
            }
        },
        generatePreviewSrc: function (file) {
            var d = $.Deferred();
            if (window.createObjectURL) {
                d.resolve(window.createObjectURL(file));
            }else if(window.URL){
                d.resolve(window.URL.createObjectURL(file));
            }else if(window.webkitURL){
                d.resolve(window.webkitURL.createObjectURL(file));
            }else if(FileReader) {
                var reader = new FileReader();
                reader.onload = function (e) {
                    d.resolve(e.target.result);
                };
                reader.readAsDataURL(file);
            }

            return d.promise();
        },
        replaceLineSeparator: function(value) {
            if(TypeUtil.isNullOrEmpty(value)){
                return value;
            }

            return value.replace(/\r\n/g, '<br/>').replace(/\r/g, '<br/>').replace(/\n/g, '<br/>');
        }
    };
});

// jquery级联下拉框插件
(function( factory ) {
    'use strict';

    factory(window, jQuery);
})(function (global, $) {
    'use strict';


    function inSelectValues(selVals, value) {
        if (TypeUtil.isArray(selVals)) {
            for(var i = 0; i < selVals.length; i++){
                if(selVals[i] === value){
                    return true;
                }
            }
        }else if(selVals === value){
            return true;
        }

        return false;
    }

    function bindEvent(cascade) {
        var o = cascade.options;

        if (cascade.$parent.length !== 1 || cascade.$child === 0) {
            return;
        }

        cascade.$parent.change(function () {
            reloadChilds(cascade, o.getParentValue.call(cascade, $(this)));
        });
        cascade.$parent.trigger('change');
    }
    function reloadChilds(cascade, parentValue) {
        var o = cascade.options;

        var result = [];
        if (!TypeUtil.isNullOrEmpty(parentValue) && !TypeUtil.isNullOrEmpty(o.data)) {
            for (var i = 0; i < o.data.length; i++) {
                if (o.predicate.call(cascade, parentValue, o.data[i], o.relationField)) {
                    result.push(o.data[i]);
                }
            }
        }

        if (o.ignoreEmptyValue) {
            cascade.$child.empty();
        } else {
            cascade.$child.find('option:not([value=""])').remove();
        }

        var optionHtml = cascade.render.call(cascade, $.extend({}, o, { data: result }));
        cascade.$child.append(optionHtml);
        cascade.$child.trigger('change');
    }


    function Cascade(options) {
        this.init(options);
    }

    Cascade.DEFAULTS = {
        parent: null,
        child: null,
        data: [],
        valueField: null,
        textField: null,
        relationField: null,
        template: '{{~it.data :item}}<option value="{{=item[it.valueField]}}">{{=item[it.textField]}}</option>{{~}}',
        ignoreEmptyValue: false,
        strictMatch: true,
        getParentValue: function ($parent) {
            return $parent.val();
        },
        predicate: function (parentValue, dataItem, relationField) {
            var o = this.options;
            if (TypeUtil.isNullOrEmpty(parentValue) || TypeUtil.isNullOrEmpty(dataItem)) {
                return false;
            }

            if (TypeUtil.isNull(relationField)) {
                return inSelectValues(parentValue, dataItem);
            } else if (TypeUtil.isObject(dataItem) && !TypeUtil.isUndefined(dataItem[relationField])) {
                if(!o.strictMatch && TypeUtil.isNull(dataItem[relationField])){
                    return true;
                }else{
                    return inSelectValues(parentValue, dataItem[relationField]);
                }
            }

            return false;
        }
    };

    Cascade.prototype.getDefaults = function () {
        return Cascade.DEFAULTS;
    };
    Cascade.prototype.getOptions = function (options) {
        var retOptions = $.extend({}, this.getDefaults(), options);
        retOptions.textField = retOptions.textField || retOptions.valueField;
        retOptions.valueField = retOptions.valueField || retOptions.textField;

        return retOptions;
    };
    Cascade.prototype.init = function (options) {
        this.options = this.getOptions(options);

        this.$parent = $(this.options.parent);
        this.$child = $(this.options.child);
        this.render = doT.template(this.options.template);

        bindEvent(this);
    };

    $.fn.cascadeParent = function (options) {
        var child = this;
        options = $.extend(true, {
            'child': child
        }, options);
        options.parent = options.parent || options.target;

        return new Cascade(options);
    };
    $.fn.cascadeParent.Constructor = Cascade;

    $.fn.cascadeChild = function (options) {
        var parent = this;
        options = $.extend(true, {
            'parent': parent
        }, options);
        options.child = options.child || options.target;

        return new Cascade(options);
    };
    $.fn.cascadeChild.Constructor = Cascade;
});
