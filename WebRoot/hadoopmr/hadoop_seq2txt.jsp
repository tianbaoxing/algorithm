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
		<h3>序列文件转化为txt文件</h3>
		<form action="HadoopSeq2Txt" method="get">
			<div><input type="text" name="hdfsPath" placeholder="请输入hdfs目录" class="form-input"></div>
			<div><input type="text" name="localPath" placeholder="请输入生成txt文件的目录" class="form-input"></div>
			<input type="submit" class="btn">
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