// $(function () {
//     init2
// }
// function init2(){
//     var commodityName = $("#commodityName").val();
//     var commodityState = $("#commodityState").val();
//     $("#commodityTable").jqGrid({
//         url: 'commodity/query',
//         datatype: "json",
//         colModel: [
//             {label: '商品id', name: 'id', index: 'id', hidden:true,width: 50, key: true},
//             {label: '商品编号', name: 'commodityNumber', index: 'commodityNumber', hidden:true,width: 30},
//             {label: '商品', name: 'img1', index: 'img1', width: 30,formatter: imgFormat},
//             {label: '商品名称', name: 'commodityName', index: 'commodityName', width: 80},
//             {label: '商品价格', name: 'commodityPrice', index: 'commodityPrice', sortable: false,align: "center", width: 50},
//             {label: '商品状态', name: 'commodityState', index: 'commodityState', sortable: false,align: "center", width: 50,formatter:typeFormat},
//             {label: '上架时间', name: 'createTime', index: 'createTime', sortable: false,align: "center", width: 50},
//             {label: '商品描述', name: 'commodityScript', index: 'commodityScript', sortable: false,align: "center", width: 50},
//             {label: '操作', name: 'state', index: 'state', width: 80,sortable: false,align: "center", edittype:"button", formatter: cmgStateFormat}
//         ],
//         height: 600,
//         rowNum: 10,
//         rowList: [10, 30, 50],
//         styleUI: 'Bootstrap',
//         loadtext: '信息读取中...',
//         rownumbers: false,
//         multiselect: false,
//         autowidth: true,
//         pager: "#commodityPager",
//         jsonReader: {
//             root: "data.list",
//             page: "data.pageNum",
//             total: "data.pages",
//             records: "data.total"
//         },
//         prmNames: {
//             page: "page",
//             rows: "rows",
//             order: "order"
//         },
//         postData : {
//             keyWords : "",
//             commodityName : commodityName,
//             commodityState : commodityState
//         },
//         gridComplete: function () {
//             //隐藏grid底部滚动条
//             $("#commodityTable").closest(".ui-jqGrid-bdiv").css({"overflow-x": "hidden"});
//         }
//     });
//     function cmgStateFormat(grid, rows) {
//         return "<button class=\"btn btn-info\" onclick=\"toCommodityMain()\"><i class=\"fa fa-plus\"></i>商品详细</button>"
//     }
//     function typeFormat(type){
//         return type == "0" ? "已上架" : type=="1"?"已下架":"待审核";
//     }
// }