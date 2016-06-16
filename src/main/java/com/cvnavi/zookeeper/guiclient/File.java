/**  
 * @Title File.java
 * @Package com.cvnavi.zookeeper.guiclient
 * @Description TODO(用一句话描述该文件做什么)
 * @author 韩欣宇
 * @date: 2016年5月20日 下午4:41:52
 * @company 上海势航网络科技有限公司
 * @version V1.0  
 */
package com.cvnavi.zookeeper.guiclient;

import org.apache.zookeeper.data.Stat;

/**
 * @ClassName File
 * @Description TODO(这里用一句话描述这个类的作用)
 * @author 韩欣宇
 * @company 上海势航网络科技有限公司
 * @date 2016年5月20日 下午4:41:52
 */
public class File {

	public static String separator = "/";

	private String absolutePath;
	private String path;
	private String name;
	private boolean isDirectory;
	private Stat stat;
	private byte[] content;
	private File parentFile;

	public File(String absolutePath) {
		this.absolutePath = absolutePath;
		if (absolutePath.equals(separator)) {
			path = absolutePath;
			name = absolutePath;
		} else {
			String substring = absolutePath.substring(absolutePath.lastIndexOf(separator) + 1, absolutePath.length());
			path = substring;
			name = substring;
		}
	}

	/**
	 * @param targetFile
	 * @param name2
	 */
	public File(File targetFile, String name2) {
		// TODO Auto-generated constructor stub
	}

	public boolean exists() {
		return true;
	}

	public File[] listFiles() {
		return ZkTool.getChildren(absolutePath);
	}

	public boolean mkdirs() {
		return false;
	}

	public long lastModified() {
		return stat == null ? 0 : stat.getMtime();
	}

	public long length() {
		return content == null ? 0 : content.length;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAbsolutePath() {
		return absolutePath;
	}

	public void setAbsolutePath(String absolutePath) {
		this.absolutePath = absolutePath;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDirectory() {
		return isDirectory;
	}

	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}

	public Stat getStat() {
		return stat;
	}

	public void setStat(Stat stat) {
		this.stat = stat;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}

	public File getParentFile() {
		File p = null;
		if (absolutePath.length() > separator.length()) {
			int i = absolutePath.lastIndexOf(separator);
			if (i == 0) {
				p = ZkTool.getData(separator);
			} else {
				p = ZkTool.getData(absolutePath.substring(0, i));
			}
		}
		return p;
	}

}
