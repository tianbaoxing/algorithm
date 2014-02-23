<%@ taglib uri="/struts-tags" prefix="s" %>
<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8"/> 
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="../monitor/css/setup.css">     <!-- 地址不是一样么？ -->
</head>
<body>
	<div id="wrap">
	    <h3>Job任务监控界面</h3>
	    <table>
	    	<caption>任务实时监控</caption>
	    	<thead>
	    		<tr>
					<td id="taskid">任务ID</td>
					<td id="taskname">任务名称</td>
					<td id="map">map进度</td>
					<td id="reduce">reduce进度</td>
					<td>任务状态</td>
	    		</tr>
	    		<tbody>
	    			
	    			<s:iterator id="list" value="jobInfosList">
	            		<tr>
	               		 	<td><s:property value="#list.jobId" /></td>
	                		<td><s:property value="#list.jobName" /></td>
	               			<td><s:property value="#list.mapProgress" /></td>
	                		<td><s:property value="#list.redProgress" /></td>
	               			<td><s:property value="#list.runState" /></td>
	            		</tr>
	       			</s:iterator>
	    	</tbody>
	    </table>
	 
    </div>
   
     <% 
    	boolean flag=Boolean.parseBoolean(request.getAttribute("flag").toString());
    	%>
    	<%
    	if(!flag){
    	%>
		<script type="text/javascript">
			delayURL("transform",1500);
			function delayURL(url, time) {
   				  setTimeout("location.href='" + url + "'", time);
				}
  		 </script>
    <%
    }else{
    %>
    	<h3 id="finishword">全部任务已经完成</h3>
    <%
    }
     %>
</body>
</html>