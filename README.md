# Gym 健身房管理系统

一个基于 Spring Boot 的健身房后台管理系统，提供管理员和会员两种角色的管理功能，集成了 AI 智能问答服务。

## 📋 项目简介

Gym 健身房管理系统是一个完整的后台管理平台，主要用于健身房的日常运营管理。系统支持管理员对员工、器材、会员进行全面管理，同时为会员提供个性化的 AI 健身咨询服务。

## 🛠️ 技术栈

- **后端框架**: Spring Boot 3.5.14
- **Java 版本**: Java 17
- **持久层框架**: MyBatis 3.0.5
- **数据库**: MySQL 8.0.25
- **构建工具**: Maven
- **工具库**: Lombok
- **AI 服务**: DeepSeek API

## 📁 项目结构

```
Gym/
├── src/main/java/com/jinjin/gymmanagementsystem/
│   ├── controller/          # 控制器层
│   │   ├── LoginController.java      # 登录控制（管理员/会员）
│   │   ├── AdminController.java      # 管理员管理
│   │   ├── EmployeeController.java   # 员工管理
│   │   ├── EquipmentController.java  # 器材管理
│   │   ├── MemberController.java     # 会员管理
│   │   └── ChatController.java       # AI 聊天咨询
│   ├── service/             # 服务层
│   │   ├── AdminService.java
│   │   ├── EmployeeService.java
│   │   ├── EquipmentService.java
│   │   ├── MemberService.java
│   │   └── ChatService.java
│   ├── mapper/              # 数据访问层
│   │   ├── AdminMapper.java
│   │   ├── EmployeeMapper.java
│   │   ├── EquipmentMapper.java
│   │   └── MemberMapper.java
│   ├── pojo/                # 实体类
│   │   ├── Admin.java       # 管理员
│   │   ├── Employee.java    # 员工
│   │   ├── Equipment.java   # 器材
│   │   └── Member.java      # 会员
│   ├── config/              # 配置类
│   └── GymApplication.java  # 启动类
├── src/main/resources/
│   ├── mapper/              # MyBatis XML 映射文件
│   │   ├── AdminMapper.xml
│   │   ├── EmployeeMapper.xml
│   │   ├── EquipmentMapper.xml
│   │   └── MemberMapper.xml
│   └── application.yml      # 配置文件
└── pom.xml                  # Maven 依赖配置
```

## ✨ 核心功能

### 1. 用户认证与授权

#### 管理员登录
- **接口**: `POST /api/adminLogin`
- **功能**: 管理员账号密码验证，登录后保存会话信息
- **会话数据**: 管理员信息、统计数据（会员总数、员工总数、器材总数等）

#### 会员登录
- **接口**: `POST /api/userLogin`
- **功能**: 会员账号密码验证，登录后保存个人信息
- **会话数据**: 会员完整信息（姓名、性别、年龄、手机号、办卡信息等）

### 2. 员工管理 (Employee)

- **查询所有员工**: `GET /api/employee/selEmployeeList`
- **查询单个员工**: `GET /api/employee/queryEmployee/{employeeAccount}`
- **添加员工**: `POST /api/employee/addEmployee`
  - 自动生成工号（1010开头 + 5位随机数）
  - 自动记录入职时间
- **删除员工**: `DELETE /api/employee/deleteEmployee/{employeeAccount}`
- **更新员工**: `PUT /api/employee/updateEmployee`

### 3. 器材管理 (Equipment)

- **查询所有器材**: `GET /api/equipment/selEquipmentList`
- **查询单个器材**: `GET /api/equipment/queryEquipment/{equipmentId}`
- **添加器材**: `POST /api/equipment/addEquipment`
  - 主键自增，无需手动指定 ID
- **删除器材**: `DELETE /api/equipment/deleteEquipment/{equipmentId}`
- **更新器材**: `PUT /api/equipment/updateEquipment`

### 4. 会员管理 (Member)

- **查询所有会员**: `GET /api/member/selMemberList`
- **查询单个会员**: `GET /api/member/queryMember/{memberAccount}`
- **添加会员**: `POST /api/member/addMember`
  - 自动生成会员号（2021开头 + 5位随机数）
  - 默认密码：123456
  - 自动记录办卡时间
  - 自动设置剩余课程数
- **删除会员**: `DELETE /api/member/deleteMember/{memberAccount}`
- **更新会员**: `PUT /api/member/updateMember`

### 5. AI 智能咨询 (Chat)

- **智能问答**: `POST /api/chat/query`
- **功能**: 
  - 基于 DeepSeek AI 模型提供健身咨询服务
  - 自动结合会员个人信息（姓名、身体数据等）生成个性化回答
  - 需要登录后使用（通过 Session 验证）

## 🗄️ 数据库设计

### 数据表说明

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| admin | 管理员表 | admin_account, admin_password |
| employee | 员工表 | employee_account, employee_name, employee_gender, employee_age, entry_time, staff, employee_message |
| equipment | 器材表 | equipment_id(自增), equipment_name, equipment_location, equipment_status, equipment_message |
| member | 会员表 | member_account, member_password, member_name, member_gender, member_age, member_height, member_weight, member_phone, card_time, card_class, card_next_class |

### 数据库配置

