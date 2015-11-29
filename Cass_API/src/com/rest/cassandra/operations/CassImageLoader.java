package com.rest.cassandra.operations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.rest.casssandra.dao.cassDAO;

public class CassImageLoader extends cassDAO {
	byte[] b;
	int length=0;
	String str=null;
	FileInputStream fis=null;
	
//load the image in the cassandra path
	public String imageLoader( String filename){
//convert the object to bytearray
		try {
            Conn();
			System.out.println("The filename is" + filename );
			
		    File f1=new File("/home/hdfs/Images/" + filename);
			
		    //check if the file exist 
		    if(f1.isFile())
		    fis=new FileInputStream(f1);
		    b= new byte[fis.available()+1];
		    length=b.length;
		    System.out.println("The length of the buffer is " +  length);
		    fis.read(b);
			
			}catch(IOException E)
			{
				System.out.println("The InputStream could not be read");
			}
			catch (NullPointerException n1)
			{
				System.out.println("The InputStream was received with null pointers");
				n1.printStackTrace();
			}finally
			{
				
			try {
				if (fis != null)
					fis.close();
			    }catch(Exception e)
			    {
			    	e.printStackTrace();
			    }
			}
			//box the inputstream to byte array 
		
			
		
		
		//convert the  byte array into a bytebuffer:

		Buffer buffer =ByteBuffer.wrap(b);
		PreparedStatement ps = session.prepare("insert into MyRetail.Messages( Rowkey ,image,imagelen) values(?,?,?)");
		BoundStatement boundStatement = new BoundStatement(ps);
		session.execute(boundStatement.bind(1, buffer,length));
		str="The object was loaded in the database";
		
		//
		session.close();
		return str;
	}
	/*
	public static void main(String[] args)
	{
		CassImageLoader test=new CassImageLoader();
		test.imageLoader("h1.jpg");
		
	}
	*/
	
}
