package com.vrv.cems.service.dbtools;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * <B>说 明</B>:连接池类
 * 
 * @author 作 者 名：张鸿杰<br/>
 *         E-mail ：zhanghongjie@qq.com
 * 
 * @version 版 本 号：V1.0.20130718<br/>
 *          创建时间：2014年08月10日 上午16:14:10
 */
public class ConnectionPool  {
    public String mConfigFilePath;
    public DataSource mDataSource = null;
    public static ConnectionPool pool = null;
    public static Logger logger = Logger.getLogger(ConnectionPool.class);

    /**
     * 单例
     * @param appContextPath
     * @return
     * @throws Exception
     */
    public static synchronized ConnectionPool getInstance(String appContextPath) throws Exception {
        if (pool == null) {
            pool = new ConnectionPool(appContextPath);
            pool.setupDataSource();
            // 初始化连接池
            if (pool.mDataSource == null)
                pool.mDataSource = pool.setupDataSource();
        }
        return pool;
    }

    public ConnectionPool() {}

    public ConnectionPool(String contextPath) throws Exception {
        this.mConfigFilePath = contextPath;
        this.mDataSource = setupDataSource();
    }

    public Connection getConnection() throws Exception {
        return mDataSource.getConnection();
    }

    public void releaeConnection(Connection con) throws SQLException {
        if (con != null)
            con.close();
    }

    private DataSource setupDataSource() throws Exception {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream(this.mConfigFilePath);
        try {
            prop.load(file);
        } catch (Exception e) {
            logger.debug("[ERROR_INFO] || 读取数据库连接配置文件" + this.mConfigFilePath + "失败");
        } finally {
            file.close();
        }
        return DruidDataSourceFactory.createDataSource(prop);
    }
}
