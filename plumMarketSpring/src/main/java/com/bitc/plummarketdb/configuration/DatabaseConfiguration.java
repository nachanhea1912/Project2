package com.bitc.plummarketdb.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

    //@Autowired : 스프링 프레임워크가 해당 객체를 생성하여 관리한다는 의미의 어노테이션 자기가 알아서 만들고 필요없으면 삭제
    @Autowired
    private ApplicationContext applicationContext; // new 라는것을 안써도 됨 필요할때 만듬.

    @Bean // @Bean : 자바빈즈를 의미하는 어노테이션, 사용자가 직접 생성한 클래스의 자바 빈즈가 아닌 라이브러리로 제공되는 클래스의 자바 빈즈
    @ConfigurationProperties(prefix = "spring.datasource.hikari") //설정 파일 안에서 특정 설정 내용을 가져오는 어노테이션
    //prefix : 접두사 , 시작단어 설정
    public HikariConfig hikariConfig(){
        //DataSource : 데이터 베이스 사용 시 커넥션 풀에 데이터 베이스 설정 정보를 저장하고 데이터베이스와 연결을 미리 생성하고, 관리하는 클래스.
        //application.properties 파일안에 존재하는 'spring.datasource.hikari'로 시작하는 설정 정보를 모두 가져와서
        //HikariConfig'클래스 타입의 객체 생성
        return new HikariConfig();
    }

    //커넥션 풀 : connection을 미리 먼저 만들어놓고 하나 던져줌 빠르게 사용가능
    @Bean
    public DataSource dataSource() throws Exception{
        //HikariCp에서 제공하는 HikariDataSource 클래스 타입의 객체를 사용하여 DataSource 생성
        //객체 생성 시 HikariConfig 클래스 타입의 객체로 데이터 베이스 설정 정보를 사용함.
        DataSource dataSource = new HikariDataSource(hikariConfig());//커넥션 소스를 만드는게 DataSource고 라이브러리 제공하는 HikariDataSource를 씀.
        System.out.println(dataSource.toString());
        return dataSource;
    }
    //실제 데이터 베이스 연결 및 사용정보
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //setMapperLocations : mybatis 사용 시 mybatis의 SQL 쿼리 파일 위치를 설정
        //getResources : 실제 mybatis sql 쿼리 파일을 가져오는 메소드, getResource /getResources 2개가 존재함.

        //** : 모든 하위폴더를 의미
        //getResource()는 지정된 파일 하나만 가져오기, getResources() 지정된 패턴의 파일을 모두 가져옴.
        // /sql/&**/sql-*.xml : sql 폴더아래의 모든 폴더에서 시작단어가 'sql-' 이며 확장자는 '.xml'인 파일을 모두 사용
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/sql/**/sql-*.xml"));
        //mybatis 설정을 추가하여 사용.
        sqlSessionFactoryBean.setConfiguration(mybatisConfig());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);

    }
    @Bean
    @ConfigurationProperties(prefix = "mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        //org.apache.ibatis.session.Configuration : 스프링 프레임워크의 Configuration 클래스와 이름이 동일하기 때문에
        //mybatis의 Configuration 클래스는 패키지 명까지 모두 사용
        //'application.properties'파일의 'mybatis.configuration'로 시작하는 설정 내용을 모두가져와서 사용함.
        return new org.apache.ibatis.session.Configuration();
    }
}