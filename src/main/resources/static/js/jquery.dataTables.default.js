// jquery Datatable 默认配置
(function( factory ) {
    'use strict';

    factory(jQuery);
})(function ($) {
    'use strict';

    $.extend($.fn.dataTable.defaults, {
        dom: '<t><"container-fluid"<"row"<"col-md-6"li><"col-md-6"p>>>',
        pageLength: 10,
        lengthMenu: [10, 20, 50, 100],
        processing: true,
        serverSide: true,
        searching : false,
        ordering: false,
        pagingType: 'full_numbers',
        autoWidth: false,
        language: {
            processing: '正在处理中...',
            lengthMenu: '_MENU_ 条/页',
            info: '第 _START_ 至 _END_ 条，共 _TOTAL_ 条',
            infoEmpty: '未找到数据',
            loadingRecords: "数据加载中...",
            emptyTable: '暂无数据',
            zeroRecords: '未找到匹配记录',
            thousands: ',',
            paginate: {
                previous: '上一页',
                next: '下一页',
                last: '尾页',
                first: '首页'
            }
        },
        ajax: {
            type: 'POST',
            timeout: 20000,
            cache: false,
            error: function () {
                Html.alert('数据请求失败');
            }
        }
    });
});

