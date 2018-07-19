 /*慕课网手记链接：https://www.imooc.com/article/30638*/ 
  
  结合DRUIS的DRDS项目结构（5步骤）
  1.项目依赖包构建
  2.增加druid的spring监控配置
  3.修改项目配置文件(application.properties)
  4.增加druid配置类
  5.在启动类上增加druid的spring监控配置文件引用的注解
  
步骤1：
POM表依赖
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid</artifactId>
	<version>1.1.9</version>
</dependency>
<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
	<groupId>com.aliyun</groupId>
	<artifactId>aliyun-java-sdk-drds</artifactId>
	<version>2.4.0</version>
</dependency>
<dependency>
	<groupId>com.aliyun</groupId>
	<artifactId>aliyun-java-sdk-core</artifactId>
	<version>2.2.1</version>
</dependency>


步骤2：
在项目的resource目录下放置druid-spring.xml文件（推荐），当然直接放在src上也可以，xml内容如下：
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:aop="http://www.springframework.org/schema/aop" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        http://www.springframework.org/schema/aop/spring-aop.xsd">
    <!-- 配置_Druid和Spring关联监控配置 -->
    <bean id="druid-stat-interceptor"
        class="com.alibaba.druid.support.spring.stat.DruidStatInterceptor"></bean>
    <!-- 方法名正则匹配拦截配置 -->
    <bean id="druid-stat-pointcut" class="org.springframework.aop.support.JdkRegexpMethodPointcut"
        scope="prototype">
        <property name="patterns">
            <list>
            	<!--此处更具实际项目的包名更改-->
                <value>com.beini.*</value>
            </list>
        </property>
    </bean>
    <aop:config proxy-target-class="true">
        <aop:advisor advice-ref="druid-stat-interceptor"
	pointcut-ref="druid-stat-pointcut" />
    </aop:config>
</beans>

步骤3：
修改项目配置文件（application.properties）：
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/shop2?useUnicode=true&autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=round&useSSL=false&rewriteBatchedStatements=true&socketTimeout=30000&connectTimeout=3000
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.type= com.alibaba.druid.pool.DruidDataSource

#druid standard config
spring.datasource.maxActive=30
spring.datasource.initialSize=3
spring.datasource.minIdle=3
spring.datasource.maxWait=120000
spring.datasource.timeBetweenEvictionRunsMillis=60000
spring.datasource.minEvictableIdleTimeMillis=300000
spring.datasource.validationQuery=select 'z'
spring.datasource.testWhileIdle=true
spring.datasource.testOnBorrow=false
spring.datasource.testOnReturn=false

#druid extends config
spring.datasource.filters= stat,wall,log4j
spring.datasource.poolPreparedStatements= true
spring.datasource.maxOpenPreparedStatements= 20
spring.datasource.logSlowSql= true

步骤4：
新建以下连接池配置类（建议配置类所在目录为项目启动类下的直接子包下）:
/*Druid配置类*/
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
public class DruidConfig {

	@Value("${spring.datasource.url}")
	private String dbUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.filters}")
	private String filters;

	@Value("${spring.datasource.logSlowSql}")
	private String logSlowSql;

	@Bean
	// @Primary 注解作用是当程序选择dataSource时选择被注解的这个
	@Primary
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasource;
	}

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", username);
		reg.addInitParameter("loginPassword", password);
		reg.addInitParameter("logSlowSql", logSlowSql);
		return reg;
	}

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}
}

步骤5：
在springboot的启动类中加上如下注解：@ImportResource(locations = { "classpath:druid-spring.xml" })


