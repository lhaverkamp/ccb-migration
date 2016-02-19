package us.haverkamp.ccb.dao;

@SuppressWarnings("serial")
public class NoDataFoundException extends DataAccessException {
	public NoDataFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoDataFoundException(String message) {
		super(message);
	}

	public NoDataFoundException(Throwable cause) {
		super(cause);
	}
}
