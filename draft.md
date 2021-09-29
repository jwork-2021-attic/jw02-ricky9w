```java
public interface Sorter
public class Scene
public class Line
public interface Linable
public enum Gourd implements Linable
public class Geezer
public class BubbleSorter implements Sorter
```



+ `Geezer` 类使用了 Singleton 设计思想, 保证全局只能出现一个 `Geezer` 对象, 这也符合实际逻辑
+ `Sorter` 类是一个抽象类, 对外提供了三个接口, `BubbleSorter` 类则是冒泡排序算法的具体实现. 这种设计方法使得接口统一, 维护方便, 层次清晰, 功能逻辑与具体实现分开. `Linable` 同样是一个抽象类, `Gourd` 是它的一种具体实现.

- [ ] 根据颜色排序的具体实现
- [ ] 颜色提取

使用了uml-reverse-mapper自动分析源代码并生成class diagram.
首先编译项目文件, 再打包生成 `.jar` 文件, 随后运行命令即可生成对应的 class diagram.
该项目使用的 Java 版本较老, 运行时会出现报错: