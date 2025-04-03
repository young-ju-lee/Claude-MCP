package com.kt.csai.domain.guide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kt.csai.domain.guide.entity.AiResultGuide;
import com.kt.csai.domain.guide.payload.response.EazyGuideLineQueryResultInterface;

public interface AiResultGuideRepository extends JpaRepository<AiResultGuide, Long> {

	@Query(value = ""
			+ "SELECT reslt_guide.tt_id AS ttId, reslt_guide.scn AS scn, reslt_guide.terml_type AS termlType, "
			+ "	guide_class.guide_code AS guideCode, guide_class.guide_title AS guideTitle, "
			+ "	guide_class.guide_desc AS guideDesc, guide_class.guide_desc2 AS guideDesc2, reslt_guide.seq_no AS seqNo "
			+ "FROM tb_ai_reslt_guide AS reslt_guide LEFT OUTER JOIN tb_guide_class AS guide_class "
			+ " ON guide_class.guide_code IN (guide1, guide2, guide3, guide4, guide5, guide6, guide7, guide8, guide9, guide10) "
			+ "WHERE reslt_guide.tt_id = :ttId "
			+ "ORDER BY seq_no, ARRAY_POSITION(ARRAY[guide1, guide2, guide3, guide4, guide5, guide6, guide7, guide8, guide9, guide10], guide_class.guide_code) "
			, nativeQuery = true
			)
	List<EazyGuideLineQueryResultInterface> findEasyGuideLineByTtId(@Param("ttId") String ttId);
}
