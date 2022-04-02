package com.alibaba.alink.operator.stream.dataproc;

import org.apache.flink.ml.api.misc.param.Params;

import com.alibaba.alink.common.annotation.InputPorts;
import com.alibaba.alink.common.annotation.NameCn;
import com.alibaba.alink.common.annotation.ParamSelectColumnSpec;
import com.alibaba.alink.common.annotation.PortDesc;
import com.alibaba.alink.common.annotation.PortSpec;
import com.alibaba.alink.common.annotation.PortSpec.OpType;
import com.alibaba.alink.common.annotation.PortType;
import com.alibaba.alink.common.annotation.TypeCollections;
import com.alibaba.alink.operator.batch.BatchOperator;
import com.alibaba.alink.operator.batch.dataproc.MultiStringIndexerTrainBatchOp;
import com.alibaba.alink.operator.common.dataproc.MultiStringIndexerModelMapper;
import com.alibaba.alink.operator.stream.utils.ModelMapStreamOp;
import com.alibaba.alink.params.dataproc.MultiStringIndexerPredictParams;

/**
 * Map string to index based on the model generated by {@link MultiStringIndexerTrainBatchOp}.
 */
@InputPorts(values = {
	@PortSpec(value = PortType.MODEL, opType = OpType.BATCH, desc = PortDesc.PREDICT_INPUT_MODEL, suggestions = MultiStringIndexerTrainBatchOp.class),
	@PortSpec(value = PortType.DATA, desc = PortDesc.PREDICT_INPUT_DATA),
	@PortSpec(value = PortType.MODEL_STREAM, isOptional = true, desc = PortDesc.PREDICT_INPUT_MODEL_STREAM)
})
@ParamSelectColumnSpec(name = "selectedCols", allowedTypeCollections = TypeCollections.INT_LONG_STRING_TYPES)
@NameCn("MultiStringIndexer预测")
public final class MultiStringIndexerPredictStreamOp
	extends ModelMapStreamOp <MultiStringIndexerPredictStreamOp>
	implements MultiStringIndexerPredictParams <MultiStringIndexerPredictStreamOp> {

	private static final long serialVersionUID = 8426930716606704994L;

	public MultiStringIndexerPredictStreamOp(BatchOperator model) {
		this(model, new Params());
	}

	public MultiStringIndexerPredictStreamOp(BatchOperator model, Params params) {
		super(model, MultiStringIndexerModelMapper::new, params);
	}
}
