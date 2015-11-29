/**
 * 
 */
package com.rest.cassandra.test;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import junit.framework.TestCase;

/**
 * @author hdfs
 *
 */
public class cassDAOTest extends TestCase {

	/**
	 * Test method for {@link com.rest.test.cassDAO#Conn()}.
	 */
	public void testConn() {
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		Session session = cluster.connect("MyRetail");
		assertEquals(session!=null,true);
		
		//fail("Not yet implemented");
	}

		/**
	 * Test method for {@link com.rest.test.cassDAO#getActiveSKU(int)}.
	 */
	public void testGetActiveSKU() {
		
		Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
		Session session = cluster.connect("MyRetail");
		int supply=0;
		int demand=0;
		int avail=0;
		
		ResultSet result1 = session.execute("SELECT Supply_Qty FROM FullFillmentCenterA where Product_Id=100");
		for (Row row : result1)
			supply+=row.getInt("Supply_Qty");
		ResultSet result2 = session.execute("SELECT Supply_Qty FROM FullFillmentCenterB where Product_Id=100");
		for (Row row : result2)
			supply+=row.getInt("Supply_Qty");
		//System.out.println();
		ResultSet result3 = session.execute("SELECT Order_Qty FROM Orders where prod_id=100");
			for(Row row:result3)
				demand+=row.getInt("Order_Qty");
			avail=(supply-demand);
			assertEquals(avail==18, true);
	}

}
