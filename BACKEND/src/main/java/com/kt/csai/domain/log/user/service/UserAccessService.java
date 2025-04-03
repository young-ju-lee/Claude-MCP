package com.kt.csai.domain.log.user.service;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kt.csai.domain.log.user.entity.Host;
import com.kt.csai.domain.log.user.entity.Local;
import com.kt.csai.domain.log.user.entity.UserAccess;
import com.kt.csai.domain.log.user.model.LogType;
import com.kt.csai.domain.log.user.model.OperationType;
import com.kt.csai.domain.log.user.model.SecurityType;
import com.kt.csai.domain.log.user.payload.UserAccessLog;
import com.kt.csai.domain.log.user.repository.USerAccessRepository;
import com.kt.csai.global.util.HostUtil;
import com.kt.csai.global.util.IpAddressUtil;
import com.kt.csai.global.util.StringUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccessService {
	private final USerAccessRepository accessRepository;
	private final IpAddressUtil ipAddressUtil;
	
	private final ModelMapper modelMapper;
	
	public UserAccessLog addLog(HttpServletRequest request, OperationType operationType, String userId) {
		UserAccess userAccess = UserAccess.builder()
				.host(
						Host.builder()
						.hostIp(HostUtil.getHostIp())
						.hostName(HostUtil.getHostName())
						.build()
						)
				.local(
						Local.builder()
						.userId(userId)
						.userIp(ipAddressUtil.getIpAddress(request))
						.build()
						)
				.service("OG010214")
				.operation(operationType.getType())
				.transactionId(StringUtil.generateTransactionId(HostUtil.getHostName()))
				.logType(LogType.IN_REQ.getType())
				.securityType(SecurityType.ACCESS.getType())
				.build();
		
		UserAccess entity = accessRepository.save(userAccess);
		return modelMapper.map(entity, UserAccessLog.class);
	}
}
