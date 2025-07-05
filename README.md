# codegen-java java代码生成器

## 介绍
codegen-java 是一个 Java 代码生成器，用于根据数据库表结构自动生成对应的 Java 实体类、Mapper 接口及 XML 映射文件等。它设计用来简化开发过程中繁琐的重复性代码编写工作。

## 目录结构
- [README.md] - 本文件，提供关于本项目的高级概述。
- [pom.xml] - Maven 构建配置文件
- `/src` - 源代码目录
    - `/main/java` - Java 源代码
        - `/org/flyingsparrow/bean` - 实体类
            - [Constants.java] - 常量定义
            - [FieldInfo.java] - 字段信息类
            - [TableInfo.java] - 表信息类
        - `/org/flyingsparrow/builder` - 生成器核心类
            - [BuildTable.java] - 表构建器
        - `/org/flyingsparrow/utils` - 工具类
            - [JsonUtils.java] - JSON 处理工具
            - [PropertiesUtils.java] - 属性文件处理工具
            - [StringUtils.java] - 字符串处理工具
        - `/org/flyingsparrow/RunApplication.java` - 主程序入口
    - `/main/resources` - 资源文件
        - [application.properties] - 应用配置文件
    - `/test/java` - 测试用例代码

## 安装与配置
### 前提条件
确保你的开发环境中已安装并配置好以下软件：
- Java Development Kit (JDK) 1.8 或更高版本
- Apache Maven 3.x 或更高版本
- Git（可选，用于版本控制）

### 构建与运行
1. 克隆仓库到本地：
    ``git clone https://github.com/Siborne/codegen-java.git``
   
2. 进入项目目录：
    
   ``cd /codegen-java.git``
   
3. 使用 Maven 构建项目：
    
   ``mvn clean install``
   
4. 启动应用：
    
   ``mvn exec:java -Dexec.mainClass="org.flyingsparrow.RunApplication"``

## 使用方法
1. 修改 `application.properties` 文件配置数据库连接信息
2. 配置需要生成代码的表名和对应实体类名称
3. 运行程序，生成的代码会输出到指定目录

## 开发者指南
项目使用 Spring Boot 2.7.15 版本进行构建，依赖管理遵循 Spring Boot 的最佳实践。如果需要扩展功能，可以继承 [BuildTable](file://S:\Users\90438\Desktop\Sto-box\700%20-%20项目工程\workspace-codegen-java\codegen-java\src\main\java\org\flyingsparrow\builder\BuildTable.java#L22-L242) 类并实现自己的代码生成功能。

## 贡献指南
我们欢迎贡献！如果你有兴趣参与开发或改进 Sto-box，请遵循以下步骤：
1. Fork 本仓库
2. 创建一个新的分支：`git checkout -b feature/new-feature`
3. 提交你的更改：`git commit -m "Add some feature"`
4. Push 到你的分支：`git push origin feature/new-feature`
5. 提交 Pull Request 并等待审核

## 许可证
本项目采用 [MIT License](https://opensource.org/licenses/MIT)，详情请查看 LICENSE 文件

## 联系信息
如需联系开发者，请发送邮件至 [bo7785888@gmail.com] 