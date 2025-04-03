package com.kt.csai.domain.guide.payload.response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class EazyGuideLine {
//	@JsonProperty("tt_id")
//	private String ttId;
//	@JsonProperty("scn")
//	private String scn;
	@JsonProperty("guide_lines")
	private List<GuideLine> guideLines;

	public EazyGuideLine() {
		guideLines = new ArrayList<>();
	}

	public EazyGuideLine(List<EazyGuideLineQueryResultInterface> eazyGuideLines) {
		Map<Integer, GuideLine> hashMap = new LinkedHashMap<>();

//		if (eazyGuideLines.size() > 0) {
//			this.ttId = eazyGuideLines.get(0).getTtId();
//			this.scn = eazyGuideLines.get(0).getScn();
//		}

		for (EazyGuideLineQueryResultInterface eazyGuideLine : eazyGuideLines) {
			Integer seqNo = eazyGuideLine.getSeqNo();
			String termlType = eazyGuideLine.getTermlType();
			if (!hashMap.containsKey(seqNo)) {
				hashMap.put(seqNo, new GuideLine(termlType));
			}

			GuideLine guideLine = hashMap.get(seqNo);
			guideLine.addGuide(eazyGuideLine);
		}

		guideLines = new ArrayList<>(hashMap.values());
	}
}
