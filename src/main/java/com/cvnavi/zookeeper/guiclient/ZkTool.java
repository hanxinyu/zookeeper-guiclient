/**  
 * @Title ZkTool.java
 * @Package com.cvnavi.zookeeper.guiclient
 * @Description TODO(用一句话描述该文件做什么)
 * @author 韩欣宇
 * @date: 2016年5月20日 下午3:47:21
 * @company 上海势航网络科技有限公司
 * @version V1.0  
 */
package com.cvnavi.zookeeper.guiclient;

import java.util.List;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class ZkTool {
	// 默认配置信息
	private final static String DEFAULT_HOST = "name1.cvnavi.com";
	private final static int DEFAULT_PORT = 2181;
	private final static int DEFAULT_TIMEOUT = 30000;

	// 当前配置信息
	private static String zkHost = DEFAULT_HOST;
	private static int zkPort = DEFAULT_PORT;
	private static int zkTimeout = DEFAULT_TIMEOUT;

	public static void main(String[] args) {
		String file = ("/");
		File[] files = getChildren(file);
		for (File file2 : files) {
			System.out.println(file2.isDirectory() + " " + file2.getAbsolutePath());
		}
	}

	private static ZooKeeper zooKeeper = null;

	public static File[] getChildren(String path) {
		File[] files = new File[0];
		try {
			openZk();
			// 取出子目录节点列表
			List<String> children = zooKeeper.getChildren(path, false);
			files = new File[children.size()];
			for (int i = 0; i < children.size(); i++) {
				File file = new File(path + (path.equals("/") ? "" : "/") + children.get(i));
				boolean isDirectory = isDirectory(file.getAbsolutePath());
				if (isDirectory) {
					File f = getData(file.getAbsolutePath());
					file.setDirectory(isDirectory);
					file.setStat(f.getStat());
				} else {
					file = getData(file.getAbsolutePath());
				}
				files[i] = file;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return files;
	}

	public static boolean isDirectory(String path) {
		try {
			openZk();
			// 取出子目录节点列表
			List<String> children = zooKeeper.getChildren(path, false);
			if (children.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static File getData(String path) {
		try {
			File file = new File(path);
			openZk();
			Stat stat = new Stat();
			// 取出子目录节点列表
			byte[] data = zooKeeper.getData(path, false, stat);
			file.setStat(stat);
			file.setContent(data);
			return file;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 设置连接信息 */
	public static void configConnection() {
		System.out.println("*****设置连接信息*****");
		do {
			try {
				// 创建一个与服务器的连接
				zooKeeper = new ZooKeeper(zkHost + ":" + zkPort, zkTimeout, new Watcher() {

					// 监控所有被触发的事件
					public void process(WatchedEvent event) {
						System.out.println("已经触发了[" + event.getType() + "]事件！");
					}
				});
			} catch (Exception e) {
				zooKeeper = null;
				e.printStackTrace();
			}
		} while (zooKeeper == null);
		System.out.println(">>>测试连接成功!");
	}

	private static ZooKeeper openZk() {
		if (zooKeeper == null) {
			// 创建一个与服务器的连接
			try {
				zooKeeper = new ZooKeeper(zkHost + ":" + zkPort, zkTimeout, new Watcher() {
					// 监控所有被触发的事件
					public void process(WatchedEvent event) {
						System.out.println("已经触发了[" + event.getType() + "]事件！");
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return zooKeeper;
	}

	public static void closeZk() {
		if (zooKeeper != null) {
			// 创建一个与服务器的连接
			try {
				zooKeeper.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}