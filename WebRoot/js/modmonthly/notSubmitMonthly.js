$(document).ready(function() {
	viewNotSubmit();
});
	

      /**
       * 加载jqgrid表格  未提交人员
       */
	function viewNotSubmit() {
	    var win = art.dialog.parent;//来源页面
		var year = win.document.getElementById("year").value;
		var monthvalue = win.document.getElementById("month").value;
		var obj=win.document.getElementById("month");
		var month = obj.options[obj.selectedIndex].text;
		jQuery("#notSubmitlist").jqGrid({
			url: '/HPOA/monthlyManage/searchNotSubmitStaff',
			postData: {year:year,month:monthvalue},//内容直接赋值到url上 ，传递参数  。参数类型：{name1:value1…}
			datatype: "json",
			mtype: 'POST',
			
			jsonReader: {
		    	root: 'gridDTOsStaff',
		    	page: 'page',       //当前页
		    	total: 'total',     //总页数
		    	records: 'records', //查询出的记录数
		    	repeatitems: false
		    },
		
	rowList: [5, 10, 15],
    sortname: 'bak1',   //排序列的名称，此参数会被传到后台
    sortable: true,       //是否可排序
    viewrecords: true,    //是否要显示总记录数
   // multiselect: true,    //定义是否可以多选
	rownumbers: true,     //如为ture则会在表格左边新增一列，显示行顺序号，从1开始递增。此列名为'rn'.
    sortorder: 'desc',    //排序顺序，升序或者降序（asc or desc）
    pgtext : "第{0}页 共{1}页", //页面信息，第一个值是当前页第二个值是总页数   
    pager: '#pager1',       //导航栏对象，必须是一个有效的html元素，位置可以随意
    rowNum: 5,
   // altRows:true,  
    altclass: 'altRowsColour',//用来指定行显示的css，可以编辑自己的css文件，只有当altRows设为 ture时起作用
    width: 'auto',
    height: 'auto',
    caption: "未提交月报人员",
		    
 // 入参
	prmNames : {
		page : "page", // 表示请求页码的参数名称
		rows : "rows", // 表示请求行数的参数名称
		totalrows : "rowCount",// 表示需从Server得到总共多少行数据的参数名称
		order : "sord",// 表示采用的排序方式的参数名称
		search : "search",// 表示是否是搜索请求的参数名称
	},	
		
	  loadComplete: function (data) {//加载完jqgrid 返回的数据
		   var rowNum = parseInt($(this).getGridParam("records"), 10);  
	        if (rowNum <= 0) {  
	            alert("没有符合条件的记录！");  
	        }  
		  
      },
			colNames: ['staffId','员工姓名', '员工电话', '隶属部门', '隶属职位',  ],//列显示名称，是一个数组对象
		    colModel: [
			    {
			      name: 'staffId',  //列显示的名称
		    	  index: 'staffId', //传到服务器端用来排序用的列名称
		    	  hidden : true,   //在初始化表格时是否要隐藏此列
		    	  width: 40,
		    	  align: "left",   //对齐方式
		    	  sortable: false },//是否可以排序
	    	   { 
		    	  name: 'staffName', 
		    	  index: 'staffName', 
		    	  search : true,
		    	  width: 110, 
		    	  align: "center" },
			    { 
		    	  name: 'staffPhone', 
		    	  index: 'staffPhone', 
		    	  search : true,
		    	  width: 120, 
		    	  align: "center" },
			    {
		    	  name: 'staffDept', 
		    	  index: 'staffDept', 
		    	  width: 100, 
		    	  align: "center" },
			    { 
		    	  name: 'staffPosi', 
		    	  index: 'staffPosi', 
		    	  width: 150, 
		    	  align: "center", 
		    	  search: true, 
		    	  sortable: true },
		    ],

		    }).trigger("reloadGrid"); 
	
		};