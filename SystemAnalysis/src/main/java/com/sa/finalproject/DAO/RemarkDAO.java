package com.sa.finalproject.DAO;

import java.util.ArrayList;

import com.sa.finalproject.entity.Remark;

public interface RemarkDAO {
	public void addRemark(long aBopSerial,Remark remark); //  新增備註
	public void updateRemark(long aBopSerial,Remark remark); //修改備註
	public void deleteRemark(long aBopSerial,Remark remark); //刪除備註
	public ArrayList<Remark> showRemark(long aBopSerial);  //顯示備註

}
