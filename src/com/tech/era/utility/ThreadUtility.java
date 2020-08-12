package com.tech.era.utility;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import com.tech.era.service.EmployeeServiceIMPL;

public class ThreadUtility {
	//pass the require argument as per your need , this is just example
	public static void runTask(ExecutorService ex,EmployeeServiceIMPL employeeServiceIMPL,List<com.tech.era.bean.EmployeeBean> listEmpBeans) throws InterruptedException, ExecutionException{
		CompletableFuture<Integer> resultEmployeeFuture = null;
		
			 resultEmployeeFuture =CompletableFuture.supplyAsync(
					()-> 
					{
						Integer res=0;
						try {
							//TODO: do your operation , this is just for example
							res=employeeServiceIMPL.createEmployee(listEmpBeans);
							
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
						return res;
					},ex);
		
		System.out.println("***"+Thread.currentThread().getName()+"***");
		//System.out.println(resultEmployeeFuture.get()); //get() made the job synchronous by waiting for result  so use it properly 
		//return resultEmployeeFuture;
	}
}
