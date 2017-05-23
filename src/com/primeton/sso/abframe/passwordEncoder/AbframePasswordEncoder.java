package com.primeton.sso.abframe.passwordEncoder;

import com.eos.foundation.common.utils.CryptoUtil;
import org.jasig.cas.authentication.handler.PasswordEncoder;

public class AbframePasswordEncoder implements PasswordEncoder {
	public String encode(String password) {
		return CryptoUtil.digestByMD5(password);
	}
}