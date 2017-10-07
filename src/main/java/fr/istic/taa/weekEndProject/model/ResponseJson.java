package fr.istic.taa.weekEndProject.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseJson {

	List<? extends InterfaceEntity> data;

	public ResponseJson(List<? extends InterfaceEntity> l1) {
		super();
		this.data = l1;
	}
	
	

	@SuppressWarnings("unchecked")
	public ResponseJson(InterfaceEntity data) {
		super();
		this.data = new ArrayList<InterfaceEntity>();
		
	
		((List<InterfaceEntity>)this.data).add(data);
	}
	
	@SuppressWarnings("unchecked")
	public ResponseJson(Activity data) {
		super();
		this.data = new ArrayList<Activity>();
		
	
		((List<Activity>)this.data).add(data);
	}

	public List<? extends InterfaceEntity> getData() {
		return data;
	}

	public void setData(List<? extends InterfaceEntity> data) {
		this.data = data;
	}
	


}
