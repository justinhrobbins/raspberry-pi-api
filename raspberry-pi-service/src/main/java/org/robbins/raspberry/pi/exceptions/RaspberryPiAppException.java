package org.robbins.raspberry.pi.exceptions;

public class RaspberryPiAppException extends Exception
{
	public RaspberryPiAppException(final String message) {
		super(message);
	}

	public RaspberryPiAppException(final String message, final Throwable cause) {
		super(message, cause);
	}
}
