package com.dream.packing.common.abnormal;

import org.apache.log4j.Logger;

public class RollBackException extends RuntimeException {
	Logger logger = Logger.getLogger(RollBackException.class);
	private static final long serialVersionUID = -6079146169710614099L;

	public RollBackException(String msg, Throwable e) {
		logger.info(e.getMessage());
	}
}