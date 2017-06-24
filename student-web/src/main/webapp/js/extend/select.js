//<select id="severity" name="severity" defaultValue="2" dataType="selectWhit"  modelCode="bug" fieldCode="severity">
//---------使用说明---start-------------
/**
 * dataType : 为空不添加任何多余选项
 * 		selectWith: 表示下拉列表中会添加一列为“--请选择--”，
 * 		allWith:表示下拉列表中会添加一列为“ALL--所有”
 * defaultValue : 为默认选种选项
 * modelCode ：数据字典中对应的模块代码
 * fieldCode ： 数据字典中对应的字段代码
 */
//---------使用说明---end---------------
$(function(){
	$("select").each(function(){
		$this = $(this);
		var modelCode = $this.attr("modelCode");//模块名
		var fieldCode = $this.attr("fieldCode");//字段名
		var dataType = $this.attr("dataType");//数据类型
		var defaultValue = $this.attr("defaultValue");//默认值
		var html = "";
		if(modelCode && fieldCode){
			if(dataType == "allSelect"){
				html = "<option value=\"\">ALL -- 所有</option>";
			}else if(dataType == "withSelect"){
				html = "<option value=\"\">-- 请选择 --</option>";
			}
			var data = {"date":new Date(),"modelCode":modelCode,"fieldCode":fieldCode};
			var url = "../common/getSelect";
			$.ajaxSettings.async = false;
			$.getJSON(url,data,function(data){
				$.each(data,function(index,value){
					if(value.valueField == defaultValue){
						html = html + "<option selected=\"selected\" value=\"" + value.valueField + "\">"+ value.valueField + " -- " +value.displayField + "</option>";
					}else{
						html = html + "<option value=\"" + value.valueField + "\">"+ value.valueField + " -- " +value.displayField + "</option>";
					}
				});
			});
			$this.html(html);
		}
	});
});