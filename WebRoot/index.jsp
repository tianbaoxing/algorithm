<!doctype html>  
<html> 
<head> 
    <meta charset="utf-8"> 
    <title>HadoopAlgorithm1.0</title>
    <link rel="stylesheet" href="css/index.css"> 
</head>
<body>
    <div id="wrap" class="clearfix">
    	<div id="header" class="clearfix">
    		<h1><a href="index.jsp">云计算平台</a></h1>
    	</div>
    	<div id="sidenav">
    		<ul>
    			<li class="sidenav-item" id="firstli">
    				<a href="" class="sidenav-title">集群配置</a>
    				<ul class="hidden">
    					<li><a href="setup/setup.jsp"><span class="triggle"></span>配置</a></li>
    					<li><a href="monitor/monitoring.jsp"><span class="triggle"></span>监控</a></li>
    				</ul>
    			</li>
    			<li class="sidenav-item">
    				<a href="" class="sidenav-title">hadoop读取删除</a>
    				<ul class="hidden">
    					<li><a href="hadoopio/hadoop_read.jsp"><span class="triggle"></span>hadoop读取</a></li>
    					<li><a href="hadoopio/hadoop_write.jsp"><span class="triggle"></span>hadoop写入</a></li>
    					<li><a href="hadoopio/hadoop_download.jsp"><span class="triggle"></span>下载</a></li>
    					<li><a href="hadoopio/hadoop_upload.jsp"><span class="triggle"></span>上传</a></li>
    				</ul>
    			</li>
    			<li class="sidenav-item">
    				<a href="" class="sidenav-title">hadoop算法</a>
    				<ul class="hidden">
    					<li><a href="hadoopmr/hadoop_sequence_read.jsp"><span class="triggle"></span>序列文件读取</a></li>
    					<li><a href="hadoopmr/hadoop_seq2txt.jsp"><span class="triggle"></span>序列文件转化为txt</a></li>
    				</ul>
    			</li>
    			<li class="sidenav-item">
    				<a href="" class="sidenav-title">mahout算法</a>
    				<ul class="hidden">
    					<li><a href="mahout/kmeans.jsp"><span class="triggle"></span>k均值算法</a></li>
    					<li><a href="mahout/canopy.jsp"><span class="triggle"></span>Canopy算法</a></li>
    					<li><a href="mahout/collaborative.jsp"><span class="triggle"></span>协同过滤算法</a></li>
    					<li id="lastli"><a href="mahout/frequent_pattern.jsp "><span class="triggle"></span>模式挖掘</a></li>
    				</ul>
    			</li>
    		</ul>
    	</div>

    	<div id="content">
    		<h3 id="conten-title">欢迎</h3>
    		<iframe src="welcome.jsp" frameborder="0" id="frame">
    		</iframe>
    	</div>
    </div>
    <script type="text/javascript" src="js/jQuery.js"></script>
    <script type="text/javascript" src="js/index.js"></script>
</body>
</html>