<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8"> 
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/mahout.css">
</head>
<body>
	<div id="wrap">
		<h3>Canopy算法</h3>
		<form action="" method="get">
			<div>
				<label for="">Input：</label>
				<input type="text" name="input" class="form-input required" value="">
			</div>
			<div>
				<label for="">Output：</label>
				<input type="text" name="output" class="form-input required" value="">
			</div>
			<div id="cuttingline"><span>以下选项为选填项目</span></div>
			<!-- ================================================== -->
			<div>
				<label for="">频繁项集最小出现次数：</label>
				<input type="text" name="minSupport" class="form-input number" value="3">
			</div>
			<div>
				<label for="">最大堆内存值：</label>
				<input type="text" name="maxHeapSize" class="form-input number" value="50">
			</div>
			<div>
				<label for="">原始数据分组个数：</label>
				<input type="text" name="numGroups" class="form-input number" value="1000">
			</div>
			<div>
				<label for="">分隔符：</label>
				<input type="text" name="splitterPattern" class="form-input number" value="[,\t]*[,|\t][ ,\t]*">
			</div>
			<div>
				<label for="">树缓存中的条目个数：</label>
				<input type="text" name="numTreeCacheEntries" class="form-input number" placeholder="推荐[5,10]" value="5">
			</div>
			<div>
				<label for="">计算方式：</label>
				单机<input type="radio" name="method"  class="" value="sequential" checked>
				集群<input type="radio" name="method"  class="" value="sequential">
			</div>
			<div>
				<label for="">文件编码：</label>
				<select name="encoding" id="encoding">
					<option value="utf8">UTF-8</option>
					<option value="utf16">utf16</option>
					<option value="GBK2312">GBK2312</option>
				</select>
			</div>
			<div>
				<label for="">是否使用后半的FPG算法：</label>
				<input type="text" name="useFPG2" class="form-input" value="">
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