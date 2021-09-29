# W02
191220119 王毓琦

## 文件结构

```
.
├── S191220119		//作业目录
│   ├── task1
│   ├── task2
│   │   ├── asset	//资源目录
│   │   └── src		//源码目录
│   ├── task3
│   │   ├── asset
│   │   └── src
│   └── utils			//代码公共依赖
├── bin						//生成的二进制文件
│   └── example
├── example				//示例代码
└── utils					//公共依赖项
```

## 类图的生成

从做第一次作业的时候就在想, 手工读源码画 class diagram 大部分都是复制粘贴操作, 非常繁琐而且容易遗漏, 这种工作一定有自动化工具来代劳, 于是找到了[uml-reverse-mapper](https://github.com/iluwatar/uml-reverse-mapper)项目. 将 Java 工程的代码编译并打包成 `.jar` 文件, 即可使用该工具生成工程的 class diagram.

不过运行的时候遇到了报错:

```shell
Exception in thread "main" java.lang.IllegalArgumentException: Unsupported class file major version 61
	at org.objectweb.asm.ClassReader.<init>(ClassReader.java:196)
	at org.objectweb.asm.ClassReader.<init>(ClassReader.java:177)
	at org.objectweb.asm.ClassReader.<init>(ClassReader.java:163)
	at org.objectweb.asm.ClassReader.<init>(ClassReader.java:284)
	at com.iluwatar.urm.scanners.FieldScanner.extractFieldEdges(FieldScanner.java:56)
	at com.iluwatar.urm.scanners.FieldScanner.getEdges(FieldScanner.java:46)
	at com.iluwatar.urm.DomainMapper.describeDomain(DomainMapper.java:38)
	at com.iluwatar.urm.DomainMapperCli.run(DomainMapperCli.java:70)
	at com.iluwatar.urm.DomainMapperCli.main(DomainMapperCli.java:27)
```

看到这个报错一脸懵逼, 不过报错信息里面有个“version”字样, 想到当前用的 M1 MacBook 因为 arm 架构兼容性问题用的是最新的 JDK 17, 合理怀疑是 JDK 版本问题导致的这个报错.

仔细看报错信息发现是 `asm` 模块报错, 搜索之后发现这个模块用来读取 Java 二进制文件. 从 `asm`  的[发行日志](https://asm.ow2.io/versions.html)知道最早支持 JDK 17 的 asm 版本是 9.1, 而 urm 项目用的则是 7.3.1. 打开项目的 `pom.xml` 文件, 修改 asm 版本为 9.1, 重新编译之后问题解决.

也知道主流的 Java IDE 都可以通过安装插件的方式做到同样的事, 但是初学 Java 实在不太想用这么重的工具, 所以这个简单的工具还是挺合我的口味的. 后续如果有能力的话或许能把这个小工具做成 VS Code 插件吧...

更加详细的踩坑笔记发布在我的个人网站: [从Java源码自动生成UML类图](https://donnadie.top/class-diagram-from-java-code/).

适用于新的 JDK 版本的项目在我的 GitHub [仓库](https://github.com/ricky9w/uml-reverse-mapper).

原来为了~~偷懒~~而做的事情, 搞完反而从中学到了不少东西, 也算是一种意外收获...

## 对可视化展示命令的调整

作业要求中的命令不适合用于较大规模排序的情况:

```shell
java -jar asciianimator.jar -f result.txt -l false -c true -fps 3
```

根据 [AsciiAnimator 项目](https://github.com/thatcherclough/AsciiAnimator)的说明文件, 该命令中 `-c true` 选项意思是在每一帧显示完成后清空屏幕. 在本次作业中, 这种绘制方式会导致画面撕裂/闪烁, 效果很不好.

在每一帧长度不一样时, `-c` 参数显然必须设置为 true, 但是我在实现妖怪类 `Youkai` 的 `toString()` 方法时根据妖怪的编号的位数在其所在方格中加入了数量不等的空格, 使得每个妖怪所在的方格长度均相等, 因此排序过程中每一帧的显示区域也是相同的, 从而可以将 `-c` 参数设置为 false 来避免画面闪烁.

```java
public String toString() {
        String spaces = " ".repeat(3 - Integer.toString(rank).length());
        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m    " + this.rank() + spaces + "  \033[0m";
    }
```

此外, 原有命令中 `-fps` 参数设置过低, 在 256 个小妖怪排序时需要很长时间. 因此将该参数调整为 120, 使整个排序时长控制在合理范围内.

## 任务一

example中的类图如下:



main方法执行过程中对象时序图如下:



阅读示例代码后发现的优点和可改进之处如下:

优点:

+ `Geezer` 类使用了 Singleton 设计思想, 保证全局只能出现一个 `Geezer` 对象, 这也符合实际逻辑

+ `Sorter` 类是一个抽象类, 对外提供了三个接口, `BubbleSorter` 类则是冒泡排序算法的具体实现. 这种设计方法使得接口统一, 维护方便, 层次清晰, 功能逻辑与具体实现分开. `Linable` 同样是一个抽象类, `Gourd` 是它的一种具体实现
+ `Linable` 和 `Position` 共同组成了类似“容器”的效果, 对数组中的元素的排序和移动加了一层抽象, 更加通用化

改进:

+ 可以将类中变量的声明放在方法定义之前, 提高代码可读性
+ `getGourdByRank` 使用循环遍历所有 `Gourd` 类成员来找到具有特定 rank 的 `Gourd` 对象, 这在对象数量不多的情况下性能尚可, 但 `Gourd` 数量较多时性能不佳. 改为哈希表数据结构会更好.



## 任务二

任务二的类图如下:

![](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/jwork-2021/jw02-ricky9w/master/S191220119/task2/asset/class-diagram.pu)

大致思路:

使用 `Matrix` 类代替示例代码中的 `Line` 类, 该类中数据存储在一个一维数组中, 类中同时记录着矩阵的行数和列数信息, 根据该信息决定矩阵的打印输出.

任务二中的排成一行操作相当于在一个 1x256 的矩阵中进行排序, 任务三则相当于在一个 16x16 的矩阵中排序, 因此需要修改的代码很少.

可视化结果展示:



在项目根目录下执行:

```shell
java -jar ./utils/asciianimator.jar -f ./S191220119/task2/asset/result-2.txt -l false -c false -fps 30
```

同样可以看到可视化结果.

## 任务三
任务三的类图如下:

![](http://www.plantuml.com/plantuml/proxy?cache=no&src=https://raw.githubusercontent.com/jwork-2021/jw02-ricky9w/master/S191220119/task3/asset/class-diagram.pu)

(其实和任务二一毛一样)

前面说过任务二、三都基于矩阵类 `Matrix` , 因此任务二和任务三的区别仅仅在于修改矩阵创建时的参数, 任务二中是 `row=1, col=256` 而任务三中是 `row=16, col=16` .

可视化结果展示:

