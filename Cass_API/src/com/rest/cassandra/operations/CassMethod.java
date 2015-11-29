package com.rest.cassandra.operations;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;

import com.rest.casssandra.dao.cassDAO;


public class CassMethod extends cassDAO{

	ResultSet result1;
	/*View Details of Product @parameter=product ID    
	 * This method accesses structured data stored in Cassandra  */
	
		public JSONArray viewProd(String prod_name)
		 {
			StringBuffer sb=new StringBuffer();
			Map<Integer, String> map=new HashMap<Integer,String>(); 
			
			System.out.println("The value of prodid is : " + prod_name);
			
			JSONArray json=new JSONArray();
			JSONArray temp=new JSONArray();
			
			
			try{
				Conn();
				PreparedStatement ps= session.prepare("select product_id,product_name from inventory where product_name = ? ;");
				BoundStatement boundStatement = new BoundStatement(ps); 
				result1=session.execute(boundStatement.bind(prod_name));
			
			for (Row row:result1)
			  {
				JSONObject obj=new JSONObject();
					{
				int numColumns = row.getColumnDefinitions().size();
				System.out.println("The number of columns is " + numColumns);
				row.getColumnDefinitions().getType(0);
			     obj.put("product_id",row.getString("product_id"));
			     obj.put("product_name",row.getString("product_name"));
			        }
				json.put(obj);
			  }}
			catch(Exception e)
			{
				System.out.println("Error while fetching the dataset");
			}finally
			{
				if (session != null)
					session.close();
			}
			
			System.out.print(json);
			return (json);
	}
		
		
}
		
