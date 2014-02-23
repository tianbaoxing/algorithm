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
		<h3>写入hadoop文件</h3>
		<form action="HadoopWrite" method="get">
			<input type="text" name="hdfsPath" placeholder="请输入hdfs文件目录" class="form-input">
			<input type="submit" class="btn">
			<div>
				<p id="file-info">文件数据</p>
				<textarea name="modifiedMessage" id=""></textarea>
			</div>
		</form>
		<div id="Alarm">
			<!-- error info -->
			<s:if test='%{info!=null||info!=""}'>
				<p style="font-family:verdana;color:red">
					<s:property value="%{info}"/>
				</p>
			</s:if>
		</div>
	</div>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/hadoopA.js"></script>
</body>
</html>