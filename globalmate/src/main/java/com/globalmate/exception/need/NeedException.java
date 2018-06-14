package com.globalmate.exception.need;

import com.globalmate.exception.GMRuntimeException;

public class NeedException extends GMRuntimeException {

	public NeedException(String msg) {
		super(msg);
	}

	@Override
	protected int getCode() {
		return 0;
	}

}
