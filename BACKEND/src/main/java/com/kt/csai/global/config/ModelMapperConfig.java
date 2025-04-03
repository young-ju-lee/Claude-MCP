package com.kt.csai.global.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kt.csai.domain.alarmtalk.entity.AlarmTalkSend;
import com.kt.csai.domain.alarmtalk.payload.alarmtalk.AlarmTalkRequest;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true).setMatchingStrategy(MatchingStrategies.STRICT);

		addMappings(modelMapper);
		return modelMapper;
	}

	private void addMappings(ModelMapper modelMapper) {
		addMappingForAlarmTalk(modelMapper);
	}

	private void addMappingForAlarmTalk(ModelMapper modelMapper) {
		modelMapper.addMappings(new PropertyMap<AlarmTalkRequest, AlarmTalkSend>() {
			@Override
			protected void configure() {
				map().setTroubleInfo(source.getTrouble());
				
			}
		});

		modelMapper.addMappings(new PropertyMap<AlarmTalkSend, AlarmTalkRequest>() {
			@Override
			protected void configure() {
				map().getTrouble().setTtId(source.getAlarmTalkId().getTtId());
				map().getTrouble().setSvcMainClsCode(source.getSvcMainClsCode());
			}
		});
	}
}
