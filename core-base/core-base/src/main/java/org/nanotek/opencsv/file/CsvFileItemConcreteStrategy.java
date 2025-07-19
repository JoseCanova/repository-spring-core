package org.nanotek.opencsv.file;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream; // Import InputStream
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets; // Use StandardCharsets for cleaner UTF-8
import java.util.Map;

import org.nanotek.AnyBase;
import org.nanotek.Base;
import org.nanotek.BaseBean;
import org.nanotek.BaseException;
import org.nanotek.beans.IntrospectionException;
import org.nanotek.beans.PropertyDescriptor;
import org.nanotek.collections.BaseMap;
import org.nanotek.opencsv.MapColumnStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.MappingStrategy;

//@Component // Note: This class is not a @Component, it's used by CsvFileConfigurations
public class CsvFileItemConcreteStrategy
<T extends BaseMap<S,P,M> ,
S  extends AnyBase<S,String> ,
P   extends AnyBase<P,Integer> ,
M extends BaseBean<?,?>>
extends CsvFileItem<S,P,M>
implements MappingStrategy<M>, InitializingBean , Closeable {

	private static Logger log = LoggerFactory.getLogger(CsvFileItemConcreteStrategy.class); // Corrected logger class

	protected T baseMap;

	protected MapColumnStrategy<M> mapColumnStrategy;

	protected BufferedReader reader;

	public CsvFileItemConcreteStrategy() {
		super();
	}

	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		mapColumnStrategy = new MapColumnStrategy<M>(immutable);
		// Prepare the file reader initially
		prepareFileReader();
		mapColumnStrategy.configureMapStrategy(BaseMap.class.cast(baseMap));
	}


	public MapColumnStrategy<M> getMapColumnStrategy() {
		return mapColumnStrategy;
	}

	public void setMapColumnStrategy(MapColumnStrategy<M> mapColumnStrategy) {
		this.mapColumnStrategy = mapColumnStrategy;
	}


	@Override
	public void captureHeader(CSVReader reader) throws IOException {
		// Implementation for header capture, if needed, usually reads first line
	}

	public BaseMap<S, P, M> getBaseMap() {
		return baseMap;
	}

	public void setBaseMap(T baseMap) {
		this.baseMap = baseMap;
	}

	/**
	 * Closes the current reader and attempts to reopen the file,
	 * creating a new BufferedReader instance.
	 * @return A new BufferedReader instance positioned at the beginning of the file.
	 * @throws BaseException if an error occurs during closing or reopening.
	 */
	public BufferedReader reopen() {
		try {
			if (reader != null) {
				reader.close();
			}
			return prepareFileReader();
		}catch (Exception ex) {
			throw new BaseException("Failed to reopen CSV file reader.", ex);
		}
	}

	/**
	 * Prepares and returns a BufferedReader for the CSV file.
	 * Handles both 'classpath:' resources and direct file system paths.
	 *
	 * @return A new BufferedReader for the file.
	 * @throws BaseException if the file cannot be found or opened.
	 */
	protected BufferedReader prepareFileReader(){
		try {
			String fullPath = getFileLocation() + System.getProperty("file.separator") + getFileName().toString();

			// Normalize path separators (especially important for classpath resources on Windows)
			fullPath = fullPath.replace("\\", "/");

			InputStream is;

			if (fullPath.startsWith("classpath:/")) { // Already starts with classpath:/
				String resourcePath = fullPath.substring("classpath:".length()); // Remove "classpath:"
				log.debug("Attempting to load classpath resource: " + resourcePath);
				is = getClass().getResourceAsStream(resourcePath);
				if (is == null) {
					// Fallback for resources in root of classpath (no leading slash needed for getResourceAsStream for root)
					// Or if getResourceAsStream needs to be called from ClassLoader
					resourcePath = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
					is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
				}
				if (is == null) {
					throw new IOException("Classpath resource not found: " + fullPath);
				}
			} else if (fullPath.startsWith("classpath:")) { // Starts with classpath: but no leading slash after it
				String resourcePath = fullPath.substring("classpath:".length());
				if (!resourcePath.startsWith("/")) {
					resourcePath = "/" + resourcePath; // Ensure leading slash for getResourceAsStream
				}
				log.debug("Attempting to load classpath resource: " + resourcePath);
				is = getClass().getResourceAsStream(resourcePath);
				if (is == null) {
					resourcePath = resourcePath.startsWith("/") ? resourcePath.substring(1) : resourcePath;
					is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
				}
				if (is == null) {
					throw new IOException("Classpath resource not found: " + fullPath);
				}
			}
			else {
				// Assume it's a file system path
				log.debug("Attempting to load filesystem file: " + fullPath);
				is = new FileInputStream(new File(fullPath));
			}

			InputStreamReader ir = new InputStreamReader(is, StandardCharsets.UTF_8);
			reader = new BufferedReader(ir, 8192); // Buffer size 8KB
		} catch (Exception e) {
			throw new BaseException("CSV File not found or failed to open. Location: '" + getFileLocation() + "' Filename: '" +  getFileName() + "'. Full path attempted: '" + (getFileLocation() + System.getProperty("file.separator") + getFileName().toString()).replace("\\", "/") + "'", e);
		}
		return reader;
	}

	@Override
	public void close() throws IOException {
		if (reader != null) {
			reader.close();
		}
	}

	public String[] getColumnMapping() {
		return mapColumnStrategy.getColumnMapping();
	}

	public PropertyDescriptor findDescriptor(int col) {
		return mapColumnStrategy.findDescriptor(col);
	}

	public PropertyDescriptor findDescriptor(String name) throws IntrospectionException {
		return mapColumnStrategy.findDescriptor(name);
	}

	public boolean matches(String name, PropertyDescriptor desc) {
		return mapColumnStrategy.matches(name, desc);
	}

	public Map<String, PropertyDescriptor> loadDescriptorMap(Class<M> cls) throws IntrospectionException {
		return mapColumnStrategy.loadDescriptorMap(cls);
	}

	public PropertyDescriptor[] loadDescriptors(Class<M> cls) throws IntrospectionException {
		return mapColumnStrategy.loadDescriptors(cls);
	}

	@Override
	public M createBean() {
		return (M) Base.newInstance(immutable).get();
	}

	/**
	 * Returns the current BufferedReader. It will implicitly call reopen()
	 * to ensure a fresh reader is returned with each call.
	 * This is suitable if each parsing operation needs to start from the beginning.
	 * If a single, continuous read is needed, the caller should manage the reader.
	 */
	public BufferedReader getCSVReader() {
		return reopen(); // This method now explicitly handles closing the old reader and opening a new one.
	}
}