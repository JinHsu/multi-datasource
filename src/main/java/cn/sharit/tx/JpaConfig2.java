package cn.sharit.tx;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Objects;

@Configuration
@EnableJpaRepositories(basePackages = "cn.sharit.repository2", //
		entityManagerFactoryRef = "localContainerEntityManagerFactoryBean2", //
		transactionManagerRef = "platformTransactionManager2")
public class JpaConfig2 {

	@Autowired
	@Qualifier(value = "ds2")
	DataSource ds2;

	@Autowired
	JpaProperties jr;

	@Bean
	LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean2(
			EntityManagerFactoryBuilder builder) {
		return builder.dataSource(ds2).properties(jr.getProperties()).packages("cn.sharit.entity")
				.persistenceUnit("pu2").build();

	}

	@Bean
	PlatformTransactionManager platformTransactionManager2(EntityManagerFactoryBuilder builder) {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = localContainerEntityManagerFactoryBean2(
				builder);
		return new JpaTransactionManager(Objects.requireNonNull(localContainerEntityManagerFactoryBean.getObject()));
	}

}
