# CLAUDE.md — DDD Demo 项目指南

## 项目概述

基于 DDD（领域驱动设计）四层架构的 Spring Boot 演示项目，展示限界上下文、聚合根、值对象、仓储模式、防腐层等 DDD 核心概念。

## 技术栈

| 组件 | 版本/方案 |
|------|----------|
| Java | 8 |
| Spring Boot | 2.7.0 |
| 构建工具 | Maven |
| 简化代码 | Lombok 1.18.30 |
| ORM | 未引入（PO/Mapper 已定义，待接入 MyBatis/JPA） |

## DDD 四层架构

```
interfaces (接口层)     ← 仅此层依赖 Spring MVC
    ↓ 依赖
application (应用层)    ← 编排、事务、调用领域服务
    ↓ 依赖
domain (领域层)         ← 核心业务逻辑，不依赖任何外部框架
    ↑ 实现
infrastructure (基础设施层) ← 实现 domain 定义的接口（DIP 倒置）
```

**关键规则**：
- 依赖方向：`interfaces → application → domain ← infrastructure`
- domain 层不依赖任何外部框架（Spring 注解仅限 `@Component` 在 Service 上）
- 仓储接口定义在 domain 层，实现在 infrastructure 层
- interfaces 层不直接调用 domain 层，必须经过 application 层

## 限界上下文

### 1. user 上下文（用户）

```
user/
├── interfaces/                          # 接口层
│   ├── dto/LoginRequest.java            # REST 请求 DTO
│   └── rest/UserController.java         # GET /login
├── application/                         # 应用层
│   ├── command/LoginCommand.java        # 登录命令对象
│   ├── command/PayCommand.java          # 支付命令对象
│   ├── dto/UserDto.java                 # 应用层 DTO
│   └── UserAppApplication.java          # 应用服务（编排登录/支付流程）
├── domain/                              # 领域层
│   ├── model/aggregate/
│   │   ├── UserAgg.java                 # 用户聚合根
│   │   └── QualificationAgg.java        # 资质聚合根
│   ├── model/valueobject/
│   │   ├── UserAggId.java               # 用户标识符
│   │   ├── Username.java                # 用户名
│   │   └── Password.java                # 密码
│   ├── repository/
│   │   ├── IUserCommandRepository.java  # 用户命令仓储接口
│   │   └── IUserQueryRepository.java    # 用户查询仓储接口
│   ├── service/UserDomainService.java   # 领域服务
│   └── event/UserEvent.java             # 用户领域事件（不含密码）
└── infrastructure/                      # 基础设施层
    ├── repository/
    │   ├── po/
    │   │   ├── UserPO.java              # 用户表 t_user 映射
    │   │   └── QualificationPO.java     # 资质表 t_qualification 映射
    │   ├── mapper/
    │   │   ├── UserMapper.java           # 用户 Mapper 接口
    │   │   └── QualificationMapper.java # 资质 Mapper 接口
    │   ├── converter/
    │   │   ├── UserConverter.java        # UserPO ↔ UserAgg 转换
    │   │   └── QualificationConverter.java
    │   ├── UserCommandRepository.java    # 仓储实现
    │   └── UserQueryRepository.java      # 仓储实现
    └── acl/
        ├── PayAclService.java            # 防腐层：调用 pay 上下文
        └── PayApiParam.java              # ACL 参数对象
```

### 2. pay 上下文（支付）

```
pay/
├── interfaces/
│   ├── dto/PayRequest.java
│   └── rest/PayController.java          # GET /pay
├── application/
│   ├── command/PayCommand.java
│   └── PayAppApplication.java
├── domain/
│   ├── model/aggregate/PayAgg.java      # 支付聚合根
│   ├── model/valueobject/
│   │   ├── PayAggId.java                # 支付标识符
│   │   └── Money.java                   # 金额（value + unit）
│   ├── repository/
│   │   ├── IPayCommandRepository.java
│   │   └── IPayQueryRepository.java
│   ├── service/PayDomainService.java
│   └── event/PayEvent.java
└── infrastructure/
    └── repository/
        ├── po/PayPO.java                # 支付表 t_pay 映射
        ├── mapper/PayMapper.java
        ├── converter/PayConverter.java
        ├── PayCommandRepository.java
        └── PayQueryRepository.java
```

### 3. common 共享内核

