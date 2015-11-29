package com.rest.casssandra.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;



public class cassDAO {

	protected static Cluster cluster;
	protected static Session session;
			
	//Create Connection Instance to Cassandra Cluster for DB: MyRetail
	
	public static void Conn()
	{
		try{			
			// Connect to the cluster and keyspace "MyRetail"
			cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
			session = cluster.connect("MyRetail");
		}
		catch(Exception e){
			System.out.println("The Connection couldnt be established");
			session.close();
		System.out.println("Connection issue");
		}
	}
}
	
	