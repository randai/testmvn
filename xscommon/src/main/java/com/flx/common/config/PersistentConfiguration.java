package com.flx.common.config;

import com.flx.common.SystemException;

/**
 * PersistentConfiguration allows configuration to be changed and persisted
 */
public interface PersistentConfiguration 
{
	/**
	 * commit change
	 */
	public void	commit()
	throws	SystemException;
}
