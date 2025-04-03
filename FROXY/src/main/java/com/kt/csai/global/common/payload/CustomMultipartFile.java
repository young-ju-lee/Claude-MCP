package com.kt.csai.global.common.payload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public class CustomMultipartFile implements MultipartFile {

	private byte[] file;
	private String fileName;

	public CustomMultipartFile() {

	}

	public CustomMultipartFile(byte[] bytes) {
		this.file = bytes;
	}

	public CustomMultipartFile(byte[] bytes, String fileName) {
		this.file = bytes;
		this.fileName = fileName;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getOriginalFilename() {
		return fileName;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		return file == null || file.length == 0;
	}

	@Override
	public long getSize() {
		return file.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return file == null ? null : file.clone();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(file);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		try (FileOutputStream fos = new FileOutputStream(dest)) {
			fos.write(file);
		}
	}

	public void transferTo(String dest) {
		try (FileOutputStream fos = new FileOutputStream(dest)) {
			try {
				fos.write(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
