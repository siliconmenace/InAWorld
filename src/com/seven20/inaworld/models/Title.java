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

/**
 * Child class of GameText that adds Title formatting
 * @author dt82437
 *
 */
 class Title extends GameText {

    public Title(String text) {
	super(text);
    }
    
    public String getTitle(){
	return getText();
    }

    
    
}

