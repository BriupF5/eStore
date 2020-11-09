package com.briup.bean;

public class Grms implements Comparable<Grms>{
    private String usid;

    private String proid;

    private String num;

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid == null ? null : usid.trim();
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid == null ? null : proid.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

	@Override
	public int compareTo(Grms o) {
		if(this.num.equals(o.num)) {
			return 1;
		}
		
		return this.num.compareTo(o.num);
			
	}
}