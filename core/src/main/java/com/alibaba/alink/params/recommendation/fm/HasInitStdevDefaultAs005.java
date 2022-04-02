package com.alibaba.alink.params.recommendation.fm;

import org.apache.flink.ml.api.misc.param.ParamInfo;
import org.apache.flink.ml.api.misc.param.ParamInfoFactory;
import org.apache.flink.ml.api.misc.param.WithParams;

import com.alibaba.alink.common.annotation.DescCn;
import com.alibaba.alink.common.annotation.NameCn;

/**
 * initial stdev.
 */
public interface HasInitStdevDefaultAs005<T> extends WithParams <T> {
	@NameCn("初始化参数的标准差")
	@DescCn("初始化参数的标准差")
	ParamInfo <Double> INIT_STDEV = ParamInfoFactory
		.createParamInfo("initStdev", Double.class)
		.setDescription("init stdev")
		.setHasDefaultValue(0.05)
		.build();

	default Double getInitStdev() {
		return get(INIT_STDEV);
	}

	default T setInitStdev(Double value) {
		return set(INIT_STDEV, value);
	}
}
