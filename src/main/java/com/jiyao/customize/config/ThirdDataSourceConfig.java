//package com.jiyao.customize.config;
//
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
///**
// * 扫描 Mapper 接口并容器管理
// * @author 36536
// */
//@Configuration
//@MapperScan(basePackages = ThirdDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "thirdSqlSessionFactory")
//public class ThirdDataSourceConfig {
//
//    /**
//     * 精确到 cluster 目录，以便跟其他数据源隔离
//     */
//    static final String PACKAGE = "com.chuweizi.customize.dao.third";
//    //static final String MAPPER_LOCATION = "classpath:mapper/third/*.xml";
//
//    @Value("${third.datasource.url}")
//    private String url;
//
//    @Value("${third.datasource.username}")
//    private String user;
//
//    @Value("${third.datasource.password}")
//    private String password;
//
//    @Value("${third.datasource.driverClassName}")
//    private String driverClass;
//
//    @Bean(name = "thirdDataSource")
//    public DataSource thirdDataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(driverClass);
//        dataSource.setUrl(url);
//        dataSource.setUsername(user);
//        dataSource.setPassword(password);
//        return dataSource;
//    }
//
//    @Bean(name = "thirdTransactionManager")
//    public DataSourceTransactionManager thirdTransactionManager() {
//        return new DataSourceTransactionManager(thirdDataSource());
//    }
//
//    @Bean(name = "thirdSqlSessionFactory")
//    public SqlSessionFactory thirdSqlSessionFactory(@Qualifier("thirdDataSource") DataSource thirdDataSource)
//            throws Exception {
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(thirdDataSource);
//        /*sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources(MasterDataSourceConfig.MAPPER_LOCATION));*/
//
//        return sessionFactory.getObject();
//    }
//}
