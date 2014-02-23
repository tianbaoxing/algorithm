<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8">
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/hadoopread.css">
</head>
<body>
	<div id="wrap">
		<h3>读取hadoop文件</h3>
		<form action="HadoopRead" method="get">
			<input type="text" name="hdfsPath" placeholder="请输入hdfs文件目录" class="form-input">
			<input type="submit" class="btn" value="提交">
			<div>
				<p >文件信息</p>
				<!-- data info -->
				<s:if test='%{data!=null||data!=""}'>
					<p id="file-info">
						<s:property value="%{data}" escape="false"/> 
					</p>
				</s:if>
				<br>
				<br>
				<br>
				<!-- error info -->
					<s:if test='%{info!=null||info!=""}'>
						<p style="font-family:verdana;color:red">
							<s:property value="%{info}"/>
						</p>
					</s:if>
			</div>
		</form>
		<div id="Alarm"></div>
	</div>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/hadoopA.js"></script>
</body>
</html>