在 `application.yml` 中配置数据库连接：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gym_management_system
    username: root
    password: your_password
```

## 🚀 快速开始

### 前置要求

- JDK 17 或更高版本
- Maven 3.6+
- MySQL 8.0+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone <repository-url>
   cd Gym
   ```

2. **创建数据库**
   ```sql
   CREATE DATABASE gym_management_system CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```

3. **导入数据表**
   - 执行 SQL 脚本创建所需的数据表（admin, employee, equipment, member）

4. **修改配置**
   - 编辑 `src/main/resources/application.yml`
   - 修改数据库用户名和密码
   - 配置 DeepSeek API Key（如需使用 AI 咨询功能）

5. **构建项目**
   ```bash
   mvn clean install
   ```

6. **运行项目**
   ```bash
   mvn spring-boot:run
   ```
   
   或者打包后运行：
   ```bash
   mvn package
   java -jar target/gym-management-system-0.0.1-SNAPSHOT.jar
   ```

7. **访问应用**
   - 默认端口: `http://localhost:8080`

## 📡 API 使用示例

### 管理员登录

```bash
curl -X POST http://localhost:8080/api/adminLogin \
  -H "Content-Type: application/json" \
  -d '{
    "adminAccount": "admin",
    "adminPassword": "123456"
  }'
```

### 会员登录

```bash
curl -X POST http://localhost:8080/api/userLogin \
  -H "Content-Type: application/json" \
  -d '{
    "memberAccount": 202112345,
    "memberPassword": "123456"
  }'
```

### 添加会员

```bash
curl -X POST http://localhost:8080/api/member/addMember \
  -H "Content-Type: application/json" \
  -d '{
    "memberName": "张三",
    "memberGender": "男",
    "memberAge": 25,
    "memberHeight": 175,
    "memberWeight": 70,
    "memberPhone": 13800138000,
    "cardClass": 10
  }'
```

### AI 咨询（需要先登录）

```bash
curl -X POST http://localhost:8080/api/chat/query \
  -H "Content-Type: application/json" \
  -H "Cookie: JSESSIONID=your_session_id" \
  -d '{
    "content": "我想制定一个减脂计划",
    "model": "deepseek-chat"
  }'
```

## 🔐 Session 管理

系统使用 HttpSession 进行会话管理：

- **管理员会话**: 存储管理员信息和系统统计数据
- **会员会话**: 存储会员个人信息和办卡信息
- **会话保持**: 通过 Cookie 中的 JSESSIONID 自动维护

### Apifox 测试提示

1. 先调用登录接口，Apifox 会自动保存 Cookie
2. 后续请求会自动携带 JSESSIONID
3. 确保开启 Apifox 的"自动管理 Cookie"功能

## ⚙️ 配置说明

### application.yml 主要配置项

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/gym_management_system
    username: root
    password: your_password
    
mybatis:
  mapper-locations: classpath:mapper/*.xml
  configuration:
    map-underscore-to-camel-case: true  # 下划线转驼峰
  type-aliases-package: com.jinjin.gymmanagementsystem.pojo

deepseek:
  api:
    key: your_api_key
    url: https://api.deepseek.com/chat/completions
  model: deepseek-chat
```

## 🎯 特色功能

1. **自动化编号生成**: 员工工号和会员号自动生成，避免重复
2. **智能会话管理**: 登录后自动保存用户信息到 Session
3. **AI 个性化服务**: 结合会员身体数据提供定制化健身建议
4. **RESTful API 设计**: 规范的接口设计，易于前端对接
5. **MyBatis 自动映射**: 支持下划线字段名到驼峰属性名的自动转换

## 📝 开发规范

- Controller 层: 处理 HTTP 请求，返回统一格式的响应
- Service 层: 业务逻辑处理
- Mapper 层: 数据库操作
- POJO 层: 实体类定义，使用 Lombok 简化代码

### 统一响应格式

```json
{
  "success": true,
  "message": "操作成功",
  "data": {}
}
```

## 🐛 常见问题

### 1. 登录提示"账号或密码错误"

- 检查数据库中是否有对应的账号数据
- 确认参数名称是否与实体类字段匹配
- 查看控制台日志确认接收到的参数值

### 2. Session 无法保持

- 确认前后端域名和端口一致
- 检查 Cookie 是否正确传递
- 查看浏览器开发者工具中的 Network 标签

### 3. 器材 ID 为 null

- 确保数据库表中 equipment_id 设置为 AUTO_INCREMENT
- 检查 MyBatis insert 语句是否配置了 `useGeneratedKeys="true"`

## 📄 许可证

本项目仅供学习使用。

## 👨‍💻 作者

- Author: jinjin
- Date: 2026/4/24

## 🔄 更新日志

### v1.0.0 (2026-04-26)
- ✅ 完成管理员登录功能
- ✅ 完成员工管理模块（增删改查）
- ✅ 完成器材管理模块（增删改查）
- ✅ 完成会员管理模块（增删改查）
- ✅ 完成会员登录功能
- ✅ 集成 DeepSeek AI 咨询服务
- ✅ 实现 Session 会话管理

---

**注意**: 使用前请确保已正确配置数据库和 API Key。如有问题，请查看控制台日志进行调试。
