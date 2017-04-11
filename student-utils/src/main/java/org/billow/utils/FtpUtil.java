package org.billow.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FtpUtil {

	private static final Logger logger = Logger.getLogger(FtpUtil.class);

	protected FTPClient ftpClient;
	public static final int BINARY_FILE_TYPE = FTP.BINARY_FILE_TYPE;
	public static final int ASCII_FILE_TYPE = FTP.ASCII_FILE_TYPE;

	private String serverOS;

	public void localPasv() {
		ftpClient.enterLocalPassiveMode();
	}

	public void remotePasv() {
		try {
			ftpClient.enterRemotePassiveMode();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void noop() {
		try {
			ftpClient.noop();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean rename(String from, String to) {
		try {
			return ftpClient.rename(from, to);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getStatus(String pathname) {
		try {
			return ftpClient.getStatus(pathname);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 设置连接超时时间
	 * 
	 * @param millisecond
	 *            毫秒数
	 */
	public void setTimeout(int millisecond) {
		try {
			ftpClient.setSoTimeout(millisecond);
		} catch (SocketException e) {
			throw new RuntimeException(e);
		}
	}

	public boolean isConnected() {
		return ftpClient.isConnected();
	}

	/**
	 * 通过FtpConfig提供的参数初始化ftp连接
	 * 
	 * @param ftpConfig
	 * @exception RuntimeException
	 *                当FtpConfig 中server,username,serverOS 为null时，
	 *                指定的服务器地址和端口不能建立ftp连接时， username location指定的地址不能访问时。
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	// public void connect(FtpConfig ftpConfig) {
	// String server = ftpConfig.getServer();
	// int port = ftpConfig.getPort();
	// String user = ftpConfig.getUsername();
	// String password = ftpConfig.getPassword();
	// String location = ftpConfig.getLocation();
	// String serverOS = ftpConfig.getServerOS();
	// try {
	// connect(server, port, user, password, location,serverOS);
	// } catch (Exception e) {
	// if(e instanceof RuntimeException){
	// throw (RuntimeException)e;
	// }
	// throw new RuntimeException(e);
	// }
	// }
	/**
	 * 通过下列参数初始化ftp连接
	 * 
	 * @param server
	 * @param port
	 * @param user
	 * @param password
	 * @param path
	 * @param serverOS
	 * @exception RuntimeException
	 *                当server,username,serverOS 为null时， 指定的服务器地址和端口不能建立ftp连接时，
	 *                用户名或密码错误时， location指定的地址不能访问时。
	 */
	public void connect(String server, int port, String user, String password, String path, String serverOS) {
		if (server == null || user == null || serverOS == null) {
			throw new RuntimeException("Parameter server,user,serverOS can not be null");
		}
		this.serverOS = serverOS;
		ftpClient = new FTPClient();
		try {
			ftpClient.connect(server, port);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException(e);
		}
		logger.info("Connected to " + server + " : " + ftpClient.getReplyCode());
		boolean flag = false;
		try {
			flag = ftpClient.login(user, password);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (!flag) {
			throw new RuntimeException("Invalid username or password!");
		}
		// Path is the sub-path of the FTP path
		if (path != null && !path.equals("")) {
			try {
				flag = ftpClient.changeWorkingDirectory(path);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			if (!flag) {
				throw new RuntimeException("Can not change the working directory '" + path + "', the directory does not exist or can not access!");
			}
		}
	}

	/**
	 * 设置上传文件格式 BINARY_FILE_TYPE,ASCII_FILE_TYPE
	 * 
	 * @param fileType
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public void setFileType(int fileType) {
		try {
			ftpClient.setFileType(fileType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 关闭ftp连接
	 * 
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public void close() {
		if (ftpClient.isConnected()) {
			try {
				ftpClient.disconnect();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 将工作目录改到path指定的目录 path可以是绝对路径,也可以是相对路径(相对于当前工作目录) 可以识别：. , .. 这两个特殊的目录
	 * 
	 * @param path
	 * @return boolean
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean changeDirectory(String path) {
		boolean flag = false;
		try {
			flag = ftpClient.changeWorkingDirectory(path);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException(e);
		}
		if (!flag) {
			throw new RuntimeException("Can not change the working directory '" + path + "', the directory does not exist or can not access!");
		}
		return flag;
	}

	/**
	 * 到上一级目录，即工作目录改成上一级目录，如果当前目录已经是最上级目录，
	 * 
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean toTheParentDirectory() {
		try {
			return ftpClient.changeToParentDirectory();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 在当前工作目录下创建 path 指定的目录
	 * 
	 * @param path
	 * @return boolean
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean createDirectory(String path) {
		String separator = null;
		String regex = "/";
		if (path.indexOf("/") >= 0) {
			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}
			separator = "/";
			regex = "/";
		} else if (path.indexOf("\\") >= 0) {
			if (path.endsWith("\\")) {
				path = path.substring(0, path.length() - 1);
			}
			separator = "\\";
			regex = "\\\\";
		} else {

		}
		boolean flag = false;
		String[] pathItem = path.split(regex);
		String directory = "";
		for (int i = 0; i < pathItem.length; i++) {
			if (!directory.equals("")) {
				directory += separator;
			}
			directory += pathItem[i];
			if (!this.exist(directory)) {// 不存在
				try {
					flag = ftpClient.makeDirectory(directory);
					if (!flag) {// 建立出错
						break;
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return flag;

	}

	/**
	 * 删除 path指定的目录，该目录必须为空目录才能被成功删除
	 * 
	 * @param path
	 * @return boolean
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean removeDirectory(String path) {
		try {
			return ftpClient.removeDirectory(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除path指定的目录下的文件夹，当isAll为true时，删除非空和空目录。当isAll为false时只删除空目录
	 * 
	 * @param path
	 * @param isAll
	 * @return boolean
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean removeDirectory(String path, boolean isAll) {
		if (!isAll) {
			return removeDirectory(path);
		}
		FTPFile[] ftpFileArr;
		try {
			ftpFileArr = ftpClient.listFiles(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (ftpFileArr == null || ftpFileArr.length == 0) {
			return removeDirectory(path);
		}
		//
		FTPFile ftpFile = null;
		for (int i = 0; i < ftpFileArr.length; i++) {
			// for (FTPFile ftpFile : ftpFileArr) {
			ftpFile = ftpFileArr[i];
			String name = ftpFile.getName();
			if (ftpFile.isDirectory()) {
				removeDirectory(path + "/" + name, true);
			} else if (ftpFile.isFile()) {
				deleteFile(path + "/" + name);
			} else if (ftpFile.isSymbolicLink()) {

			} else if (ftpFile.isUnknown()) {

			}
		}
		try {
			return ftpClient.removeDirectory(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// Check the path is exist; exist return true, else false.
	/**
	 * 检查当前目录是否存在名为 path 的目录或文件
	 * 
	 * @param path
	 * @return boolean
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean exist(String path) {
		// boolean flag = false;
		// FTPFile[] ftpFileArr;
		// try {
		// ftpFileArr = ftpClient.listFiles("");//列出当前工作目录下的所有文件和文件夹
		// } catch (IOException e) {
		// throw new RuntimeException(e);
		// }
		// FTPFile ftpFile = null;
		// for (int i = 0; i < ftpFileArr.length; i++) {
		// // for (FTPFile ftpFile : ftpFileArr) {
		// ftpFile = ftpFileArr[i];
		// if (ftpFile.isDirectory()
		// && ftpFile.getName().equalsIgnoreCase(path)) {
		// flag = true;
		// break;
		// }
		// }
		// return flag;
		boolean flag = false;
		if (path == null || path.equals("")) {
			return flag;
		}
		// 找到指定目录的父目录
		String parentDir = null;
		String lastPathItem = null;
		if (path.indexOf("/") >= 0) {
			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}
			String[] paths = path.split("/");
			lastPathItem = paths[paths.length - 1];
			int index = path.lastIndexOf(lastPathItem);
			parentDir = path.substring(0, index);
		} else if (path.indexOf("\\") >= 0) {
			if (path.endsWith("\\")) {
				path = path.substring(0, path.length() - 1);
			}
			String[] paths = path.split("\\");
			lastPathItem = paths[paths.length - 1];
			int index = path.lastIndexOf(lastPathItem);
			parentDir = path.substring(0, index);
		} else {
			lastPathItem = path;
		}
		// String[] ftpFileNames;
		FTPFile[] ftpFiles;
		try {
			// ftpFileNames =
			// ftpClient.listNames(parentDir);//列出指定目录父目录的所有文件和文件夹名称
			ftpFiles = ftpClient.listFiles(parentDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (int i = 0; i < ftpFiles.length; i++) {
			String ftpFileName = ftpFiles[i].getName();
			if (serverOS.equalsIgnoreCase("Windows")) {// windows系统忽略目录大小写
				if (ftpFileName.equalsIgnoreCase(lastPathItem)) {
					flag = true;
					break;
				}
			} else if (serverOS.equalsIgnoreCase("Linux")) {// 区分 目录大小写
				if (ftpFileName.equals(lastPathItem)) {
					flag = true;
					break;
				}
			} else {
				if (ftpFileName.equals(lastPathItem)) { // 默认区分目录大小写
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	// =======================================================================
	// == About file =====
	// Download and Upload file using
	// ftpUtil.setFileType(FtpUtil.BINARY_FILE_TYPE) better!
	// =======================================================================

	// #1. list & delete operation
	// Not contains directory
	/**
	 * 如果path是相对路径，并且是目录则列出当前工作目录/path/下的所有文件的名称。
	 * 如果path是相对路径，并且是文件(最后一段的文件名部分可以使用通配符)列出符合条件的文件名称。
	 * 如果path是绝对路径，并且是目录，列出path下所有文件的名称。
	 * 如果path是绝对路径，并且是文件(最后一段的文件名部分可以使用通配符)列出符合条件的文件名称。
	 * 如果path=null，列出当前工作目录下的所有文件名称。
	 * 
	 * @param path
	 *            相对路径或绝对路径
	 * @param path
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public List<String> listFile(String path) {
		// listFiles return contains directory and file, it's FTPFile instance
		// listNames() contains directory, so using following to filer
		// directory.
		// String[] fileNameArr = ftpClient.listNames(path);
		FTPFile[] ftpFiles;
		try {
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<String> retList = new ArrayList<>();
		if (ftpFiles == null || ftpFiles.length == 0) {
			return retList;
		}
		FTPFile ftpFile = null;
		for (int i = 0; i < ftpFiles.length; i++) {
			// for (FTPFile ftpFile : ftpFileArr) {
			ftpFile = ftpFiles[i];
			if (ftpFile.isFile()) {
				retList.add(ftpFile.getName());
			}
		}
		return retList;
	}

	/**
	 * 如果path是相对路径，并且是目录则列出当前工作目录/path/下的所有文件和目录的名称。
	 * 如果path是相对路径，并且是文件(最后一段的文件名部分可以使用通配符)列出符合条件的文件名称。
	 * 如果path是绝对路径，并且是目录，列出path下所有文件和目录的名称。
	 * 如果path是绝对路径，并且是文件(最后一段的文件名部分可以使用通配符)列出符合条件的文件名称。
	 * 如果path=null，列出当前工作目录下的所有文件和目录的名称。 该方法不会列出 . .. 这两个特殊的目录
	 * 
	 * @param path
	 *            相对路径或绝对路径
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public List<String> listChild(String path) {
		// listFiles return contains directory and file, it's FTPFile instance
		// listNames() contains directory, so using following to filer
		// directory.
		// String[] fileNameArr = ftpClient.listNames(path);
		FTPFile[] ftpFiles;
		try {
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<String> retList = new ArrayList<>();
		if (ftpFiles == null || ftpFiles.length == 0) {
			return retList;
		}
		FTPFile ftpFile = null;
		for (int i = 0; i < ftpFiles.length; i++) {
			ftpFile = ftpFiles[i];
			retList.add(ftpFile.getName());
		}
		return retList;
	}

	/**
	 * 如果path是相对路径，并且是目录则列出当前工作目录/path/下的所有目录的名称。如果最后一段的目录名中有通配符 则按通配符格式列出目录名称。
	 * 如果path是绝对路径，并且是目录，列出path下所有文件和目录的名称。如果最后一段的目录名中有通配符 则按通配符格式列出目录名称。
	 * 如果path=null，列出当前工作目录下的所有目录名称。 该方法不会列出 . .. 这两个特殊的目录
	 * 
	 * @param path
	 *            相对路径或绝对路径
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public List<String> listDirectory(String path) {
		// listFiles return contains directory and file, it's FTPFile instance
		// listNames() contains directory, so using following to filer
		// directory.
		// String[] fileNameArr = ftpClient.listNames(path);
		FTPFile[] ftpFiles;
		try {
			ftpFiles = ftpClient.listFiles(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		List<String> retList = new ArrayList<>();
		if (ftpFiles == null || ftpFiles.length == 0) {
			return retList;
		}
		FTPFile ftpFile = null;
		for (int i = 0; i < ftpFiles.length; i++) {
			ftpFile = ftpFiles[i];
			if (ftpFile.isDirectory()) {
				retList.add(ftpFile.getName());
			}
		}
		return retList;
	}

	/**
	 * 在当前工作目录下删除path指定的文件
	 * 
	 * @param pathName
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean deleteFile(String path) {
		try {
			return ftpClient.deleteFile(path);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// #2. upload to ftp server
	// InputStream <------> byte[] simple and See API
	/**
	 * 上传文件到当前工作目录，并且文件名为newName。如果newName包含目录，则上传到指定目录下。
	 * 
	 * @param fileName
	 *            本地文件路径和文件名
	 * @param newName
	 *            服务器端文件路径和文件名(以当前工作目录为根目录的相对目录结构)
	 * @return boolean 当newName指定的路径的目录不存返回false。
	 * @exception RuntimeException
	 *                当文件无法访问时 在网络中断或服务器当机时
	 */
	public boolean uploadFile(String fileName, String newName) {
		boolean flag = false;
		InputStream iStream = null;
		try {
			iStream = new FileInputStream(fileName);
			flag = uploadFile(iStream, newName);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException(e);
		} finally {
			if (iStream != null) {
				try {
					iStream.close();
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		return flag;
	}

	/**
	 * 上传文件到当前工作目录。并且新文件名等于本地文件名。如果本地文件名是一个文件路径，如果服务器识别为路径
	 * 则将文件上传到指定路径，否则，整个文件路径作为上传服务器的文件的名字，上传到当前工作目录。
	 * 
	 * @param fileName
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean uploadFile(String fileName) {
		return uploadFile(fileName, fileName);
	}

	/**
	 * 上传文件的输入流到当前工作目录，并且文件名为newName。如果newName包含目录，则上传到指定目录下。
	 * 
	 * @param iStream
	 * @param newName
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean uploadFile(InputStream iStream, String newName) {
		boolean flag = false;
		try {
			// can execute [OutputStream storeFileStream(String remote)]
			// Above method return's value is the local file stream.
			flag = ftpClient.storeFile(newName, iStream);
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			throw new RuntimeException(e);
		}
		return flag;
	}

	// #3. Down load
	/**
	 * 将remoteFileName指定的文件 下载到localFileName指定的路径下
	 * 
	 * @param remoteFileName
	 *            以当前工作目录为根的相对路径或者绝对路径
	 * @param localFileName
	 *            本地地址，绝对路径
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public boolean download(String remoteFileName, String localFileName) {
		boolean flag = false;
		File outfile = new File(localFileName);
		OutputStream oStream = null;
		try {
			oStream = new FileOutputStream(outfile);
			flag = ftpClient.retrieveFile(remoteFileName, oStream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				oStream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return flag;
	}

	/**
	 * 将remoteFileName指定的文件下载到一个输入流中。
	 * 
	 * @param sourceFileName
	 *            以当前工作目录为根的相对路径或者绝对路径
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public InputStream downFile(String sourceFileName) {
		try {
			return ftpClient.retrieveFileStream(sourceFileName);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 返回当前的工作目录名，是一个绝对路径。
	 * 
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public String getCurrentWorkDirectory() {
		try {
			return ftpClient.printWorkingDirectory();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获得path指定的路径的类型 1-文件，2-目录，-1-不存在这样的路径
	 * 
	 * @param path
	 * @return
	 * @exception RuntimeException
	 *                一般在网络中断或服务器当机时才会出现
	 */
	public int getPathType(String path) {
		int flag = -1;
		if (path == null || path.equals("")) {
			return 2;// 当前工作目录
		}
		// 找到指定目录的父目录
		String parentDir = null;
		String lastPathItem = null;
		if (path.indexOf("/") >= 0) {
			if (path.endsWith("/")) {
				path = path.substring(0, path.length() - 1);
			}
			String[] paths = path.split("/");
			lastPathItem = paths[paths.length - 1];
			int index = path.lastIndexOf(lastPathItem);
			parentDir = path.substring(0, index);
		} else if (path.indexOf("\\") >= 0) {
			if (path.endsWith("\\")) {
				path = path.substring(0, path.length() - 1);
			}
			String[] paths = path.split("\\");
			lastPathItem = paths[paths.length - 1];
			int index = path.lastIndexOf(lastPathItem);
			parentDir = path.substring(0, index);
		} else {
			lastPathItem = path;
		}
		// String[] ftpFileNames;
		FTPFile[] ftpFiles;
		try {
			// ftpFileNames =
			// ftpClient.listNames(parentDir);//列出指定目录父目录的所有文件和文件夹名称
			ftpFiles = ftpClient.listFiles(parentDir);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		for (int i = 0; i < ftpFiles.length; i++) {
			String ftpFileName = ftpFiles[i].getName();
			int temp = 0;
			if (serverOS.equalsIgnoreCase("Windows")) {// windows系统忽略目录大小写
				if (ftpFileName.equalsIgnoreCase(lastPathItem)) {
					temp = 1;
				}
			} else if (serverOS.equalsIgnoreCase("Linux")) {// 区分 目录大小写
				if (ftpFileName.equals(lastPathItem)) {
					temp = 1;
				}
			} else {
				if (ftpFileName.equals(lastPathItem)) { // 默认区分目录大小写
					temp = 1;
				}
			}
			if (temp == 1) {
				if (ftpFiles[i].isFile()) {
					flag = 1;
				} else if (ftpFiles[i].isDirectory()) {
					flag = 2;
				}
			}
		}
		return flag;
	}

}
