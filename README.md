# CMusic
**温馨提示：阅读过程中将不可避免地出现以下内容**

群除我佬

自我怀疑

《关于我不会自己从网上下载图片并显示于是用网易图标外加两张表情包充数这回事》

明明是音乐app却因为程序猿太菜无法播放音乐，这样的app你喜欢吗？

###### 步入正轨 ######

#### 已实现部分 ####

完成了主要UI，实现各部分的点击事件，有一些因为涉及未完成功能就用Toast代替了，实现大概75%的数据获取~~bug真的很令人崩溃~~，因为时间紧迫并未使用持久化技术进行存储。完成了各部分的点击跳转。

#### 新增app使用说明 ####

登录密码与账号都是000000，且qpp中所有的网络数据请求所需的参数皆为硬编码，即参数在代码中是固定的，所请求到的数据也就是固定的，导致app不像一个真正的app

#### 存在的问题以及不足 ####

首先是因为时间与技术都不太足的原因，导致即使有去尝试却也未能实现MediaPlayer、ImgLoader这两项个人认为比较核心的功能~~是我太菜了~~，其次是在各种地方的细节都没有处理，例如play的图标selector等均未实现，而且可能还存在我没有发现或处理妥当的bug~~还是我太菜了~~。

#### 用到的技术 ####

数据持久化（sharedpreferences，sqlite）

viewpager+radiogroup+fragment实现底部导航，滑动切换~~原本所有界面都想使用fragment实现，但因为我有我无法处理的bug出现就没有这样做，菜上加菜~~

自定义工具类（http，json）~~本来自己写的自定义工具类就少，拿得出手的还就只有这俩~~

其它就没啥好说的了~~果然还是我太菜了~~

#### 感悟与心得 ####

确实意识到了学无止境，以及要多加练习的铁道理

自己平常的学习模式就是看书~~虽然最近因为网课的模式多少有些怠惰，缺乏动力~~，不太会写demo，认为见多识广，可通过这次考核我明白了自己并不适合这种学习方式，看再多需要时写不出来也没用，还是要多码码代码

~~**最后，劳动法很好看，我已经第三遍了（doge）**~~

gif图如果时间充裕会补上 ~~（基本不可能了）~~

![](https://github.com/dr-chene/CMusic/blob/master/cmusic_home.gif)
![](https://github.com/dr-chene/CMusic/blob/master/cmusic_login.gif)
![](https://github.com/dr-chene/CMusic/blob/master/cmusic_playlist.gif)
![](https://github.com/dr-chene/CMusic/blob/master/cmusic_profile.gif)
