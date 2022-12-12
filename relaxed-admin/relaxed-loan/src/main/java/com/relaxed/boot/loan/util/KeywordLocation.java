package com.relaxed.boot.loan.util;

import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yakir
 * @Topic KeywordLocation
 * @Description
 * @date 2022/12/12 16:01
 * @Version 1.0
 */
@Data
public class KeywordLocation {
    private Integer pageNum;

    private List<IPdfTextLocation> ipdfTextLocations=new ArrayList<>();
}
