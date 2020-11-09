package com.briup.big.step.step8;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

/**
 * 用于封装保存到数据库中的最后推荐数据
 * */
public class Grms implements WritableComparable<Grms>,DBWritable{

	@Override
	public String toString() {
		return "Grms [usid=" + usid + ", proid=" + proid + ", num=" + num + "]";
	}

	private Text usid = new Text();
	private Text proid = new Text();
	private Text num = new Text();
	
	public Grms() {}
	public Grms(Text usid,Text proid,Text num){
		this.usid = new Text(usid.getBytes());
		this.proid = new Text(proid.getBytes());
		this.num = new Text(num.getBytes());
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		usid.write(out);
		proid.write(out);
		num.write(out);
	}
	
	@Override
	public void readFields(DataInput in) throws IOException {
		usid.readFields(in);
		proid.readFields(in);
		num.readFields(in);
	}

	@Override
	public int compareTo(Grms o) {
		int compareTo = this.usid.compareTo(o.usid);
		if(compareTo==0){
			int compareTo2 = this.proid.compareTo(o.proid);
			if(compareTo2==0){
				return 0;
			}
			return compareTo2;
		}
		return compareTo;
	}

	@Override
	public void readFields(ResultSet rs) throws SQLException {
		rs.next();
		
		String us = rs.getString("usid");
		String pro = rs.getString("proid");
		String nu = rs.getString("num");
		
		usid.set(us);
		proid.set(pro);
		num.set(nu);
		
		
	}

	@Override
	public void write(PreparedStatement stat) throws SQLException {
		stat.setString(1, usid.toString());
		stat.setString(2, proid.toString());
		stat.setString(3, num.toString());
	}
}
