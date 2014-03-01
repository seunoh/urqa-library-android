package com.urqa.rank;

/**
 * @deprecated Use {@link com.urqa.android.rank.ErrorLevel} instead.
 */
public enum ErrorRank {
	Nothing(-1),Unhandle(0),Native(1),Critical(2),Major(3),Minor(4);
	 
	private final int value;
	
	ErrorRank(int value) 
	{
		this.value = value; 
	}
    public int value() 
    {
    	return value; 
    }
}
