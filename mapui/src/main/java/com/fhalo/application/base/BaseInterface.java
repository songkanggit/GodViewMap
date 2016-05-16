package com.fhalo.application.base;

public interface BaseInterface {

	/**
	 * 初始化所有控件
	 * @description:
	 * @methodName: initView
	 * @return: void
	 * @throws:
	 */
	public void initView();
	/**
	 * 初始化其它非XML控件对象
	 * @description:
	 * @methodName: initData
	 * @return: void
	 * @throws:
	 */
	public void initData();
	/**
	 * 对数据及逻辑进行操作
	 * @description:
	 * @methodName: DataOper
	 * @return: void
	 * @throws:
	 */
	public void DataOper();
	
}
