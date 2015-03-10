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

import com.seven20.inaworld.enums.CommandEnum;

public class Exit implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7291186705967235164L;
    private Location _leadsTo = null;
    private CommandEnum _direction;
    
    public Exit(){
	_direction = CommandEnum.UNDEFINED;
    }
    
    public Exit(CommandEnum direction, Location leadsTo){
	_direction = direction;
	_leadsTo = leadsTo;
    }
    
    public String toString(){
	return _direction.getDirName();
    }
    
    public String getDirectionName(){
	return _direction.getDirName();
    }
    
    public String getShortDirectionName(){
	return _direction.getShortName();
    }
    
    public Location getLeadsTo(){
	return _leadsTo;
    }
    

}

