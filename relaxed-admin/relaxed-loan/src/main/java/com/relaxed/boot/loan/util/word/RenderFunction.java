package com.relaxed.boot.loan.util.word;

import com.deepoove.poi.XWPFTemplate;

/**
 * @author Yakir
 * @Topic RenderFunction
 * @Description
 * @date 2022/4/24 11:53
 * @Version 1.0
 */
@FunctionalInterface
public interface RenderFunction<R> {

	R render(XWPFTemplate xwpfTemplate);

}
