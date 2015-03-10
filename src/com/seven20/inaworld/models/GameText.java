/*
 * COPYRIGHT:
 *
 *   The computer systems, procedures, data bases and programs
 *   created and maintained by DST Systems, Inc., are proprietary
 *   in nature and as such are confidential.  Any unauthorized
 *   use or disclosure of such information may result in civil
 *   liabilities.
 *
 *   Copyright 2015 by DST Systems, Inc.
 *   All Rights Reserved.
 */
package com.seven20.inaworld.models;

import java.io.Serializable;

public abstract class GameText implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -5473605366236711157L;

    private String _text;
    
    protected GameText(String text) {
	_text = text;
    }

    protected String getText() {
        return _text;
    }
    
    public String toString(){
	return _text;
    }
    


}

