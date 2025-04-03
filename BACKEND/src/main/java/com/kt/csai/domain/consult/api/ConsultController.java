package com.kt.csai.domain.consult.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kt.csai.domain.consult.payload.dto.ConsultStatus;
import com.kt.csai.domain.consult.payload.dto.ConsultingDto;
import com.kt.csai.domain.consult.payload.dto.DoneConsult;
import com.kt.csai.domain.consult.payload.receive.ReceiveMessage;
import com.kt.csai.domain.consult.payload.receive.ResultResponse;
import com.kt.csai.domain.consult.payload.receive.UserMetadataRequest;
import com.kt.csai.domain.consult.service.ConsultService;
import com.kt.csai.global.socket.payload.SocketMessageDto;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/consult")
public class ConsultController {

	private final ConsultService consultService;

	@PostMapping("/reference")
	public ResponseEntity<?> receiveUserMetaData(@RequestBody UserMetadataRequest userMetadataRequest) {
		return new ResponseEntity<>(consultService.enteredCustomer(userMetadataRequest), HttpStatus.OK);
	}

	@PostMapping("/message")
	public ResponseEntity<?> receiveMessage(@RequestBody ReceiveMessage receiveMessage) {
		consultService.receiveMessage(receiveMessage);
		return new ResponseEntity<>("SEND MESSAGE", HttpStatus.OK);
	}

	@PostMapping("/send-test")
	public ResponseEntity<?> sendMessage(@RequestBody SocketMessageDto socketMessageDto) {
		return new ResponseEntity<>(consultService.sendMessage(socketMessageDto), HttpStatus.OK);
	}

	@PostMapping("/result")
	public ResponseEntity<?> receiveResult(@RequestBody ResultResponse resultResponse) {
		return new ResponseEntity<>(consultService.doReceiveProcess(resultResponse), HttpStatus.OK);
	}

	@PostMapping("/status")
	public ResponseEntity<?> changeStatus(@RequestBody ConsultStatus consultStatus) {
		return new ResponseEntity<>(consultService.changeConsultStatus(consultStatus), HttpStatus.OK);
	}

	@PostMapping("/done")
	public ResponseEntity<?> done(@RequestBody DoneConsult doneConsult) {
		return new ResponseEntity<>(consultService.doneConsult(doneConsult), HttpStatus.OK);
	}

	@GetMapping("/history/{telnum}")
	public ResponseEntity<?> loadConsultHistory(@PathVariable("telnum") String telNum) {
		return new ResponseEntity<>(consultService.findAllByTelNum(telNum), HttpStatus.OK);
	}

	@GetMapping("/history/{telnum}/{id}/chat")
	public ResponseEntity<?> loadConsultChatHistory(@PathVariable("telnum") String telNum,
			@PathVariable("id") String id) {
		return new ResponseEntity<>(consultService.readChatData(id), HttpStatus.OK);
	}

	@GetMapping("/consulting")
	public ResponseEntity<?> loadConsulting(ConsultingDto consultingDto) {
		return new ResponseEntity<>(consultService.findAllByCnvrstSttusNotAndCsttCmpno(consultingDto), HttpStatus.OK);
	}
}