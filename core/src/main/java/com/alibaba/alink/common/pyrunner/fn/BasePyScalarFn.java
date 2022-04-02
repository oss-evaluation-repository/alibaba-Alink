package com.alibaba.alink.common.pyrunner.fn;

import org.apache.flink.table.functions.FunctionContext;
import org.apache.flink.table.functions.ScalarFunction;

import com.alibaba.alink.common.AlinkGlobalConfiguration;
import com.alibaba.alink.common.utils.Functional.SerializableBiFunction;

import java.io.Serializable;
import java.util.Collections;

import static com.alibaba.alink.common.pyrunner.bridge.BasePythonBridge.PY_TURN_ON_LOGGING_KEY;

/**
 * Base class for all Python scalar functions.
 *
 * @param <PYOUT>  Output type of Python side.
 * @param <HANDLE> Python stub type.
 */
public abstract class BasePyScalarFn<PYOUT, HANDLE extends PyScalarFnHandle <PYOUT>> extends ScalarFunction
	implements Serializable {

	protected final String name;
	protected final String fnSpecJson;
	protected final SerializableBiFunction <String, String, String> runConfigGetter;
	protected PyScalarFnRunner <PYOUT, HANDLE> runner;
	protected Class <PYOUT> pyOutType;

	public BasePyScalarFn(String name, String fnSpecJson, Class <PYOUT> pyOutType) {
		this(name, fnSpecJson, pyOutType, Collections. <String, String>emptyMap()::getOrDefault);
	}

	public BasePyScalarFn(String name, String fnSpecJson, Class <PYOUT> pyOutType,
						  SerializableBiFunction <String, String, String> runConfigGetter) {
		this.name = name;
		this.fnSpecJson = fnSpecJson;
		this.pyOutType = pyOutType;
		this.runConfigGetter = runConfigGetter;
	}

	@Override
	public void open(FunctionContext context) throws Exception {
		super.open(context);

		SerializableBiFunction <String, String, String> newRunConfigGetter = (key, defaultValue) -> {
			if (PY_TURN_ON_LOGGING_KEY.equals(key)) {
				return String.valueOf(AlinkGlobalConfiguration.isPrintProcessInfo());
			} else {
				return runConfigGetter.apply(key, context.getJobParameter(key, defaultValue));
			}
		};
		runner = new PyScalarFnRunner <>(fnSpecJson, pyOutType, newRunConfigGetter);
		runner.open();
	}

	@Override
	public void close() throws Exception {
		runner.close();
		super.close();
	}
}
