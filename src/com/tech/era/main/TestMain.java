package com.tech.era.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import com.tech.era.bean.EmployeeBean;
import com.tech.era.service.EmployeeServiceIMPL;
import com.tech.era.utility.CustomExecutorServiceUtility;
import com.tech.era.utility.ThreadUtility;


public class TestMain {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//instead of map i am using list just for example
		List<EmployeeServiceIMPL> list  = getListEmployeeService();
		
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(list.size());
		try {					
			
			for(EmployeeServiceIMPL service:list){
				/*CompletableFuture<Integer> value = */ThreadUtility.runTask(ex,service,getListEmpBeans());
			}
			//here we can proceed with other stuff it ll execute through main thread or servlet thread
			for(int i1=0;i1<100;i1++){
				System.out.println("insisde main:"+i1);
			}	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
		}
		//from this line whatever we write it ll execute after the executer shutdown
			
		
	}
	
	public static List<EmployeeServiceIMPL> getListEmployeeService(){
		List<EmployeeServiceIMPL> list  = new ArrayList<>();
		EmployeeServiceIMPL employeeServiceIMPL1= new EmployeeServiceIMPL();
		EmployeeServiceIMPL employeeServiceIMPL2= new EmployeeServiceIMPL();
		EmployeeServiceIMPL employeeServiceIMPL3= new EmployeeServiceIMPL();
		list.add(employeeServiceIMPL1);list.add(employeeServiceIMPL2);list.add(employeeServiceIMPL3);
		return list;
	}
	//
	public static List<EmployeeBean> getListEmpBeans(){
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
				new EmployeeBean(1002,"Justin"),
				new EmployeeBean(1003,"Mario"),
				new EmployeeBean(1004,"Tim"));
		return listEmpBeans;
	}
	
	
}
