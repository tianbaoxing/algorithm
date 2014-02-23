<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8"> 
    <!-- <link rel="stylesheet" href="setup/css/base.css">
    <link rel="stylesheet" href="setup/css/setup.css"> -->
     <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/setup.css">
</head>
<body>
	<div id="wrap">
		<h3>配置</h3>
		<form action="Setup" method="post">
			<div>
				<label for="jobtracker">jobtracker:</label>
				<input class="form-input" type="text" name="jobtracker">
				<label for="jobtrackerPort">端口:</label>
				<input class="form-input port" type="text" name="jobtrackerPort">
			</div>
			<div>
				<label for="namenode">namenode:</label>
				<input class="form-input" type="text" name="namenode">
				<label for="namenodePort">端口:</label>
				<input class="form-input port" type="text" name="namenodePort">
			</div>
			<div id="btn-group">
				<input type="reset" value="重置" class="btn">
				<input type="submit" value="提交" class="btn">
			</div>
		</form>
		<div id="Alarm">
			<s:if test='%{info!=null||info!=""}'>
				<s:property value="%{info}"/> 
			</s:if>
			
		</div>
		
	</div>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/setup.js"></script>
</body>
</html>