package cn.sharit.ds;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DataSourceConfig {

	@Primary // 表示这个数据源是默认数据源, 这个注解必须要加，因为不加的话spring将分不清楚那个为主数据源（默认数据源）
	@Bean("ds1")
	@ConfigurationProperties(prefix = "spring.datasource.ds1") // 读取application.yml中的配置参数映射成为一个对象
	public DataSource getDs1() {
		return DataSourceBuilder.create().build();
	}

	@Bean("ds2")
	@ConfigurationProperties(prefix = "spring.datasource.ds2") // 读取application.yml中的配置参数映射成为一个对象
	public DataSource getDs2() {
		return DataSourceBuilder.create().build();
	}

}
