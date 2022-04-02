package com.alibaba.alink.params.dataproc;

import org.apache.flink.ml.api.misc.param.ParamInfo;
import org.apache.flink.ml.api.misc.param.ParamInfoFactory;
import org.apache.flink.ml.api.misc.param.WithParams;

import com.alibaba.alink.common.annotation.DescCn;
import com.alibaba.alink.common.annotation.NameCn;

public interface StratifiedSampleParams<T> extends WithParams <T> {

	@NameCn("分层列")
	@DescCn("分层列")
	ParamInfo <String> STRATA_COL = ParamInfoFactory
		.createParamInfo("strataCol", String.class)
		.setDescription("strata col name.")
		.setAlias(new String[] {"strataColName"})
		.setRequired()
		.build();

	@NameCn("采用比率")
	@DescCn("采用比率")
	ParamInfo <Double> STRATA_RATIO = ParamInfoFactory
		.createParamInfo("strataRatio", Double.class)
		.setDescription("strata ratio.")
		.setHasDefaultValue(-1.0)
		.build();

	@NameCn("采用比率")
	@DescCn("采用比率, eg, a:0.1,b:0.3")
	ParamInfo <String> STRATA_RATIOS = ParamInfoFactory
		.createParamInfo("strataRatios", String.class)
		.setDescription("strata ratios. a:0.1,b:0.3")
		.setRequired()
		.build();

	default String getStrataCol() {
		return get(STRATA_COL);
	}

	default T setStrataCol(String value) {
		return set(STRATA_COL, value);
	}

	default Double getStrataRatio() {
		return get(STRATA_RATIO);
	}

	default T setStrataRatio(double value) {
		return set(STRATA_RATIO, value);
	}

	default String getStrataRatios() {
		return get(STRATA_RATIOS);
	}

	default T setStrataRatios(String value) {
		return set(STRATA_RATIOS, value);
	}

}
