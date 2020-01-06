$(document).ready(function () {
    var mysend = function (url,data) {
        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            success: success,
            dataType: dataType
        });
    };

    var source = {
        url:'/daily/all',
        datafields: [
            { name: 'id' },
            { name: 'taskName' },
            { name: 'actual'},
            { name: 'category' },
            { name: 'date'},
            { name: 'elapse'},
            { name: 'reason'}
        ],
        datatype: "json",
        addrow:function(rowid, rowdata, position, commit){
            // alert(rowid+"--"+JSON.stringify(rowdata));
            $.post("/daily/add", {"info":JSON.stringify(rowdata)});
            commit(true);
        },
        updaterow: function (rowid, rowdata, commit) {
            // alert(rowid+"--"+JSON.stringify(rowdata));
            $.post("/daily/update", {"info":JSON.stringify(rowdata)});
            commit(true);
        }
    };


    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#grid").jqxGrid({
        width: getWidth('grid'),
        source: dataAdapter,
        showeverpresentrow: true,
        everpresentrowposition: "top",
        editable:true,
        sortable: true,
        columns: [
            { text: 'id', datafield: 'id' },
            { text: 'taskName', datafield: 'taskName', width: 200 },
            { text: 'actual', datafield: 'actual'},
            { text: 'category', datafield: 'category'},
            { text: 'date', datafield: 'date', width: 100 },
            { text: 'elapse', datafield: 'elapse'},
            { text: 'reason', datafield: 'reason',width: 300}
        ]
    });
});