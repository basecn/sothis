package com.velix.sothis.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface View {
	String getContentType();

	void render(Map<String, ?> model, HttpServletRequest request,
			HttpServletResponse response);
}
