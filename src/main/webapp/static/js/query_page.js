
/*
 * tab:jquery object
 * 分页查询
 * 
 * */
var Page = function(_container){
	var pageIndex = 0;
	var pageSize = 10;
	var req_url;
	var req_args = {};
	var amountPageNum = 0;
	var rawfun;
	var list;
	var tabTitle="";
	var container = _container;
	var callback;
	_this = this;
	
	/* url:string
	 * args:json
	 * 设置分页请求，分页属性都将重置
	*/
	this.setRequest = function(url,args){
		req_url = url;
		req_args = args;
		pageIndex = 0;
		amountPageNum = 0;
	};
	
	/*
	 * 获取当前页面的下标
	 * */
	this.getCurrentIndex = function(){
		return pageIndex;
	};
	
	/*
	 * title:string 
	 * fun:function(obj)
	 * 加载列表框架
	 * title 为table列表的标题
	 * fun table行要填充的内容，fun为回调函数
	 * */
	this.loadTabFrame = function(title,fun){
		tabTitle = title;
		rawfun = fun;
	};
	this.skipPage = function(obj){
		var index = $(obj).val();
		var reg = new RegExp("^[0-9]*$");
		if (undefined == index || !reg.test(index)) {
			pageIndex = 0;
		} else if (index >= amountPageNum) {
			pageIndex = amountPageNum - 1;
		} else if (index < 1) {
			pageIndex = 0;
		} else {
			pageIndex = index - 1;
		}
		this.showPage(pageIndex);
	};
	
	/* event:function
	 * 为列表内部标签绑定触发事件函数
	 * 当页面换页后，它内部的元素绑定事件将失效。
	 * 外部可以使用live方法避免失效问题，但不是每个元素都可以用
	 * 该方法。
	 * 
	 * 目前的做法是外部将要触发的事件分装在一个函数内，把该函数绑定到page
	 * 对象，page每次渲染完毕后将自动绑定这些事件。
	 * 
	 * NB：若要使用该方法，须在showPage函数之前使用才有效。
	 * */
	this.regPageInternalEvents = function(event){
		callback = event;
	};
	
	/*
	 * index:int
	 * 显示页面，此方法核心为查询和渲染列表
	 * 当index == undefined表示第一页，即调用showPage().
	 * */
	this.showPage = function(index){
		if (index == undefined){
			pageIndex = 0;
		}else if(index < 0){
			pageIndex = 0;
		}else if(index >= amountPageNum){
			pageIndex = amountPageNum -1;
		}else{
			pageIndex = index;
		}
		doSearch();
		var tabbody ="<tbody>";
		if (list != undefined || list != null){
			for(var i=0; i<list.length; i++){
				tabbody += rawfun(list[i]);
			}
		}
		tabbody += "</tbody>";

        var footer = '<div id="footer" class="pagination offset2"><ul><li><a id="page_ft" href="javascript:_this.showPage(0)">首页</a></li>';
        for(var index=0;index < amountPageNum;index++){
        	footer += '<li><a id="page_'+index+'" href="javascript:_this.showPage(' + index +')">' + (index+1) + '</a></li>';
        }
        var endPage = 0;
        if (amountPageNum > 0){
        	endPage = amountPageNum - 1;
        }
        footer += '<li><a id="page_lt" href="javascript:_this.showPage('+ endPage +')">尾页</a></li></ul></div>';
		
        container.empty();
		container.html('<table class="table table-bordered table-striped table-fluid">' +tabTitle + tabbody +'</table>' + footer);
		
		$("#footer").css("margin","0 auto");
		$("#footer").css("text-align","center");
		//高亮当前页
		$("#page_"+pageIndex).css("background-color","beige");
		
		
		$("#footer").find("a").click(function(){
			if($(this).css("background-color") == "beige"){
				return;
			}
		});
		if (amountPageNum == 0 || amountPageNum == 1){
			$("#page_ft").attr("disabled","disabled");	
			$("#page_lt").attr("disabled","disabled");
			$("#page_0").attr("disabled","disabled");
		}else if(pageIndex == 0){
			$("#page_ft").attr("disabled","disabled");
		}else if((pageIndex+1) == amountPageNum){
			$("#page_lt").attr("disabled","disabled");
		}
		/*
		if (amountPageNum == 0){
			$("#setpage").find("a").attr("href","javascript:void(0);");
			$("#setpage").find("input").attr("disabled","disabled");
		}else if(pageIndex == 0){
			$("#setpage>:eq(1)").attr("href","javascript:void(0);");
			$("#setpage>:eq(2)").attr("href","javascript:void(0);");
		}else if(pageIndex+1 == amountPageNum){
			$("#setpage>:eq(3)").attr("href","javascript:void(0);");
			$("#setpage>:eq(4)").attr("href","javascript:void(0);");			
		}
		*/
		callback();
	};
	
	
	var doSearch = function(){
		req_args.page_index = pageIndex;
		req_args.page_size = pageSize;
		$.ajax({
			type : "POST",
			url : req_url,
			data : JSON.stringify(req_args),
			dataType : "json",
			contentType : "application/json;charset=UTF-8",
			async : false
		}).done(function(msg) { // 执行成功
			if ("RESULT_OK" === msg.result) {//
				var page = msg.value;
				amountPageNum = page.total;
				page_index = page.index;
				list = page.data;
			} else // 用户会话已失效
			{
				alert(msg.result);
			}
		}).fail(function(jqXHR, textStatus) { // 执行失败
			alert("Request failed: " + textStatus);
		});		
	}
	
};