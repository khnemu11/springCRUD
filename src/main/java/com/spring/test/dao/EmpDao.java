package com.spring.test.dao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.spring.test.beans.Emp;

@Repository("dao")
public class EmpDao {
	@Resource(name="jt")
	JdbcTemplate template;
	@Resource(name="sqlSessoinTemplate")
	SqlSessionTemplate session;
	
	public void setTemplate(JdbcTemplate template) {    
	    this.template = template;    
	}    
	public void setSession(SqlSessionTemplate session) {
		this.session = session;
	}
	public int save(Emp p){    
		int result = session.insert("sample_mapper.insert", p);
		return result;
		/*
		 * String
		 * sql="insert into Emp99(name,salary,designation) values('"+p.getName()+"',"+p.
		 * getSalary()+",'"+p.getDesignation()+"')"; return template.update(sql);
		 */
	}    
	public int update(Emp p){    
		 int result = session.update("sample_mapper.updateId", p);
		 return result;
		
		/*
		 * String sql="update Emp99 set name='"+p.getName()+"', salary="+p.getSalary()+
		 * ",designation='"+p.getDesignation()+"' where id="+p.getId()+""; return
		 * template.update(sql);
		 */
	}    
	public int delete(int id){    
		 int result = session.delete("sample_mapper.deleteId", id);
		 return result;
		/*
		 * String sql="delete from Emp99 where id="+id+""; return template.update(sql);
		 */  
	}    
	public Emp getEmpById(int id){    
	    Emp emp = session.selectOne("sample_mapper.selectId",id);
	    return emp;
		/*
		 *    String sql="select * from Emp99 where id=?";    
		 * return template.queryForObject(sql, new Object[]{id},new
		 * BeanPropertyRowMapper<Emp>(Emp.class));
		 */	}    
	public List<Emp> getEmployees(){    
		System.out.println("Get employee start!");
		
		List<Emp> empList = session.selectList("sample_mapper.selectAll");
		
		return empList;
		/*
		 * return template.query("select * from Emp99",new RowMapper<Emp>(){ public Emp
		 * mapRow(ResultSet rs, int row) throws SQLException { Emp e=new Emp();
		 * e.setId(rs.getInt(1)); e.setName(rs.getString(3));
		 * e.setSalary(rs.getFloat(2)); e.setDesignation(rs.getString(4)); return e; }
		 * });
		 */	}    
	}   