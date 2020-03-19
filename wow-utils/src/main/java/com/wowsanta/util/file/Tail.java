package com.wowsanta.util.file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Tail {

	private String fileName;
	private int readCount;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	
	public String read() {
		String result = "";
		try {
			RandomAccessFile readWriteFileAccess = new RandomAccessFile(fileName, "r");
			int file_length = (int)readWriteFileAccess.length();
			
			int read_count = 0;
	        int line_count  = 0;
	        ByteBuffer revers_buffer = ByteBuffer.allocate(file_length);
	        
			while(file_length > read_count) {
				read_count++;
				long pos = file_length - read_count;
				readWriteFileAccess.seek(pos);
				
				byte readbyte = readWriteFileAccess.readByte();
				if(readbyte == '\n') {
					line_count++;
				}
				revers_buffer.put(readbyte);
				if(line_count > readCount) {
					break;
				}
			}
			readWriteFileAccess.close();
			
			byte[] read_buffer = new byte[read_count];
			byte[] revers_line = revers_buffer.array();
			for(int i=0; i < read_count; i++) {
				read_buffer[i] = revers_line[read_count - i];
			}
			result = new String(read_buffer);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}


}
