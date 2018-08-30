+(function (factory) {
    'use strict';

    factory(window);
})(function (global) {
    'use strict';

    global.BusinessUtil = {
        // 启用状态选项集
        EnabledOptions: [
            {
                value: true,
                text: '启用'
            },
            {
                value: false,
                text: '禁用'
            }
        ],
        // 转换为状态文本
        convertToEnabledText: function (enabled) {
            return enabled ? '启用' : '禁用';
        }
    };


    global.EnumUtil = {};
});
