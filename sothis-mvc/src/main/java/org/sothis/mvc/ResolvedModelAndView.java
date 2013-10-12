package org.sothis.mvc;

/**
 * model��view�Ľ������
 * 
 * @author velna
 * 
 */
public class ResolvedModelAndView {
	private final View view;
	private final ModelAndView modelAndView;

	public ResolvedModelAndView(final ModelAndView modelAndView, final View view) {
		this.modelAndView = modelAndView;
		this.view = view;
	}

	public View getView() {
		return view;
	}

	public ModelAndView getModelAndView() {
		return modelAndView;
	}

}
