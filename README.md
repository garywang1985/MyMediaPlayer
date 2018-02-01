# ArcMediaPlayer-Android #
## 阅读对象 ##
本文档面向所有使用当虹云Android播放SDK的开发、测试人员等, 要求读者具有一定的Android编程开发经验。
## 1.产品概述 ##
杭州当虹科技有限公司的Android平台多媒体播放引擎v3.5版本SDK主要提供了本地音视频的播放和网络流媒体的播放功能，能够实现较低的性能开销达到较好播放体验。本参考文档将对该SDK的主要函数及使用进行详细的描述，以便开发者能够参考该文档进行快速的开发。本文档中提到的所有接口和定义都是针对java语言。
## 2.版权信息 ##
当虹云Android播放SDK版权信息：[LICENSE](https://github.com/Arcloud/ArcMediaPlayer-Android/blob/master/LICENSE)
当虹云提供的Android播放SDK可以用于商业应用，不会收取任何SDK使用费用。但基于当虹云Android播放SDK的其他商业服务，由服务提供商收取费用。
## 3.ArcMediaPlayer SDK特性 ##
- 支持 RTMP 和 HLS 协议的直播流媒体播放
- 支持首屏秒开
- 支持直播累积延时优化
- 支持常见的音视频文件和编码格式（MP4、mp3、flv 等）
- 支持预缓冲和多个视频同时播放
- 支持软解和MediaCodec硬解，且支持自动切换
- 接口定义与Android系统播放器MediaPlayer保持一致
- 提供ArcVideoView播放控件
- 支持HTTPS协议
- 支持纯音频播放
- 支持后台播放
- 支持mp4文件本地缓存
- 支持当虹云私有 DRM

## 4.开发准备 ##
### 4.1 设备以及系统要求 ###
- 系统要求：Android 4.0 (API 14) 及其以上

### 4.2 Android播放SDK下载 ###
- https://github.com/Arcloud/ArcMediaPlayer-Android

## 5.快速集成 ##
#### 5.1 首先需要开发者或公司在[当虹云后台](http://www.danghongyun.com/)进行注册账号，然后按照指定的方式获取AccessKey/Secret； ####
#### 5.2 APP 需要给 SDK 开一些权限，权限配置文件为 AndroidManifest.xml，权限项如下： ####
    <uses-permission android:name="android.permission.WRITE_SETTINGS" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    <uses-permission android:name="android.permission.VIBRATE" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.RAISED_THREAD_PRIORITY" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS" />
    <uses-permission android:name="android.permission.CHANGE_CONFIGURATION" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_LOGS"/>    
#### 5.3 在调用播放接口之前需要先进行一些必要 so 库的预加载和 ini 配置文件的生成操作，只有这一步准备好了，播放器接口才能顺利运行，可参考 samplecode 中的ArcPlayerSampleApplication.java 中的 copyPlayerIni()和 LoadLibraray()函数： ####

#### 5.4 ArcMediaPlayer 的主要调用接口和时序如下： ####
- 创建 player 对象： `ArcMediaPlayer mMediaPlayer = new ArcMediaPlayer();`
- 设置 context 和 configfile: `mMediaPlayer.setConfigFile(context, configFile);`
- 设置鉴权参数：`mMediaPlayer.validate(this, accessKey, secretKey, appKey);`
- 设置播放 url 之前调用：`mMediaPlayer.reset();`
- 设置播放地址 `mMediaPlayer.setDataSource(m_strURL,headers); `headers 可以为空
-  设置各种监听 Listener：
 ```
 mMediaPlayer.setOnCompletionListener(this);
 mMediaPlayer.setOnPreparedListener(this);
 mMediaPlayer.setOnVideoSizeChangedListener(this);
 mMediaPlayer.setOnInfoListener(this);
 mMediaPlayer.setOnErrorListener(this);
 ```
- 设置 SurfaceHolder 给播放器，用于播放显示：`mMediaPlayer.setDisplay(SurfaceHolder)`，如果没有 SurfaceHolder 则可以直接设置 Surface：`mMediaPlayer.setSurface(Surface)`;设置的时机一般在surfaceCreated/surfaceChanged之后。
- 开始播放准备(使用前面设置的 m_strURL 和其它参数进行播放准备工作)调用接口 `mMediaPlayer.prepareAsync()`，准备工作做完SDK会通过 onPrepared 通知 APP;
- 如果播放过程中需要 seek，则可调用：`mMediaPlayer.seekTo(position)`；
- 播放过程中如果出错SDK会通过 onError 通知(比如：网络连接失败等等)，有提示信息SDK会通过onInfo 通知(比如：缓冲开始、缓冲结束等等);
- 播放结束SDK通过 onCompletion 通知 App，App 收到消息后应该主动停止播放；
- 停止播放调用 `mMediaPlayer.stop()`；
- 进程退出时调用 `mMediaPlayer.release()`释放所有资源，调用该函数后下次播放必须全部重新创建ArcMediaPlayer。

## 6.参考代码 ##
链接跳转（Sample APP）
Sample code中的ArcPlayerSample.java，是一个完整的简单播放应用，除了调用ArcMediaPlayer的播放接口实现播放逻辑之外，还牵涉到一些错误处理，界面元素状态转换和控制，Activity的消息监听和处理，控件和屏幕点击事件处理，整个测试程序是按照一个功能完整的普通播放器逻辑来实现的。

## 7.注意事项(权限，与其他SDK、第三方库的冲突等) ##
### 7.1代码混淆 ###
为了保证正常使用 SDK ，请在混淆配置proguard文件中添加以下代码：
```
-keep class com.arcvideo.MediaPlayer.**{ *; }
-keep class com.arcvideo.arcvideoview.**{ *; }
```

## 8.接口说明 ##
链接跳转

## 9.详细用法介绍##
链接跳转（更详细的场景化应用的调用或者设置说明，基于我们现有或者目标客户必定会遇到的场景）

## 10.FAQ ##
链接跳转(问题/错误、原因、解决方案)

## 11.反馈及意见 ##
### 11.1联系方式 ###
- 主页：[当虹云](http://www.danghongyun.com/)
- 邮箱：
- 可以通过在GitHub的repo提交issues来反馈问题，反馈问题时建议你用如下格式，有助于快速解决问题。
##### 格式 #####

类型|描述
:------: |:---------:
SDK版本|v3.5.0.1
|设备型号|XiaoMi Note2
|OS版本|Android 6.0.1/MIUI 9.2
|问题描述|描述问题出现的现象，操作步骤，播放内容等
|附件|log,trace,截屏等
Issue:[https://github.com/Arcloud/ArcMediaPlayer-Android/issues](https://github.com/Arcloud/ArcMediaPlayer-Android/issues)