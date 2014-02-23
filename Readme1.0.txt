Notice:

 1. 文件夹分类：严格按照所给的目录结构；
    WebRoot： 根目录；
    	-- css  : 公共css目录
    	-- font : 公共font目录
    	-- image：公共image目录
    	-- js   ：公共js目录
    	-----------------------------
    	-- setup: 云平台配置目录
    		-- css ： setup独有的css目录
    		-- js ： setup 独有的js目录
    		。。。 ，有其他文件夹可以加
    	-- hadoopio: 文件读取删除功能
    		-- css ： setup独有的css目录
    		-- js ： setup 独有的js目录
    		。。。 ，有其他文件夹可以加
    	-- mahout ： mahout算法页面
    		-- css ： setup独有的css目录
    		-- js ： setup 独有的js目录
    		。。。 ，有其他文件夹可以加
    	-- hadoopmr： hadoop中相关MR算法
    		-- css ： setup独有的css目录
    		-- js ： setup 独有的js目录
    		。。。 ，有其他文件夹可以加
    	-- monitor： 含有相关MR算法的监控，如果没有则不用监控，直接跳转到成功界面
    		-- css ： setup独有的css目录
    		-- js ： setup 独有的js目录
    		。。。 ，有其他文件夹可以加
    	-- util： 公共界面目录
    		-- css ： setup独有的css目录
    		-- js ： setup 独有的js目录
    		。。。 ，有其他文件夹可以加
    	-- index.jsp: 首页
    	
 2. 运行程序：
 	1）把algorithm.xml放在$TOMCAT_HOME\conf\Catalina\localhost  tomcat根目录下的文件夹路径，修改其中相应的目录为我发给你工程你存放的相应路径；
 	2） 启动tomcat，浏览器中输入 http://localhost:8080/algorithm/test.jsp ,点击提交，然后跳转到测试ok页面，则说明环境ok，可以进行页面开发；
 	3） form提交的请求都是 *_Test 即可；  