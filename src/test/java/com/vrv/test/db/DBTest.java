package com.vrv.test.db;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.sys.common.util.PrintUtils;
import com.vrv.cems.service.cache.bean.CInstallPackBean;
import com.vrv.cems.service.cache.bean.PolicyBean;
import com.vrv.cems.service.dbtools.DBUtil;


public class DBTest {
	private Logger log = Logger.getLogger(DBTest.class);
	/**
	 * 初始化一些该单元测试类需要的数据
	 */
	@Before
	public void init(){
		
		
	
	}
	
	@Test
	public void testAddData()
	{
	
	
	}
	
	@Test
	public void testDelData()
	{
		/*QueryRunner run =DBUtil.queryRunner;
		CollectSoftBean softBean = null;
		String id="123213";
			if(StringUtils.isNotBlank(id)){
				
				String sql = "select * from cems_collectsoft where id = '"
						+ id + "'";
				try {
					softBean = run.query(sql.toString(), new BeanHandler<CollectSoftBean>(
							CollectSoftBean.class));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			System.out.println(""+softBean.getId()+"....."+softBean.getName()+","+softBean.getTest0());*/
			
		
	}
	
	
	/*@Test
	public void testUpdateData() throws SQLException
	{
		List<CInstallPackBean> softBeanList=new ArrayList<CInstallPackBean>();
		List<PolicyBean> softBeanList1=new ArrayList<PolicyBean>();
		softBeanList=DBUtil.queryByList(CInstallPackBean.class);
		
		System.out.println("softBeanList.size"+softBeanList.size());
		
		Map<String,String> conditions = new HashMap<String,String>();
		
		conditions.put("isPublish", "1");//approvalState
		conditions.put("approveState", "1");
		conditions.put("state", "1");
		
		softBeanList1=DBUtil.queryConditionsByList(PolicyBean.class, conditions);
		List<String> targetList=new ArrayList<String>();
		
		for(PolicyBean pb:softBeanList1)	
		{  
			StringBuffer policyTarget=new StringBuffer();
			//System.out.println("pb.getId"+pb.getId());
			
			String sql="select b.* from cems_policy_target_mapping a,cems_policy_target b where  a.targetId=b.id and a.policyId=?";
			targetList=DBUtil.queryRunner.query(sql, new ColumnListHandler<String>("target"),pb.getId());
			for(int i=0;i<targetList.size();i++)
			{

					
				if(i>=1&&i<=targetList.size()-1){
					System.out.println(i>=1&&i<=targetList.size()-1);
					 policyTarget.append("|");
				}
				policyTarget.append(targetList.get(i));
			}
			pb.setObj(policyTarget.toString());
			System.out.println("pb.getId"+pb.getId()+"pb.getObj"+pb.getObj());
		}
		for(String target:targetList)
		{
			System.out.println("target"+target);
		}
		//System.out.println("softBeanList1.size"+softBeanList1.size());
	}
	*/

}
