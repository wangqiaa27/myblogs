package cn.jhc;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.h2.jdbcx.JdbcDataSource;


public class UtilsTest {
	private static DataSource getDataSource() {
		
		JdbcDataSource ds = new JdbcDataSource();
		ds.setUrl("jdbc:h2:~/ds");
		ds.setUser("sa");
		return ds;
	}
	private static QueryRunner getRunner() {
		return new QueryRunner(getDataSource());
		
	}
	private static void createTable() throws SQLException{
		String sql = "create table if not exists user (id int auto_increment primary key,"
				+"username varchar(32) not null unique,"
				+"`password` varchar(128) not null)";
		getRunner().execute(sql);
	}
	private static void insertData() throws SQLException {
		String delSql = "delete from user";
		String sql = "insert into user(username,`password`) values('lucy','6666')";
		String sql2 = "insert into user(username,`password`) values('alise','7777')";
		getRunner().execute(delSql);
		getRunner().execute(sql);
		getRunner().execute(sql2);
	}
	private static void select()  throws SQLException{
		String sql = "select username ,`password` from user";
		List<User> users = getRunner().query(sql,new BeanListHandler<>(User.class));
		for (User user : users) {
			System.out.println(user);
		}
	}
	public static void main(String[] args) throws SQLException {
		createTable();
		insertData();
		select();
	}
}
