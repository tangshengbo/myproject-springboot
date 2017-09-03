package com.tangshengbo.javaconfig;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import java.util.Properties;

/**
 * Created by TangShengbo on 2016/12/26.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig implements TransactionManagementConfigurer {

    //DataSource配置
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new org.apache.tomcat.jdbc.pool.DataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
//        sqlSessionFactoryBean.setTypeAliasesPackage(MODEL_PACKAGE);
        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("reasonable", "true"); //页码<=0 查询第一页，页码>=总页数查询最后一页
        props.setProperty("supportMethodsArguments", "true"); //支持通过 MyMapper 接口参数来传递分页参数
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        props.setProperty("offsetAsPageNum", "true");
        props.setProperty("rowBoundsWithCount", "true");
        props.setProperty("pageSizeZero", "true");//分页尺寸为0时查询所有纪录不再执行分页
        pageHelper.setProperties(props);
        //添加插件
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapping/*.xml"));
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
