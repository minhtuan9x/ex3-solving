package com.trongit.utils;

import com.trongit.constant.SystemConstant;

public class ValidateUtil {
	public static boolean isValid(Object obj) {
		if (obj == null)
			return false;
		else if (!obj.toString().equals(SystemConstant.EMPTY_STRING))
			return true;
		return false;
	}
}
