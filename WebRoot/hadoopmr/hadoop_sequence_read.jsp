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
		<h3>序列文件读取</h3>
		<form action="HadoopSeqRead" method="get">
			<input type="text" name="hdfsPath" placeholder="请输入文件路径哦" class="form-input">
			<input type="submit" class="btn">
			<div>
				<p >文件信息</p>
				<p id="file-info">
					<!-- data info -->
					<s:if test='%{data!=null||data!=""}'>
						<s:property value="%{data}" escape="false"/> 
					</s:if>
					
				</p>
			</div>
		</form>
		<div id="Alarm">
		<!-- error info -->
		<s:if test='%{info!=null||info!=""}'>
			<p style="font-family:verdana;color:red">
				<s:property value="%{info}"  />
			</p>
		</s:if>
		</div>
		
		<br>
		<br>
		<br>
		<p>
		注意，可以运行运行test/util/HadoopIOUtilTest.testWriteSeq方法，则可以生成/dev/b.seq文件<br>
		需要修改Configuration的配置，直接在方法里面修改即可.
		</p>
	</div>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/hadoopA.js"></script>
</body>
</html>