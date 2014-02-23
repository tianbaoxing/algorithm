<!doctype html> 
<html> 
<head> 
    <meta charset="utf-8"> 
    <link rel="stylesheet" href="css/base.css">
    <link rel="stylesheet" href="css/mahout.css">
</head>
<body>
	<div id="wrap">
		<h3>协同过滤算法</h3>
		<form action="Colla_" method="get">
			<div>
				<label for="">Input：</label>
				<input type="text" name="input" class="form-input required" value="">
			</div>
			<div>
				<label for="">Output：</label>
				<input type="text" name="output" class="form-input required" value="">
			</div>
			<div>
				<label for="">距离计算公式：</label>
				<select name="similarityClassname" id="similarityClassname">
				<option value="SIMILARITY_EUCLIDEAN_DISTANCE">SIMILARITY_EUCLIDEAN_DISTANCE</option>
					<option value="SIMILARITY_COOCCURRENCE">SIMILARITY_COOCCURRENCE</option>
					
					<option value="SIMILARITY_LOGLIKELIHOOD">SIMILARITY_LOGLIKELIHOOD</option>
					<option value="SIMILARITY_PEARSON_CORRELATION">SIMILARITY_PEARSON_CORRELATION</option>
					<option value="SIMILARITY_TANIMOTO_COEFFICIENT">SIMILARITY_TANIMOTO_COEFFICIENT</option>
					<option value="SIMILARITY_UNCENTERED_COSINE">SIMILARITY_UNCENTERED_COSINE</option>
					<option value="SIMILARITY_UNCENTERED_ZERO_ASSUMING_COSINE">SIMILARITY_UNCENTERED_ZERO_ASSUMING_COSINE</option>
				</select>
				<!-- <label for="">距离计算公式：</label>
                <select name="similarityClassname" id="similarityClassname">
                    <option value="EuclideanDistanceMeasure">EuclideanDistanceMeasure</option>
                    <option value="ManhattanDistanceMeasure">ManhattanDistanceMeasure</option>
                    <option value="SquaredEuclideanDistanceMeasure">SquaredEuclideanDistanceMeasure</option>
                    <option value="TanimotoDistanceMeasure">TanimotoDistanceMeasure</option>
                    <option value="CosineDistanceMeasure">CosineDistanceMeasure</option>
                </select> -->
			</div>
			<!-- <div id="cuttingline"><span>以下选项为选填项目</span></div> -->
			<div>
				<label for="">推荐数量：</label>
				<input type="text" name="numRecommendations" class="form-input number" value="10">
			</div>
			<!-- <div>
				<label for="">用户所在文件：</label>
				<input type="text" name="usersFile" class="form-input" value="">
			</div>
			<div>
				<label for="">项目所在文件：</label>
				<input type="text" name="itemsFile" class="form-input" value="">
			</div>
			<div>
				<label for="">过滤文件：</label>
				<input type="text" name="filterFile" class="form-input" value="">
			</div> -->
			<div>
				<!-- <label for="">评分个数：</label> -->
				<label for="">MaxPerfsPerUser：</label>
				<input type="text" name="maxPerfsPerUser" class="form-input number" value="">
			</div>
			<div>
			<!-- 	<label for="">相似度最大的用户数：</label> -->
				<label for="">MaxSimilaritiesPerItem：</label>
				<input type="text" name="maxSimilaritiesPerItem" class="form-input number" value="">
			</div>
			<div>
				<!-- <label for="">单用户最大的项目数：</label> -->
				<label for="">MaxPrefsPerUserInItemSimilarity：</label>
				<input type="text" name="maxPrefsPerUserInItemSimilarity" class="form-input number" value="">
			</div>
			<div>
				<!-- <label for="">用户最低评价阈值：</label> -->
				<label for="">MinPrefsPerUser：</label>
				<input type="text" name="minPrefsPerUser" class="form-input number" value="">
			</div>
			<div>
				<label for="">输入数据是否包含评分：</label>
				<select name="booleanData" id="booleanData">
				<option value="true">是</option>
					<option value="false">否</option>
					
				</select>	
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