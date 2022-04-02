package com.alibaba.alink.operator.batch.classification;

import org.apache.flink.ml.api.misc.param.Params;

import com.alibaba.alink.common.annotation.NameCn;

/**
 * Prediction with text classifier using Bert models.
 */
@NameCn("Bert文本分类预测")
public class BertTextClassifierPredictBatchOp extends TFTableModelClassifierPredictBatchOp <BertTextClassifierPredictBatchOp> {

	public BertTextClassifierPredictBatchOp() {
		this(new Params());
	}

	public BertTextClassifierPredictBatchOp(Params params) {
		super(params);
	}
}
