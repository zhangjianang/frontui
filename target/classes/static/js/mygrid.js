$(document).ready(function () {
    var source = {
        url:'/data/daily',
        datafields: [
            { name: 'taskName' },
            { name: 'actual'},
            { name: 'category' },
            { name: 'date'},
            { name: 'elapse'},
            { name: 'id' },
            { name: 'reason'}
        ],
        datatype: "json",
        addrow:function(rowid, rowdata, position, commit){
            alert(rowid+"--"+rowdata);
        }
    };
    var dataAdapter = new $.jqx.dataAdapter(source);

    $("#grid").jqxGrid(
        {
            width: getWidth('grid'),
            source: dataAdapter,
            showeverpresentrow: true,
            everpresentrowposition: "top",
            editable:true,
            sortable: true,
            columns: [
                { text: 'taskName', datafield: 'taskName', width: 200 },
                { text: 'actual', datafield: 'actual'},
                { text: 'category', datafield: 'category'},
                { text: 'date', datafield: 'date', width: 100 },
                { text: 'elapse', datafield: 'elapse'},
                { text: 'id', datafield: 'id' },
                { text: 'reason', datafield: 'reason',width: 300}
            ]
        });
});