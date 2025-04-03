package com.kt.csai.global.util;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kt.csai.global.common.exception.InternalServerException;
import com.kt.csai.global.common.exception.model.ErrorCode;
import com.kt.csai.global.socket.payload.SocketMessageDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

@Component
public class CsvUtil {

	@Value("${FILE.CHAT}")
	private String basePath;

	public List<String[]> readChat(String fileName) {
		try (CSVReader reader = new CSVReader(new FileReader(basePath.concat(fileName)))) {
			return reader.readAll();
		} catch (IOException | CsvException e) {
			throw new InternalServerException(ErrorCode.INVALID_READ_CHAT_FILE);
		}
	}

	public void writeChat(SocketMessageDto socketMessage) {
		String senderType = socketMessage.getSenderType();
		String contentType = socketMessage.getContentType();
		String content = "TX".equals(contentType) ? socketMessage.getContent()
				: "CT".equals(senderType) ? "고객이 보낸 이미지 및 동영상은 확인 불가능합니다."
						: "IM-BASE64".equals(contentType) ? "상담사가 보낸 임의 이미지 및 동영상은 확인 불가능합니다."
								: socketMessage.getFileName();
		String chatTime = socketMessage.getChatTime();
		String consultId = socketMessage.getConsultId();

		contentType = "CT".equals(senderType) || "IM-BASE64".equals(contentType) ? "TX" : contentType;

		String fileName = consultId.concat(".csv");
		try (CSVWriter writer = new CSVWriter(new FileWriter(basePath.concat(fileName), true))) {
			writer.writeNext(new String[] { senderType, contentType, content, chatTime });
		} catch (IOException e) {
			throw new InternalServerException(ErrorCode.INVALID_WRITE_CHAT_FILE);
		}
	}
}
