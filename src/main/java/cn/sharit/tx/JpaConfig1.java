package cn.sharit.tx;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "cn.sharit.repository1", //
		entityManagerFactoryRef = "localContainerEntityManagerFactoryBean1", //
		transactionManagerRef = "platformTransactionManager1")
public class JpaConfig1 {

	@Autowired
	@Qualifier(value = "ds1")
	DataSource ds1;

	@Autowired
	JpaProperties jr;

	@Primary
	@Bean
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean1(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(ds1).properties(jr.getProperties()).packages("cn.sharit.entity")
				.persistenceUnit("pu1").build();

	}

	@Primary
	@Bean
	PlatformTransactionManager platformTransactionManager1(EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBean1(
				builder);
		return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
	}

}