```
common/
├── events/
│   ├── DomainEvent.java      # 领域事件抽象基类
│   ├── EventBus.java          # 事件总线
│   └── Listener.java           # 事件监听器（只依赖 DomainEvent 抽象）
├── result/
│   ├── Result.java            # 通用响应包装 Result<T>
│   └── DomainResult.java      # 领域结果
└── helper/MQHelper.java       # MQ 辅助（桩代码）
```

## 设计约定

### 值对象（Value Object）
- **必须不可变**：`@Getter` + `@AllArgsConstructor` + `private final` 字段
- **禁止** `@Data`（含 setter）
- 放在 `domain/model/valueobject/` 包下

### 聚合根（Aggregate Root）
- **禁止公开 setter**：使用 `@Getter` 而非 `@Data`
- 通过静态工厂方法 `init()` 或 `@Builder` 创建
- 内部维护 `List<DomainEvent> events` 列表
- 放在 `domain/model/aggregate/` 包下

### 仓储（Repository）
- **接口**在 domain 层的 `domain/repository/` 包下定义
- **实现**在 infrastructure 层的 `infrastructure/repository/` 包下
- 命名：`I{X}CommandRepository`（写操作）+ `I{X}QueryRepository`（读操作），CQRS 风格

### 数据库实体（PO）
- 放在 `infrastructure/repository/po/` 包下
- 使用 `@Data`（纯数据载体，无行为）
- 值对象在 PO 中展开为平铺字段（如 `Money(value, unit)` → `moneyValue` + `moneyUnit`）
- `createTime`/`updateTime` 仅存在于 PO 中，不放领域模型（审计字段是基础设施关注点）

### PO ↔ Domain 转换
- 转换器放在 `infrastructure/repository/converter/` 包下
- 静态方法：`toDomain(PO)` 组装值对象，`toPO(Aggregate)` 展开值对象
- 领域层不感知 PO 的存在

### 应用服务
- 不包含业务逻辑，只做流程编排
- 标准流程：查询聚合根 → 调用领域服务 → 持久化 → 发布事件
- 返回 `Result<T>` 泛型类型

### 跨上下文通信
- 必须通过 **ACL 防腐层**（`infrastructure/acl/`），不直接导入其他上下文的领域对象
- ACL 服务封装外部调用，使用自己的参数对象

### 领域事件
- 不暴露敏感信息（如 `UserEvent` 不含密码字段）
- 事件字段使用 `private final`
- `Listener` 只依赖 `DomainEvent` 抽象基类，不依赖具体事件类型

## 数据流

```
HTTP Request
  → interfaces/dto/XxxRequest       (DTO)
  → Controller 转换为 Command
  → application/command/XxxCommand   (Command)
  → ApplicationService               (编排)
    → QueryRepository.query()        (infrastructure 实现 → Mapper → PO → Converter → Domain)
    → DomainService.xxx(aggregate)   (领域逻辑)
    → CommandRepository.save()       (Domain → Converter → PO → Mapper → DB)
    → EventBus.publish(event)        (领域事件)
  → Result<T>                        (响应)
```

## DB 表映射

| 表名 | PO 类 | 领域聚合根 |
|------|-------|-----------|
| `t_user` | `UserPO` | `UserAgg` (Username, Password, UserAggId) |
| `t_qualification` | `QualificationPO` | `QualificationAgg` |
| `t_pay` | `PayPO` | `PayAgg` (PayAggId, Money) |

## API 端点

| 上下文 | 端点 | 方法 | 说明 |
|--------|------|------|------|
| user | `/login` | GET | 用户登录 |
| pay | `/pay` | GET | 支付操作 |

## Git 操作规则

- 修改/新增文件后自动 `git add`（PostToolUse Hook on Write|Edit）
- `git commit` 和 `git push` 需要用户确认
- 工作目录：`/Users/zhihengzhu/Desktop/github/ddd-demo`
- IntelliJ 项目根目录是 `3D/`（pom.xml 所在），`.claude/` 在父目录

## 已知桩代码

以下类是演示桩代码，仅有空实现或框架声明：
- `QualificationAgg` — 空壳聚合根
- `Arrange` — 空组件
- `MQHelper` — MQ 辅助空类
- `UserDomainService` / `PayDomainService` — 薄层委托，直接调用聚合根方法
- `PayAclService` — 防腐层桩，仅打印日志
- `UserMapper` / `PayMapper` 等 — 仅定义接口，无 ORM 实现
- `*Converter.toDomain()` 中的查询逻辑为演示代码，实际需配合 ORM

## 项目入口

`xuanmi.ning.App` — Spring Boot 主启动类
