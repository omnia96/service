## OMNIA96

### 资料
 [spring Guides](https://spring.io/guides)
 
 [spring Web](https://spring.io/guides/gs/serving-web-content/)
 
 [spring Docs](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/#boot-features-sql)
 
 [Kotlin Docs](https://kotlinlang.org/docs)
 
 [Bootstrap Docs](https://v3.bootcss.com)
 
 [Github OAuth](https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/)
 
 [mybatis Docs](http://www.mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
 
 ### 工具
 
 [Spring Initializr](https://start.spring.io/)
 
 ### Sql
 
 ```
 create table [user]
 (
 	id int identity
 		constraint user_pk
 			primary key nonclustered,
 	name text,
 	account_id text,
 	token text,
 	gmt_reate text,
 	gmt_modified text
 )
 go
 ```