##码农学习日常

##学习资料获取
[Spring 文档](https://spring.io/guides)
[Spring Web](http://spring.io/guide/gs/serving-web-content/)
[es](http://elasticsearch.cn/explore)
[Github deploy key](https://developer.github.com/v3/guides/managing-deploy-keys/#deploy-keys)
[bootstrap](https://v3.bootcss.com/getting-started)  
[Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
[Spring](http://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)
[Flyway](https://flywaydb.org/getstarted/firststeps/maven)
[菜鸟教程]
[Thymeleaf]

##学习工具
[Git](https://git-scm.com/download)
[Visual Paradigm](https://www.visual-paradigm.com)

##脚本
```sql
CREATE TABLE USER{
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
};
 ```
```bash
mvn flyway:migrate
```