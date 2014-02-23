<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8"> 
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/mahout.css">
</head>
<body>
	<div id="wrap">
		<h3>k均值算法</h3>
		<form action="Kmeans_" method="get">
			<div>
				<label for="">Input：</label>
				<input type="text" name="input" class="form-input required" value="">
			</div>
			<div>
				<label for="">Output：</label>
				<input type="text" name="output" class="form-input required" value="">
			</div>
			<div>
				<label for="">原始数据分隔符：</label>
				<input type="text" name="splitter" class="form-input required" value="">
			</div>
			<div>
				<label for="">最大的循环次数：</label>
				<input type="text" name="maxIter" class="form-input required number" value="">
			</div>
			<!-- <div id="cuttingline"><span>以下选项为选填项目</span></div> -->
			<div>
				<label for="">距离计算公式：</label>
                <select name="distanceMeasure" id="distanceMeasure">
                    <option value="EuclideanDistanceMeasure">EuclideanDistanceMeasure</option>
                    <option value="ManhattanDistanceMeasure">ManhattanDistanceMeasure</option>
                    <option value="SquaredEuclideanDistanceMeasure">SquaredEuclideanDistanceMeasure</option>
                    <option value="TanimotoDistanceMeasure">TanimotoDistanceMeasure</option>
                    <option value="CosineDistanceMeasure">CosineDistanceMeasure</option>
                </select>
			</div>

			<div>
				<label for="">聚类中心的数目：</label>
				<input type="text" name="numClusters" class="form-input number" value="">
			</div>
		
			<div>
				<label for="">是否对数据进行分类：</label>
				<select name="clustering" id="clustering">
					<option value="true">是</option>
					<option value="false">否</option>
				</select>
			</div>
			<div>
				<label for="">计算方式：</label>
				单机<input type="radio" name="method"  class="" value="sequential" >
				集群<input type="radio" name="method"  class="" value="mapreduce" checked>
			</div>
			<div id="btn-group">
				<input type="reset" class="btn">
				<input type="submit" class="btn">
			</div>
		</form>
		<div id="Alarm"></div>
	</div>
	<script type="text/javascript" src="js/jQuery.js"></script>
	<script type="text/javascript" src="js/mahout.js"></script>
</body>
</html>