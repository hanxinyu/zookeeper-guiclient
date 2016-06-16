/**  
 * @Title StringUtil.java
 * @Package com.cvnavi.zookeeper.guiclient
 * @Description TODO(用一句话描述该文件做什么)
 * @author 韩欣宇
 * @date: 2016年5月20日 下午3:48:22
 * @company 上海势航网络科技有限公司
 * @version V1.0  
 */
package com.cvnavi.zookeeper.guiclient;

/**
 * @ClassName StringUtil
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 韩欣宇
 * @company 上海势航网络科技有限公司
 * @date 2016年5月20日 下午3:48:22
 */
public class StringUtil {

	/**
	 * @Title strLeftBack
	 * @Description TODO(这里用一句话描述这个方法的作用)
	 * @param zpath
	 * @param string
	 * @return String
	 * @author 韩欣宇
	 * @date 2016年5月20日 下午3:48:27
	 */
	public static String strLeftBack(String zpath, String string) {
		return zpath.substring(0, zpath.lastIndexOf(string));
	}

}
