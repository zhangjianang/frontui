// $.noConflict();
$(document).ready(function () {
    var dateFommater = function(cellvalue, options, rowObject){
        if(rowObject.date === undefined ||  rowObject.date/1000000000000<1){
            return ''
        }
        var date = new Date(rowObject.date);
        var year = date.getFullYear() + '-';
        var month = (date.getMonth()+1 < 10 ? '0' + (date.getMonth()+1) : date.getMonth()+1) + '-';
        var dates = date.getDate() + ' ';
        var hour = date.getHours() + ':';
        var min = date.getMinutes() + ':';
        var second = date.getSeconds();
        // return year + month + dates + hour + min + second ;
        return year + month + dates ;
    };
    $("#jqGrid").jqGrid({
        editurl: '/daily/updatebean',
        datatype: "local",
        colModel: [
            {label:'操作',name:'myact',index:'myact',width:80,formatter:opFomatter },
            { label: 'ID', name: 'id',width:20 },
            { label: 'Task Name', name: 'taskName', width: 75,editable:true },
            { label: 'Category', name: 'category', width: 90 ,editable:true},
            { label: 'Elapse', name: 'elapse', width: 20 },
            { label: 'Actual', name: 'actual', width: 20, sorttype: 'integer' },
            { label: 'Date', name: 'date', width: 80,
                formatter: dateFommater,
                editable: true,
                edittype:"text",
                editoptions: {
                    dataInit: function (element) {
                        $(element).datepicker({
                            id: 'date',
                            dateFormat: 'yy-mm-dd',
                            altField : '[name=publish_time]',
                            altFormat : '@'
                            //minDate: new Date(2010, 0, 1),
                        });
                    }
                },
                unformat:unDateFomatter
            },
            { label: 'Reason', name: 'reason', width: 80 }
        ],
        viewrecords: true, // show the current page, data rang and total records on the toolbar
        width: 780,
        height: 200,
        pager: "#jqGridPager"
    });
    $('#jqGrid').navGrid("#jqGridPager", {
        search: true, // show search button on the toolbar
        add: false,
        edit: false,
        del: false,
        refresh: true
    });

    function opFomatter(cellvalue, options, rowObject){
        return "<a href='#' onclick='myOperate("+ rowObject.id+ ")'>操作</a>"
    };
    function unDateFomatter(cellvalue, options) {
        // alert(cellvalue);
        // return (new Date(cellvalue)).getTime();
    }

    (function fetchGridData() {
        var gridArrayData = [];
        $.ajax({
            url: "/daily/all",
            success: function (result) {
                var items = JSON.parse(result);
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    gridArrayData.push({
                        id: item.id,
                        taskName: item.taskName,
                        category: item.category,
                        elapse: item.elapse,
                        actual: item.actual,
                        reason:item.reason,
                        date:item.date
                    });
                }
                // set the new data
                $("#jqGrid").jqGrid('setGridParam', { data: gridArrayData}).trigger('reloadGrid');
            }
        });
    })();
});
function myOperate(rowid){
    // $('#jqGrid').editRow(rowid);
    jQuery('#jqGrid').editGridRow(rowid,{closeAfterEdit: true});
};
// function pickdates(id) {
//     jQuery("#" + id + "_date", "#jqGrid").datepicker({
//         dateFormat : "yy-mm-dd"
//     });
// };