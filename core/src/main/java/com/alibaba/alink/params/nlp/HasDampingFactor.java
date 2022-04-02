package com.alibaba.alink.params.nlp;

import org.apache.flink.ml.api.misc.param.ParamInfo;
import org.apache.flink.ml.api.misc.param.ParamInfoFactory;
import org.apache.flink.ml.api.misc.param.WithParams;

import com.alibaba.alink.common.annotation.DescCn;
import com.alibaba.alink.common.annotation.NameCn;
import com.alibaba.alink.operator.common.nlp.TextRankConst;

public interface HasDampingFactor<T> extends WithParams <T> {
	@NameCn("阻尼系数")
	@DescCn("阻尼系数")
	ParamInfo <Double> DAMPING_FACTOR = ParamInfoFactory
		.createParamInfo("dampingFactor", Double.class)
		.setDescription("damping factor")
		.setHasDefaultValue(TextRankConst.DAMPINGFACTOR)
		.build();

	default Double getDampingFactor() {
		return get(DAMPING_FACTOR);
	}

	default T setDampingFactor(Double value) {
		return set(DAMPING_FACTOR, value);
	}
}
