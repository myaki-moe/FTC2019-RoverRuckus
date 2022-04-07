# 项目概述


 FTC7860队伍2019年使用程序，包括自动程序和手动程序。

这个仓库仅用于备份以及共享代码，以后不会修改。

如果您是新入门FTC的队伍，希望这个项目能给您带来些许帮助。

本项目基于MIT协议开源，允许商业用途。


---

# 如何使用

### 下载项目

使用GIT下载：https://github.com/Ny-Cat/FTC2019-RoverRuckus.git

或者压缩包：点击网页右侧绿色Clone or download按钮

---

### 下载最新版JDK

官方下载：https://www.oracle.com/technetwork/java/javase/downloads/index.html

注意：一定要下载JDK，安装时选默认路径以后出问题的几率会小些

---

### 下载Android Studio

官方下载（可能会被墙）：https://developer.android.google.cn/studio/

国内下载：http://www.android-studio.org/

---

### 使用Android Studio打开下载的项目文件夹

开始愉快的开发（编程 × 写BUG √）

---



# 项目简单介绍

### 总体介绍

切换视图到Android视图

打开TeamCode/java/org.firstinspires.ftc.teamcode文件夹

这个文件夹就是我们的工作区，所有的代码文件都放在这里。

目前一共有三个包 : automous、ftcLib、teleOp

---

### automous包

这个包中存放了所有关于自动模式的代码

* AutoTest :  自动模式的启动程序，用于启动主程序
* AutoActivity : 自动模式的主程序
* Object_Test : 自动模式物体识别模块的测试
* PidController : PID控制器

---

### teleOp包

这个包中存放了所有关于手动模式的代码

* TeleOp : 手动模式的启动程序，用于启动所有的手动模式模块

---

### ftcLib包

这个包中存放了所有模块

目前共有两个包 : hardware、system

##### hardware包

这个包中主要是对于FTC官方硬件的抽象

* FtcCrServo : VEX EDR 393 电机的抽象
* FtcDistanceSensor : REV 2M 距离传感器的抽象
* FtcIMU : BNO055 9轴IMU的抽象
* FtcMotor : FTC电机的抽象
* FtcServo : 伺服的抽象

##### system包

这个包中主要是对于hardware包中的抽象的硬件的控制

**这部分的代码可能需要配合我们的结构设计才能看懂，如果您不能看懂，可以看看我们在B站上的视频简单了解一下我们的结构设计和运行模式。**

* CollectionBox: 车身前部伸出去的收集装置的控制
* ForwardSlideway: 前向滑轨的控制
* MecanumWheel: 麦克纳姆轮的控制
* ObjectDetection: TensorFlow物品识别的封装
* UpperBox: 上部存放装置的控制
* VerticalSlideway : 纵向滑轨的控制

---

# 【完】
