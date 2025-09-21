# Mysite-Backend

本项目是 Mysite 的后端部分，使用 Java 语言开发，负责为前端提供 API 支持及数据服务。

## 技术栈

- Java 17+
- Spring Boot
- MySQL
- Maven

## 项目结构

```shell
├── src
│   ├── main
│   │   ├── java
│   │   │   └── ...      # 后端源代码
│   │   └── resources    # 配置文件
│   └── test             # 测试代码
├── pom.xml              # Maven 配置
```

## 快速开始

1. **克隆项目**
   ```bash
   git clone https://github.com/luf-23/Mysite-Backend.git
   cd Mysite-Backend
   ```
2. **配置数据库**
   - 在 `src/main/resources/application.yml` 或 `application.properties` 中配置你的数据库连接信息。

3. **构建并运行**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. **默认 API 端口**
   - `http://localhost:8080`

## 与前端对接

推荐搭配 [Mysite-Frontend](https://github.com/luf-23/Mysite-Frontend) 项目使用。前端会通过 API 地址与本后端交互。

## 相关文档

- [Spring Boot 官方文档](https://spring.io/projects/spring-boot)
- [MySQL 官方文档](https://dev.mysql.com/doc/)
