# TinkerDemo
这几天想学习一下Tinker，但我觉得官方给的例子太复杂，作为新手，我只想一个最简单最直观的例子。

在下愚钝，开始集成并不顺利，在Tinker的官方技术人员的悉心指导之下，花了一天多的时间终于有点运行出了

我的一个小补丁,还是很开心的,看图说话：


![image](https://github.com/tomyZhou/TinkerDemo/blob/master/scrrenshot/old.png)   
						（原始界面）

![image](https://github.com/tomyZhou/TinkerDemo/blob/master/scrrenshot/new.png)  
		
						（补丁后界面）

关键步骤截图如下：

![image](https://github.com/tomyZhou/TinkerDemo/blob/master/scrrenshot/1.png) 

						（图一）

![image](https://github.com/tomyZhou/TinkerDemo/blob/master/scrrenshot/2.png)

						（图二）

![image](https://github.com/tomyZhou/TinkerDemo/blob/master/scrrenshot/3.png)

						（图三）

![image](https://github.com/tomyZhou/TinkerDemo/blob/master/scrrenshot/4.png)

						（图四）



集成步骤如下：

	1.gradle配置文件，包括总项目build.gradle,app里的build.gradle 和 thinkpatch.gradle最简化的配置就
	
	如我项目里的，少一点就会出问题。
	
	
	2.配置好了后，点击右边Gradle视图中的build/assembleRelease（如图三），此时会在build/bakApk里生成
	
	app-1.0.0-xxxx-xx-xx-xx/release/app-release.apk(mapping 和 R.txt),这里着重需要说明的是"xxxx-xx-xx-xx"
	
	这个是根据当前时间戳生成的一个字符串，所以每次生成时都会不一样，此事跟下一步的操作有关。（如图一）
	
	
	3.步骤2完成了以后，我们就生成了初始安装包。下一步，我们修改代码，例子里我就写了

	tv_hello.setText("5.25发了个补丁，修复了bug")。
	
	
	4.接下来一步最为重要：我们要把我们在2步骤里生成的三个文件的具体路径地址把tinkerpatch.gradle的 baseApkFile ，
	
	baseProguardMappingFile ，baseResourceRFile  三个变量赋值（如图一）。主要是因为2步骤里的"xxxx-xx-xx-xx"路径
	
	每次生成时间戳不一样，所以这里要写死。写好了以后，我们双击Grale视图里的tinker/tinkerPatchRelease（如图三），
	
	此事正常会生成outputs/tinkerPatch/release/patch_signed_7zip.apk 这个就是我们要的补丁的压缩版。如果那三个变量所指向

	的原始包地址不对，会编译不成功，提示找不到xxx.apk。
	
	5.最后发布版本。注意版本号要和 tinkerpath.gradle里的配置 appVersion = "1.0.0" 一致，而不是和 app/build.gradle里
	
	的versionName一致。（如图四）
	

测试步骤：

	1. 安装 old.apk ，安装查看原始效果。
	
	2. 到tinkerpatch官网后台发布补丁。http://www.tinkerpatch.com
	
	3. 清除项目进程，过5秒左右重新进入项目，正常的话补丁就生效了。
	
	
	
运行成功了，下面我们来看看例子中代码几处重要的地方：

	1.Application  文档中说到了Application有两种实现方式，我用的是 reflectApplication = true这种
	
	public class SampleApplication extends Application，在Application里面初始化Tinker。
	
	2.注意SampleApplication中添加一个空的构造函数：public SampleApplication() {}  否则AndroidManifest.xml 中会报错。
		 
	3.Tinker和HotFix有点不一样，补丁不是主动推送下来的，需要我们在关键的地方请求一下服务器是否有补丁。
	
	（官方说明：http://www.tinkerpatch.com/Docs/FAQ）
	
	 本例由于我只有一个Activity，所以我就放在onCreate里面了。
	