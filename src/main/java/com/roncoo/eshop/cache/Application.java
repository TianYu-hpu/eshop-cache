package com.roncoo.eshop.cache;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.sql.DataSource;
import java.util.HashSet;
import java.util.Set;

@Data
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@PropertySource(value={"classpath:application.properties"})
@ConfigurationProperties(prefix = "spring.datasource")
@MapperScan("com.roncoo.eshop.cache.mapper")
public class Application {

	@Value("url")
	private String url;
	@Value("username")
	private String username;
	@Value("password")
	private String password;

	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/base/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Bean
	public JedisCluster JedisClusterFactory() {
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort("192.168.0.131", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.0.132", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.0.133", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.0.134", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.0.135", 6379));
		jedisClusterNodes.add(new HostAndPort("192.168.0.136", 6379));
		JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes);
		return jedisCluster;
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigures() {
		return new PropertySourcesPlaceholderConfigurer();
	}

}